package it.unibo.pixArt.model.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import it.unibo.pixArt.model.pixel.ImplPixel;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.tool.AbstractTool;
import it.unibo.pixArt.model.tool.ToolFactory;
import it.unibo.pixArt.model.tool.ToolFactoryImpl;

class AbstractToolTest {

    private ToolFactory toolFactory = new ToolFactoryImpl();
    private AbstractTool tool = toolFactory.createTool("PENCIL", null, 2);
    private Set<Pixel> TEST_FRAME = new HashSet<>();

    private void createFrame() {
        for(int i = 0; i < 16; i++) {
            for(int j = 0; j < 16; j++) {
                TEST_FRAME.add(new ImplPixel(i, j));
            }
        }
    }

    @Test
    void getFrameSize(){
        this.createFrame();
        assertEquals(16, tool.getFrameSize(TEST_FRAME));
    }
    
}
