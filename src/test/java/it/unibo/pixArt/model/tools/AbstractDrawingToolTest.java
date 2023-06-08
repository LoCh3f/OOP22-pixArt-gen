package it.unibo.pixArt.model.tools;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.pixel.PixelBuilder;
import it.unibo.pixArt.model.tool.AbstractDrawingTool;
import it.unibo.pixArt.model.tool.ToolFactory;
import it.unibo.pixArt.model.tool.ToolFactoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AbstractDrawingToolTest {

    private ToolFactory toolFactory = new ToolFactoryImpl();
    private AbstractDrawingTool tool = (AbstractDrawingTool) toolFactory.createTool("PENCIL", null, 2);
    private Pixel TEST_PIXEL1 = new PixelBuilder.PxlBuilder().setY(13).setX(13).build();
    private Pixel TEST_PIXEL2 = new PixelBuilder.PxlBuilder().setY(15).setX(10).build();

    @Test
    void calculatePosition() {
        assertEquals(15, tool.calculatePosition(TEST_PIXEL1, 3, 16).getX());
        assertEquals(15, tool.calculatePosition(TEST_PIXEL1, 3, 16).getY());
        assertEquals(16, tool.calculatePosition(TEST_PIXEL1, 5, 16).getX());
        assertEquals(16, tool.calculatePosition(TEST_PIXEL1, 5, 16).getY());
    }

    @Test
    void calculatePosition2() {
        assertEquals(12, tool.calculatePosition(TEST_PIXEL2, 3, 16).getX());
        assertEquals(16, tool.calculatePosition(TEST_PIXEL2, 3, 16).getY());
    }


}
