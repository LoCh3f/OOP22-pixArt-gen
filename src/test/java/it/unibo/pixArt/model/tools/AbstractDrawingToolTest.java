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
    private static final int PIXEL1_X = 13;
    private static final int PIXEL1_Y = 13;
    private static final int PIXEL2_X = 10;
    private static final int PIXEL2_Y = 15;
    private static final Pixel TEST_PIXEL1 = new PixelBuilder.PxlBuilder().setY(PIXEL1_Y).setX(PIXEL1_X).build(); 
    private static final Pixel TEST_PIXEL2 = new PixelBuilder.PxlBuilder().setY(PIXEL2_Y).setX(PIXEL2_X).build(); 
    private static final int FRAMESIZE = 16;

    @Test
    void calculatePosition() {
        final int increment1 = 3;
        final int increment2 = 5;
        assertEquals(PIXEL1_X + increment1 - 1, tool.calculatePosition(TEST_PIXEL1, increment1, FRAMESIZE).getX()); 
        assertEquals(PIXEL1_Y + increment1 - 1, tool.calculatePosition(TEST_PIXEL1, increment1, FRAMESIZE).getY()); 
        assertEquals(FRAMESIZE, tool.calculatePosition(TEST_PIXEL1, increment2, FRAMESIZE).getX()); 
        assertEquals(FRAMESIZE, tool.calculatePosition(TEST_PIXEL1, increment2, FRAMESIZE).getY()); 
    }

    @Test
    void calculatePosition2() {
        final int increment = 3;
        assertEquals(PIXEL2_X + increment - 1, tool.calculatePosition(TEST_PIXEL2, increment, FRAMESIZE).getX()); //
        assertEquals(FRAMESIZE, tool.calculatePosition(TEST_PIXEL2, increment, FRAMESIZE).getY());
    }


}
