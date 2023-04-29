package it.unibo.pixArt.model.tool.drawingTools;

import java.util.Collections;
import java.util.Set;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.tool.AbstractDrawingTool;
import javafx.scene.paint.Color;

public class Eraser extends AbstractDrawingTool{

    public Eraser(int toolSize) {
        super(toolSize);
        //TODO Auto-generated constructor stub
    }

    @Override
    public Set<Pixel> updateGrid(Pixel pixel, Set<Pixel> frame) {
        pixel.setColor(Color.WHITE);
        return Collections.singleton(pixel);
    }

}
