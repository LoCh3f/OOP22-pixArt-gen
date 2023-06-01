package it.unibo.pixArt.model.tool.drawingTools;

import java.util.HashSet;
import java.util.Set;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.tool.AbstractDrawingTool;
import it.unibo.pixArt.utilities.Pair;

public final class LightenTool extends AbstractDrawingTool {

    public LightenTool(final int toolSize) {
        super(toolSize);
    }

    @Override
    public Set<Pixel> updateGrid(final Pixel pixel, final Set<Pixel> frame) {
        Set<Pixel> newPixSet = new HashSet<>();
        Pair<Integer, Integer> oppositePixPos = super.calculatePosition(pixel, super.getToolSize(), super.getFrameSize(frame));
        Pixel tempPix;

        for (var x: range(pixel.getPosition().getX(), oppositePixPos.getX())) {
            for (var y: range(pixel.getPosition().getY(), oppositePixPos.getY())) {
                tempPix = frame.stream().filter(p -> p.getPosition().equals(new Pair<>(x, y))).findFirst().get();
                tempPix.setColor(tempPix.getColor().brighter());
                newPixSet.add(tempPix);
            }
        }

        return newPixSet;
    }

}
