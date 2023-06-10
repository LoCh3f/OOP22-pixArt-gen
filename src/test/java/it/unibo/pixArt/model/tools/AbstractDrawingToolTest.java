package it.unibo.pixArt.model.tools;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.pixel.PixelBuilder;
import it.unibo.pixArt.model.tool.AbstractDrawingTool;
import it.unibo.pixArt.model.tool.ToolFactory;
import it.unibo.pixArt.model.tool.ToolFactoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AbstractDrawingToolTest {

    private static final int CONSTCOORD = 13;
    private static final int CONSTCOORD2 = 15;
    private static final int CONSTCOORD3 = 5;
    private static final int CONSTCOORD4 = 12;
    private ToolFactory toolFactory = new ToolFactoryImpl();
    private AbstractDrawingTool tool = (AbstractDrawingTool) toolFactory.createTool("PENCIL", null, 2);
    private static final Pixel TEST_PIXEL1 = new PixelBuilder.PxlBuilder().setY(CONSTCOORD).setX(CONSTCOORD).build();
    private static final Pixel TEST_PIXEL2 = new PixelBuilder.PxlBuilder().setY(CONSTCOORD2).setX(10).build();

    @Test
    void calculatePosition() {
        assertEquals(CONSTCOORD2, tool.calculatePosition(TEST_PIXEL1, 3, 16).getX());
        assertEquals(CONSTCOORD2, tool.calculatePosition(TEST_PIXEL1, 3, 16).getY());
        assertEquals(16, tool.calculatePosition(TEST_PIXEL1, CONSTCOORD3, 16).getX());
        assertEquals(16, tool.calculatePosition(TEST_PIXEL1, CONSTCOORD3, 16).getY());
    }

    @Test
    void calculatePosition2() {
        assertEquals(CONSTCOORD4, tool.calculatePosition(TEST_PIXEL2, 3, 16).getX());
        assertEquals(16, tool.calculatePosition(TEST_PIXEL2, 3, 16).getY());
    }


}
