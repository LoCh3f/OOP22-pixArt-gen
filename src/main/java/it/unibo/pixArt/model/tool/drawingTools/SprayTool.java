package it.unibo.pixArt.model.tool.drawingTools;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.pixel.PixelBuilder;
import it.unibo.pixArt.model.tool.AbstractDrawingTool;

import javafx.scene.paint.Color;

import java.util.Random;
import java.util.Set;

public final class SprayTool extends AbstractDrawingTool {

    private final Color selectedColor;

    public SprayTool(final Color selectedColor, final int size) {
        super(size);
        this.selectedColor = selectedColor;
    }

    @Override
    public void updatePixel(final Set<Pixel> frame, final int x, final int y, final Set<Pixel> newPixSet) {
        final Pixel tempPix;
        final Random rand = new Random();
        tempPix = new PixelBuilder.PxlBuilder().setX(x).setY(y).build();
        if (rand.nextBoolean()) {
            tempPix.setColor(selectedColor);
            newPixSet.add(tempPix);
        }
    }

}
