package it.unibo.pixArt.model.tool;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.IntStream;

import it.unibo.pixArt.model.pixel.ImplPixel;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.utilities.Pair;
import javafx.scene.paint.Color;

public class ToolFactoryImpl implements ToolFactory{

    @Override
    public Tool createBrush(final int brushSize, final Set<Pixel> frame) {
        
        return new Tool() {

            @Override
            public Set<Pixel> color(final Pixel pixel, final Color color) {
                Set<Pixel> newPixSet = new HashSet<>();
                var p2Position = calculatePosition(pixel); 
                Pixel p2 = new ImplPixel(p2Position.getX(), p2Position.getY());
                Pixel tempPix;

                for (var x: range(pixel.getPosition().getX(), p2.getPosition().getX())){
                    for (var y: range(pixel.getPosition().getY(), p2.getPosition().getY())){
                        tempPix = new ImplPixel(x,y);
                        tempPix.setColor(color);
                        newPixSet.add(tempPix);
                    }
                }

                return newPixSet;

            }

            private Iterable<Integer> range(int x, int x2) {
                return ()->IntStream.rangeClosed(x, x2).iterator();
            }

            private Pair<Integer, Integer> calculatePosition(final Pixel p1){
                int x = p1.getPosition().getX() + (brushSize-1);
                int y = p1.getPosition().getY() + (brushSize-1);
                if (x > frame.size()){
                    x = frame.size();
                }
                if (y > frame.size()){
                    y = frame.size();
                }
                return new Pair<>(x,y);
            }

        };
    }

    
    @Override
    public Tool createBucket(final HashMap<Pixel, Pixel> frame) {
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


    @Override
    public Tool createEraser() {
        return new Tool(){
            @Override
            public Set<Pixel> color(final Pixel pixel, final Color color) {
                pixel.setColor(Color.WHITE);
                return Collections.singleton(pixel);
            }
        };
    }


}
