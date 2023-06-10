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

    private static final int TOOL_SIZE = 4;
    private static final Pencil TEST_PENCIL = new Pencil(Color.BLACK, TOOL_SIZE);
    private static final Set<Pixel> TEST_FRAME = new HashSet<>();
    private static final int FRAME_SIZE = 16;
    private static final int PIXEL_X = 2;
    private static final int PIXEL_Y = 2;

    private void createFrame() {
        for (int i = 0; i < FRAME_SIZE; i++) {
            for (int j = 0; j < FRAME_SIZE; j++) {
                TEST_FRAME.add(new PixelBuilder.PxlBuilder().setX(i).setY(j).build());
            }
        }
    }


    @Test
    void updateGrid() {
        this.createFrame();
        assertEquals(FRAME_SIZE * FRAME_SIZE, TEST_FRAME.size());
        final Pixel pixel = new PixelBuilder.PxlBuilder().setX(PIXEL_X).setY(PIXEL_Y).build();
        final Set<Pixel> newSet = TEST_PENCIL.updateGrid(pixel, TEST_FRAME);
        assertEquals(FRAME_SIZE, newSet.size());
        for (final Pixel p : newSet) {
            assertEquals(Color.BLACK, p.getColor());
            assertTrue(p.getPosition().getX() >= PIXEL_X && p.getPosition().getX() <= PIXEL_X + TOOL_SIZE - 1);
            assertTrue(p.getPosition().getY() >= PIXEL_Y && p.getPosition().getY() <= PIXEL_Y + TOOL_SIZE - 1);
        }
    }

}
