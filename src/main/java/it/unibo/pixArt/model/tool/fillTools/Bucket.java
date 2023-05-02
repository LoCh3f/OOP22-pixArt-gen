package it.unibo.pixArt.model.tool.fillTools;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.tool.AbstractTool;
import it.unibo.pixArt.utilities.Pair;
import javafx.scene.paint.Color;

public class Bucket extends AbstractTool{

    private final Color selectedColor;

    public Bucket(final Color selectedColor) {
        this.selectedColor = selectedColor;
    }

    @Override
    public Set<Pixel> updateGrid(final Pixel pixel, Set<Pixel> frame) {
        Map<Pair<Integer, Integer>, Pixel> frameMap = getFrameMap(frame);
        Set<Pixel> newSet = new HashSet<>();
        Color old_color = pixel.getColor();

        if (old_color.equals(this.selectedColor)) {
            return Collections.emptySet(); 
        }
        
        Queue<Pixel> queue = new LinkedList<>();
        queue.add(pixel);

        while (!queue.isEmpty()) {
            Pixel temp = queue.poll();
            if (temp == null || !isValid(frameMap, temp.getPosition().getX(), temp.getPosition().getY(), old_color, selectedColor)) {
                continue;
            }
            else {
                temp.setColor(selectedColor);
                newSet.add(temp);
                queue.add(frameMap.get(new Pair<>(temp.getPosition().getX()+1, temp.getPosition().getY())));
                queue.add(frameMap.get(new Pair<>(temp.getPosition().getX()-1, temp.getPosition().getY())));
                queue.add(frameMap.get(new Pair<>(temp.getPosition().getX(), temp.getPosition().getY()+1)));
                queue.add(frameMap.get(new Pair<>(temp.getPosition().getX(), temp.getPosition().getY()-1)));
            }
        }
        return newSet;
    }

    private boolean isValid(final Map<Pair<Integer, Integer>, Pixel> frame, final int x, final int y, final Color oldColor, final Color newColor) {
        if (frame.get(new Pair<>(x, y)).getColor() != oldColor || frame.get(new Pair<>(x, y)).getColor() == newColor) {
            return false;
        }  
        return true;
    }

    private Map<Pair<Integer, Integer>, Pixel> getFrameMap(Set<Pixel> frame) {
        return frame.stream().collect(Collectors.toMap(x->x.getPosition(), Function.identity()));
    }
}

