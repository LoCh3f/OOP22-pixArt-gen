package it.unibo.pixArt.model.tool.drawingTools;

import java.util.Set;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.tool.AbstractDrawingTool;

public class Brush extends AbstractDrawingTool{

    public Brush(int toolSize) {
        super(toolSize);
    }

    @Override
    public Set<Pixel> updateGrid(Pixel pixel, Set<Pixel> frame) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePixel'");
    }

}
