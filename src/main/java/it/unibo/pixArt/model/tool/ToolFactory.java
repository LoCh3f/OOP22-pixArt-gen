package it.unibo.pixArt.model.tool;

import java.util.HashMap;

import it.unibo.pixArt.model.pixel.Pixel;

public interface ToolFactory {

    Tool createNormalBrush();
    Tool createEraser();
    Tool createBucket(HashMap<Pixel, Pixel> frame);
    
}
