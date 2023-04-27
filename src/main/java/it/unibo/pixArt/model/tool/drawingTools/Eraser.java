package it.unibo.pixArt.model.tool.drawingTools;

import java.util.Collections;
import java.util.Set;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.tool.AbstractDrawingTool;
import javafx.scene.paint.Color;

public class Eraser extends AbstractDrawingTool{

    @Override
    public Set<Pixel> updatePixel(Pixel pixel, int size, Set<Pixel> frame) {
        pixel.setColor(Color.WHITE);
        return Collections.singleton(pixel);
    }

}
