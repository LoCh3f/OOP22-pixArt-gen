package it.unibo.pixArt.model.tool.drawingTools;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.pixel.PixelBuilder;
import it.unibo.pixArt.model.tool.AbstractDrawingTool;
import javafx.scene.paint.Color;

import java.util.Set;

/**
 * Implementation of the eraser.
 */
public final class Eraser extends AbstractDrawingTool {

    /**
     * @param toolSize size of the tool.
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
