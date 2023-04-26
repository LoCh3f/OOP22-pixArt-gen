package it.unibo.pixArt.model.tool;

import java.util.HashMap;
import java.util.Set;

import it.unibo.pixArt.model.pixel.Pixel;
import javafx.scene.paint.Color;

public interface ToolFactory {

    Tool createPencil(int pencilSize, Set<Pixel> frame, Color color);
    Tool createBrush(int brushSize, Set<Pixel> frame, Color color);
    Tool createEraser();
    Tool createBucket(HashMap<Pixel, Pixel> frame, Color color);
    Tool createLightenTool();
    Tool createDarkenTool();
    
}
