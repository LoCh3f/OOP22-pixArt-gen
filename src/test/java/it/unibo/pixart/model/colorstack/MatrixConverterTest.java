package it.unibo.pixart.model.colorstack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import it.unibo.pixart.model.grid.Matrix;
import it.unibo.pixart.model.grid.PixelMatrix;
import it.unibo.pixart.model.pixel.Pixel;
import it.unibo.pixart.utilities.MatrixConverter;
import javafx.scene.paint.Color;

/** The Test class of the MatrixConverter class.
 */
class MatrixConverterTest {
    private static final Matrix GRID = new PixelMatrix.MatrixBuilder().setRows(16).setColumns(16).build();
    private static final int MATRIXSIZE = 256;

    @Test
    void integrityTest() {
        final Map<Color, Set<Pixel>> map = new MatrixConverter().apply(GRID.getPixels());
        for (final var key : map.keySet()) {
            assertEquals(Color.WHITE, key);
        }
        assertEquals(1, map.keySet().size());
        assertEquals(MATRIXSIZE, map.values().stream().flatMap(Set::stream).count());
    }

    @Test
    void emptyMap() {
        final Map<Color, Set<Pixel>> map = new MatrixConverter().apply(Collections.emptySet());
        assertEquals(0, map.keySet().size());
        assertEquals(0, map.values().stream().flatMap(Set::stream).count());
    }
}
