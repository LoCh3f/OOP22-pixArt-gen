package it.unibo.pixArt.model.tools;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.pixel.PixelBuilder;
import it.unibo.pixArt.model.tool.drawingTools.Pencil;
import javafx.scene.paint.Color;

class PencilTest {

    private static final Pencil TEST_PENCIL = new Pencil(Color.BLACK, 4);
    private static final Set<Pixel> TEST_FRAME = new HashSet<>();
    private static final int FRAMESIZE = 256;
    private static final int CONSTCOORD = 5;

    private void createFrame() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                TEST_FRAME.add(new PixelBuilder.PxlBuilder().setX(i).setY(j).build());
            }
        }
    }


    @Test
    void updateGrid() {
        this.createFrame();
        assertEquals(FRAMESIZE, TEST_FRAME.size());
        Pixel pixel = new PixelBuilder.PxlBuilder().setX(2).setY(2).build();
        Set<Pixel> newSet = TEST_PENCIL.updateGrid(pixel, TEST_FRAME);
        assertEquals(16, newSet.size());
        for (Pixel p : newSet) {
            assertEquals(Color.BLACK, p.getColor());
            assertTrue(p.getPosition().getX() >= 2 && p.getPosition().getX() <= CONSTCOORD);
            assertTrue(p.getPosition().getY() >= 2 && p.getPosition().getY() <= CONSTCOORD);
        }
    }

}
