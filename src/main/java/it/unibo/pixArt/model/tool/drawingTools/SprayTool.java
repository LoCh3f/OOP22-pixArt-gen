package it.unibo.pixArt.model.tool.drawingTools;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.pixel.PixelBuilder;
import it.unibo.pixArt.model.tool.AbstractDrawingTool;
import it.unibo.pixArt.utilities.Pair;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SprayTool extends AbstractDrawingTool {

    private final Color selectedColor;

    public SprayTool(final Color selectedColor, final int size) {
        super(size);
        this.selectedColor = selectedColor;
    }

    @Override
    public Set<Pixel> updateGrid(Pixel pixel, Set<Pixel> frame) {
        Set<Pixel> newPixSet = new HashSet<>();
        Pair<Integer, Integer> oppositePixPos = calculatePosition(pixel, super.toolSize * 2, super.getFrameSize(frame));
        Pixel tempPix;
        Random rand = new Random();

        for (var x : range(pixel.getPosition().getX(), oppositePixPos.getX())) {
            for (var y : range(pixel.getPosition().getY(), oppositePixPos.getY())) {
                tempPix = new PixelBuilder.PxlBuilder().setX(x).setY(y).build();
                if (rand.nextBoolean()) {
                    tempPix.setColor(selectedColor);
                    newPixSet.add(tempPix);
                }
            }
        }
        return newPixSet;
    }

}
