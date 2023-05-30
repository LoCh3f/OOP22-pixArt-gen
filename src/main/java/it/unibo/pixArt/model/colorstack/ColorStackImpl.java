package it.unibo.pixArt.model.colorstack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import it.unibo.pixArt.model.pixel.Pixel;
import javafx.scene.paint.Color;

public class ColorStackImpl implements ColorStack {
    private final Map<Color,Set<Pixel>> colorMap = new HashMap<>();
    private int initialSize;

    public ColorStackImpl(final Set<Pixel> pixels) {
        final List<Color> colors = pixels.stream().map(e ->e.getColor()).distinct().collect(Collectors.toList());
        for(var elem : colors) {
            final Set<Pixel> colorSet = pixels.stream().filter(e -> e.getColor().equals(elem)).collect(Collectors.toSet());
            this.colorMap.put(elem, colorSet);
        }
        this.initialSize = pixels.size();
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
        if(elem.isPresent()) {
            this.colorMap.get(color).remove(elem.get());
            if( this.colorMap.get(color).isEmpty()) {
                this.colorMap.remove(color);
            }
        }
        /*Write better code */
    }

    @Override
    public float getPercentage() {
        final int totalPixels = this.colorMap.entrySet().stream().mapToInt(e -> e.getValue().size()).sum();
        return 100 - ((totalPixels * 100) / initialSize);
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
