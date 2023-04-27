package it.unibo.pixArt.model.grid;

import it.unibo.pixArt.model.grid.PixelMatrix.MatrixBuilder;
import it.unibo.pixArt.model.pixel.ImplPixel;
import it.unibo.pixArt.model.utilities.TestVariable;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PixelMatrixTest {

    private static final ImplPixel TEST_PIXEL = new ImplPixel(0, 0);

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
        System.out.println(TEST_PIXEL.getColor());
        final PixelMatrix matrix = new MatrixBuilder().setRows(16).setColumns(16).build();
        matrix.update(p -> {
            if(p.comparePixel(TEST_PIXEL)) {
                p.setColor(TEST_PIXEL.getColor());
            }
        }, TEST_PIXEL);

        matrix.getPixels().forEach(p -> {
            if(p.comparePixel(TEST_PIXEL)) {
                assertEquals(p.getColor(), TEST_PIXEL.getColor());
                System.out.println(p.getColor());
            } else {
                assertEquals(p.getColor(), Color.WHITE);
            }
        });
        System.out.println(TestVariable.ANSI_RED + "Test passed" + TestVariable.ANSI_RESET);

    }
}
