package it.unibo.pixArt.model.tool.drawingTools;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.pixel.PixelBuilder;
import it.unibo.pixArt.model.tool.AbstractDrawingTool;
import javafx.scene.paint.Color;

import java.util.Set;

public final class Eraser extends AbstractDrawingTool {

    public Eraser(final int toolSize) {
        super(toolSize);
    }

    @Override
    public void updatePixel(Set<Pixel> frame, int x, int y, Set<Pixel> newPixSet) {
        final Pixel tempPix;
        tempPix = new PixelBuilder.PxlBuilder().setX(x).setY(y).build();
        tempPix.setColor(Color.WHITE);
        newPixSet.add(tempPix);
    }

}
