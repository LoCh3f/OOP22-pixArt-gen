package it.unibo.pixart.model.tools;

import it.unibo.pixart.model.pixel.Pixel;
import it.unibo.pixart.model.pixel.PixelBuilder;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AbstractToolTest {

    private final ToolFactory toolFactory = new ToolFactoryImpl();
    private final AbstractTool tool = toolFactory.createTool("PENCIL", null, 2);
    private final Set<Pixel> testFrame = new HashSet<>();

    private void createFrame() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                testFrame.add(new PixelBuilder.PxlBuilder().setX(i).setY(j).build());
            }
        }
    }

    @Test
    void testGetFrameSize() {
        this.createFrame();
        assertEquals(16, tool.getFrameSize(testFrame));
    }

}
