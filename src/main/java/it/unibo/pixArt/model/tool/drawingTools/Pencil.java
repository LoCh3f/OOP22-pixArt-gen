package it.unibo.pixArt.model.tool.drawingTools;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.pixel.PixelBuilder;
import it.unibo.pixArt.model.tool.AbstractDrawingTool;
import javafx.scene.paint.Color;

import java.util.Set;

/**
 * Implementation of Pencil. 
 */
public final class Pencil extends AbstractDrawingTool {

    private final Color selectedColor;

    /**
     * Constructor
     * @param selectedColor 
     * @param size size of the tool
     */
    public Pencil(final Color selectedColor, final int size) {
        super(size);
        this.selectedColor = selectedColor;
    }

    @Override
    public void updatePixel(final Set<Pixel> frame, final int x, final int y, final Set<Pixel> newPixSet) {
        final Pixel tempPix;
        tempPix = new PixelBuilder.PxlBuilder().setY(y).setX(x).build();
        tempPix.setColor(this.selectedColor);
        newPixSet.add(tempPix);
    }

}


