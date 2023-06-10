package it.unibo.pixart.model.colorstack;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import it.unibo.pixart.model.pixel.Pixel;
import javafx.scene.paint.Color;

/**
 * Implementation of ColorStack.
 */
public final class ColorStackImpl implements ColorStack {
    private  Map<Color, Set<Pixel>> colorMap = new HashMap<>();
    private int initialSize;

    /**
     * Constructor.
     * @param colorMap
     * @param size
     */
    public ColorStackImpl(final Map<Color, Set<Pixel>> colorMap, final int size) {
        this.colorMap = new HashMap<>(colorMap);
        this.initialSize = size;
    }

    @Override
    public Map<Color, Set<Pixel>> getColorMap() {
        return this.colorMap;
    }

    @Override
    public void removePixel(final Color color, final Pixel pixel) {
        final Optional<Pixel> elem = this.colorMap.values().stream()
                .flatMap(Set::stream)
                .filter(p -> p.equals(pixel)).findAny();
        if (elem.isPresent()) {
            this.colorMap.get(color).remove(elem.get());
            if (this.colorMap.get(color).isEmpty()) {
                this.colorMap.remove(color);
            }
        }
    }

    @Override
    public double getPercentage() {
        final int currentSize = this.colorMap.entrySet().stream()
                .mapToInt(e -> e.getValue().size())
                .sum();
        return 100 - (currentSize * 100) / this.initialSize;
    }

    @Override
    public boolean isPresent(final Pixel pixel) {
        return this.colorMap.values().stream()
                .flatMap(Set::stream)
                .anyMatch(p -> p.equals(pixel));
    }

    @Override
    public boolean isEmpty() {
        return this.colorMap.isEmpty();
    }
}
