package it.unibo.pixart.model.tools.drawingtools;

import it.unibo.pixart.model.pixel.Pixel;
import it.unibo.pixart.model.pixel.PixelBuilder;
import it.unibo.pixart.model.tools.AbstractDrawingTool;
import javafx.scene.paint.Color;

import java.util.Set;

/**
 * Implementation of the eraser.
 */
public final class Eraser extends AbstractDrawingTool {

    /**
     * @param toolSize size of the tools.
     */
    public Eraser(final int toolSize) {
        super(toolSize);
    }

    @Override
    public void updatePixel(final Set<Pixel> frame, final int x, final int y, final Set<Pixel> newPixSet) {
        final Pixel tempPix;
        tempPix = new PixelBuilder.PxlBuilder().setX(x).setY(y).build();
        tempPix.setColor(Color.WHITE);
        newPixSet.add(tempPix);
    }

}
