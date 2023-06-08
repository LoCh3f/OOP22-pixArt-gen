package it.unibo.pixArt.model.tool.drawingTools;

import java.util.Set;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.tool.AbstractDrawingTool;
import it.unibo.pixArt.utilities.Pair;


public final class DarkenTool extends AbstractDrawingTool {

    public DarkenTool(final int toolSize) {
        super(toolSize);
    }

    @Override
    public void updatePixel(Set<Pixel> frame, int x, int y, Set<Pixel> newPixSet) {
        final Pixel tempPix;
        tempPix = frame.stream().filter(p -> p.getPosition().equals(new Pair<>(x, y))).findFirst().get();
        tempPix.setColor(tempPix.getColor().darker());
        newPixSet.add(tempPix);
    }


}
