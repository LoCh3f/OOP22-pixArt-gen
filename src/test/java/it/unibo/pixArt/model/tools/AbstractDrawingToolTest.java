package it.unibo.pixArt.model.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.pixArt.model.pixel.ImplPixel;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.tool.AbstractDrawingTool;
import it.unibo.pixArt.model.tool.ToolFactory;
import it.unibo.pixArt.model.tool.ToolFactoryImpl;

class AbstractDrawingToolTest {

    private ToolFactory toolFactory = new ToolFactoryImpl();
    private AbstractDrawingTool tool = (AbstractDrawingTool) toolFactory.createTool("PENCIL", null, 2);
    private Pixel TEST_PIXEL1 = new ImplPixel(13, 13);
    private Pixel TEST_PIXEL2 = new ImplPixel(15, 10);


    @Test
    void calculatePosition() {
        assertEquals(15, tool.calculatePosition(TEST_PIXEL1, 3, 16).getX());
        assertEquals(15, tool.calculatePosition(TEST_PIXEL1, 3, 16).getY());
        assertEquals(16, tool.calculatePosition(TEST_PIXEL1, 5, 16).getX());
        assertEquals(16, tool.calculatePosition(TEST_PIXEL1, 5, 16).getY());
    }

    @Test
    void calculatePosition2() {
        assertEquals(16, tool.calculatePosition(TEST_PIXEL2, 3, 16).getX());
        assertEquals(12, tool.calculatePosition(TEST_PIXEL2, 3, 16).getY());
    }

    
}
