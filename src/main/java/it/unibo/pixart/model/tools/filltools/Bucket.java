package it.unibo.pixart.model.tools.fillTools;

import it.unibo.pixart.model.pixel.Pixel;
import it.unibo.pixart.model.tools.AbstractTool;
import it.unibo.pixart.utilities.Pair;
import javafx.scene.paint.Color;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Implementation of Bucket.
 */
public final class Bucket extends AbstractTool {

    private final Color selectedColor;

    /**
     * Constructor.
     *
     * @param selectedColor
     */
    public Bucket(final Color selectedColor) {
        this.selectedColor = selectedColor;
    }

    @Override
    public Set<Pixel> updateGrid(final Pixel pixel, final Set<Pixel> frame) {
        final Color oldColor = pixel.getColor();

        if (oldColor.equals(this.selectedColor)) {
            return Collections.emptySet();
        }

        final Map<Pair<Integer, Integer>, Pixel> frameMap = getFrameMap(frame);
        final Set<Pixel> newSet = new HashSet<>();
        final Queue<Pixel> queue = new LinkedList<>();
        queue.add(pixel);

        while (!queue.isEmpty()) {
            final Pixel temp = queue.poll();
            if (temp == null
                    || !isValid(frameMap, temp.getPosition().getX(), temp.getPosition().getY(), oldColor, selectedColor)) {
                continue;
            } else {
                temp.setColor(selectedColor);
                newSet.add(temp);
                queue.add(frameMap.get(new Pair<>(temp.getPosition().getX() + 1, temp.getPosition().getY())));
                queue.add(frameMap.get(new Pair<>(temp.getPosition().getX() - 1, temp.getPosition().getY())));
                queue.add(frameMap.get(new Pair<>(temp.getPosition().getX(), temp.getPosition().getY() + 1)));
                queue.add(frameMap.get(new Pair<>(temp.getPosition().getX(), temp.getPosition().getY() - 1)));
            }
        }
        return newSet;
    }

    private boolean isValid(final Map<Pair<Integer, Integer>, Pixel> frame,
                            final int x, final int y, final Color oldColor, final Color newColor) {
        return !(!frame.get(new Pair<>(x, y)).getColor().equals(oldColor)
                || frame.get(new Pair<>(x, y)).getColor().equals(newColor));
    }

    private Map<Pair<Integer, Integer>, Pixel> getFrameMap(final Set<Pixel> frame) {
        return frame.stream().collect(Collectors.toMap(x -> x.getPosition(), Function.identity()));
    }
}

