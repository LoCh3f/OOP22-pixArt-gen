package it.unibo.pixArt.model.tool;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import it.unibo.pixArt.model.pixel.ImplPixel;
import it.unibo.pixArt.model.pixel.Pixel;
import javafx.scene.paint.Color;

public class ToolFactoryImpl implements ToolFactory{

   @Override
    public Tool createNormalBrush() {
        return new Tool(){
            @Override
            public Set<Pixel> color(final Pixel pixel, final Color color) {
                pixel.setColor(color);
                return Collections.singleton(pixel);
            }
        };
    }

    
    @Override
    public Tool createBucket(HashMap<Pixel, Pixel> frame) {
        return new Tool(){
            @Override
            public Set<Pixel> color(final Pixel pixel, final Color color) {
                Set<Pixel> newSet = new HashSet<>();
                Color old_color = pixel.getColor();
                if (old_color.equals(color)) {
                    return Collections.emptySet(); 
                }
                Queue<Pixel> queue = new LinkedList<>();
                queue.add(pixel);
                while (!queue.isEmpty()) {
                    Pixel temp = queue.poll();
                    if (!isValid(frame, temp.getPosition().getX(), temp.getPosition().getY(), old_color, color)) {
                        continue;
                    }
                    else {
                        temp.setColor(color);
                        newSet.add(temp);
                        queue.add(frame.get(new ImplPixel(temp.getPosition().getX()+1, temp.getPosition().getY())));
                        queue.add(frame.get(new ImplPixel(temp.getPosition().getX()-1, temp.getPosition().getY())));
                        queue.add(frame.get(new ImplPixel(temp.getPosition().getX(), temp.getPosition().getY()+1)));
                        queue.add(frame.get(new ImplPixel(temp.getPosition().getX(), temp.getPosition().getY()-1)));
                    }
                }
                return newSet;
            }

            private boolean isValid(final HashMap<Pixel, Pixel> frame, final int x, final int y, final Color oldColor, final Color newColor) {
                if (x < 0 || x >= frame.size() || y < 0 || y >= frame.size() || frame.get(new ImplPixel(x, y)).getColor() != oldColor 
                    || frame.get(new ImplPixel(x, y)).getColor() == newColor) {
                    return false;
                }  
                return true;
            }
        };
    }


}
