package it.unibo.pixArt.model.tool.drawingTools;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.pixel.PixelBuilder;
import it.unibo.pixArt.model.tool.AbstractDrawingTool;
import javafx.scene.paint.Color;

import java.util.Set;

public final class Pencil extends AbstractDrawingTool {

    private final Color selectedColor;

    public Pencil(final Color selectedColor, final int size) {
        super(size);
        this.selectedColor = selectedColor;
    }

    @Override
    public void updatePixel(Set<Pixel> frame, int x, int y, Set<Pixel> newPixSet) {
        final Pixel tempPix;
        tempPix = new PixelBuilder.PxlBuilder().setY(y).setX(x).build();
        tempPix.setColor(this.selectedColor);
        newPixSet.add(tempPix);
    }

}


