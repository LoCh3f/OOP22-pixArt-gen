package it.unibo.pixart.model.tools.drawingtools;

import it.unibo.pixart.model.pixel.Pixel;
import it.unibo.pixart.model.tools.AbstractDrawingTool;
import it.unibo.pixart.utilities.Pair;

import java.util.Set;

/**
 * Implementation of the lighten tools.
 */
public final class LightenTool extends AbstractDrawingTool {

    /**
     * Constructor.
     *
     * @param toolSize size of the tools
     */
    public LightenTool(final int toolSize) {
        super(toolSize);
    }

    @Override
    public void updatePixel(final Set<Pixel> frame, final int x, final int y, final Set<Pixel> newPixSet) {
        final Pixel tempPix;
        tempPix = frame.stream().filter(p -> p.getPosition().equals(new Pair<>(x, y))).findFirst().get();
        tempPix.setColor(tempPix.getColor().brighter());
        newPixSet.add(tempPix);
    }

}
