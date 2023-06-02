package it.unibo.pixArt.model.tool;

import java.util.Set;
import java.util.stream.IntStream;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.utilities.Pair;

public abstract class AbstractDrawingTool extends AbstractTool {

    private final int toolSize;

    public AbstractDrawingTool(final int toolSize) {
        this.toolSize = toolSize;
    }

    /**
     * @return the size of the tool
     */
    public int getToolSize() {
        return this.toolSize;
    }

    @Override
    public abstract Set<Pixel> updateGrid(Pixel pixel, Set<Pixel> frame);

    /**
     * @param p pixel 
     * @param increment tool size
     * @param frameSize size of the frame
     * @return the position of the opposite pixel
     */
    public Pair<Integer, Integer> calculatePosition(final Pixel p, final int increment, final int frameSize) {
        int x;
        int y;

        x = (p.getPosition().getX() + (increment - 1) > frameSize) ? frameSize : p.getPosition().getX() + (increment - 1);
        y = (p.getPosition().getY() + (increment - 1) > frameSize) ? frameSize : p.getPosition().getY() + (increment - 1);

        return new Pair<>(x, y);
    }

    /**
     * @param x
     * @param x2
     * @return all the numbers between x and x2
     */
    public Iterable<Integer> range(final int x, final int x2) {
        return () -> IntStream.rangeClosed(x, x2).iterator();
    }

}
