package it.unibo.pixart.model.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import it.unibo.pixart.model.pixel.Pixel;
import it.unibo.pixart.model.pixel.PixelBuilder;
import it.unibo.pixart.model.tool.AbstractTool;
import it.unibo.pixart.model.tool.ToolFactory;
import it.unibo.pixart.model.tool.ToolFactoryImpl;


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
