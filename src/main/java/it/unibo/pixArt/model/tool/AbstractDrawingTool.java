package it.unibo.pixArt.model.tool;

import java.util.Set;
import java.util.stream.IntStream;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.utilities.Pair;

public abstract class AbstractDrawingTool extends AbstractTool {

    private int toolSize;

    public AbstractDrawingTool(final int toolSize){
        this.toolSize = toolSize;
    }

    @Override
    public abstract Set<Pixel> updateGrid(Pixel pixel, Set<Pixel> frame);

    public Pair<Integer, Integer> calculatePosition(final Pixel p, final int increment, final int frameSize) {
        int x;
        int y;

        x = (p.getPosition().getX() + (increment-1) > frameSize) ? frameSize : p.getPosition().getX() + (increment-1);
        y = (p.getPosition().getY() + (increment-1) > frameSize) ? frameSize : p.getPosition().getY() + (increment-1);

        return new Pair<>(x,y);
    }

    public Iterable<Integer> range(int x, int x2) {
        return ()->IntStream.rangeClosed(x, x2).iterator();
    }

}
