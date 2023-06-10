package it.unibo.pixart.model.tool.drawingtools;

import java.util.Set;

import it.unibo.pixart.model.pixel.Pixel;
import it.unibo.pixart.model.tool.AbstractDrawingTool;
import it.unibo.pixart.utilities.Pair;

/**
 * Implementation of Darken Tool.
 */
public final class DarkenTool extends AbstractDrawingTool {

    /**
     * Constructor.
     * @param toolSize size of the tool.
     */
    public DarkenTool(final int toolSize) {
        super(toolSize);
    }

    @Override
    public void updatePixel(final Set<Pixel> frame, final int x, final int y, final Set<Pixel> newPixSet) {
        final Pixel tempPix;
        tempPix = frame.stream().filter(p -> p.getPosition().equals(new Pair<>(x, y))).findFirst().get();
        tempPix.setColor(tempPix.getColor().darker());
        newPixSet.add(tempPix);
    }


}
