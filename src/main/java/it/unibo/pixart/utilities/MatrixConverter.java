package it.unibo.pixart.utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import it.unibo.pixart.model.pixel.Pixel;
import javafx.scene.paint.Color;

/**
 * A class that implements the Function interface, that takes a Set of Pixel(Set<Pixel>) and creates a Map where:
 *  Key->Color, Value -> Set<Pixel> (all the pixels with color = Key).
 */
public final class MatrixConverter implements Function<Set<Pixel>, Map<Color, Set<Pixel>>>  {
    private final Map<Color, Set<Pixel>> colorMap = new HashMap<>();

    @Override
    public Map<Color, Set<Pixel>> apply(final Set<Pixel> pixels) {
        final List<Color> colors = pixels.stream().map(e -> e.getColor()).distinct().collect(Collectors.toList());
        for (final var elem : colors) {
            final Set<Pixel> colorSet = pixels.stream().filter(e -> e.getColor().equals(elem)).collect(Collectors.toSet());
            this.colorMap.put(elem, colorSet);
        }
        return this.colorMap;
    } 
}
