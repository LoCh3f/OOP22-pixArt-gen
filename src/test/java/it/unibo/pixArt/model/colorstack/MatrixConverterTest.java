package it.unibo.pixArt.model.colorstack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import it.unibo.pixArt.model.grid.Matrix;
import it.unibo.pixArt.model.grid.PixelMatrix;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.utilities.MatrixConverter;
import javafx.scene.paint.Color;

public class MatrixConverterTest {
    private static final Matrix grid = new PixelMatrix.MatrixBuilder().setRows(16).setColumns(16).build();
    
    @Test
    void integrityTest() {
        final Map<Color,Set<Pixel>> map = new MatrixConverter().apply(grid.getPixels());
        for(var key : map.keySet()) {
            assertEquals(Color.WHITE, key);
        }
        assertEquals(1,map.keySet().size());
        assertEquals(256, map.values().stream().flatMap(Set::stream).count());
    }

    @Test
    void emptyMap() {
        final Map<Color,Set<Pixel>> map = new MatrixConverter().apply(Collections.emptySet());
        assertEquals(0,map.keySet().size());
        assertEquals(0, map.values().stream().flatMap(Set::stream).count());
    }
}
