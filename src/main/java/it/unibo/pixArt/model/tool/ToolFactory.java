package it.unibo.pixArt.model.tool;

import java.util.HashMap;
import java.util.Set;

import it.unibo.pixArt.model.pixel.Pixel;

public interface ToolFactory {

    Tool createBrush(int brushSize, Set<Pixel> frame);
    Tool createEraser();
    Tool createBucket(HashMap<Pixel, Pixel> frame);
    
}
