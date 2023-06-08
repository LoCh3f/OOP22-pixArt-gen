package it.unibo.pixArt.model.tool.drawingTools;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.pixel.PixelBuilder;
import it.unibo.pixArt.model.tool.AbstractDrawingTool;
import it.unibo.pixArt.utilities.Pair;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Set;

public final class Pencil extends AbstractDrawingTool {

    private final Color selectedColor;

    public Pencil(final Color selectedColor, final int size) {
        super(size);
        this.selectedColor = selectedColor;
    }

    @Override
    public Set<Pixel> updateGrid(final Pixel pixel, final Set<Pixel> frame) {
        final Set<Pixel> newPixSet = new HashSet<>();
        Pair<Integer, Integer> oppositePixPos = super.calculatePosition(pixel, super.getToolSize(), super.getFrameSize(frame));
        Pixel tempPix;

        for (var x : range(pixel.getPosition().getX(), oppositePixPos.getX())) {
            for (var y : range(pixel.getPosition().getY(), oppositePixPos.getY())) {
                tempPix = new PixelBuilder.PxlBuilder().setY(y).setX(x).build();
                tempPix.setColor(this.selectedColor);
                newPixSet.add(tempPix);
            }
        }

        return newPixSet;

    }
}


