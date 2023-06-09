package it.unibo.pixArt.model.grid;

import it.unibo.pixArt.model.grid.PixelMatrix.MatrixBuilder;
import it.unibo.pixArt.model.pixel.ImplPixel;
import it.unibo.pixArt.model.pixel.PixelBuilder;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * The Test class of the PixelMatrix class
 */
public class PixelMatrixTest {

    private static final ImplPixel TEST_PIXEL = new PixelBuilder.PxlBuilder().setX(0).setY(0).build();

    @Test
    void testBuilder() {
        assertThrows(IllegalStateException.class, () -> new MatrixBuilder().setRows(15).setColumns(15).build());
        assertThrows(IllegalStateException.class, () -> new MatrixBuilder().setRows(15).setColumns(16).build());
        assertThrows(IllegalStateException.class, () -> new MatrixBuilder().setRows(16).setColumns(15).build());
        assertDoesNotThrow(() -> new MatrixBuilder().setRows(16).setColumns(16).build());
    }

    @Test
    void testUpdate() {
        TEST_PIXEL.setColor(Color.BLACK);
        final PixelMatrix matrix = new MatrixBuilder().setRows(16).setColumns(16).build();
        matrix.update(p -> {
            if (p.comparePixel(TEST_PIXEL)) {
                p.setColor(TEST_PIXEL.getColor());
            }
        }, TEST_PIXEL);

        matrix.getPixels().forEach(p -> {
            if (p.comparePixel(TEST_PIXEL)) {
                assertEquals(p.getColor(), TEST_PIXEL.getColor());
                System.out.println(p.getColor());
            } else {
                assertEquals(p.getColor(), Color.WHITE);
            }
        });

    }
}
