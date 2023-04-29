package it.unibo.pixArt.model.tool.drawingTools;

import java.util.Collections;
import java.util.Set;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.tool.AbstractDrawingTool;

public class DarkenTool extends AbstractDrawingTool{

    public DarkenTool(int toolSize) {
        super(toolSize);
        //TODO Auto-generated constructor stub
    }

    @Override
    public Set<Pixel> updateGrid(Pixel pixel,Set<Pixel> frame) {
        pixel.setColor(pixel.getColor().brighter());
        return Collections.singleton(pixel);
    }


}
