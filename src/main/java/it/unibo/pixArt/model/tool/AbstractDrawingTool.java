package it.unibo.pixArt.model.tool;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.utilities.Pair;

/**
 * Class that implements the methods for generic tools.
 */
public abstract class AbstractDrawingTool extends AbstractTool {

    private final int toolSize;

    /**
     * @param toolSize size of the tool.
     */
    public AbstractDrawingTool(final int toolSize) {
        this.toolSize = toolSize;
    }

    /**
     * @return the size of the tool
     */
    public int getToolSize() {
        return this.toolSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Pixel> updateGrid(final Pixel pixel, final Set<Pixel> frame) {
        final Set<Pixel> newPixSet = new HashSet<>();
        final Pair<Integer, Integer> oppositePixPos = calculatePosition(pixel, getToolSize(), super.getFrameSize(frame));

        for (var x: range(pixel.getPosition().getX(), oppositePixPos.getX())) {
            for (var y: range(pixel.getPosition().getY(), oppositePixPos.getY())) {
                this.updatePixel(frame, x, y, newPixSet);
            }
        }

        return newPixSet;
    }

    /**
     * @param frame pixel grid
     * @param x 
     * @param y
     * @param newPixSet new set of pixel
     * update the color of the pixel.
     */
    public abstract void updatePixel(Set<Pixel> frame, int x, int y, Set<Pixel> newPixSet);

    /**
     * @param p pixel 
     * @param increment tool size
     * @param frameSize size of the frame
     * @return the position of the opposite pixel
     */
    public Pair<Integer, Integer> calculatePosition(final Pixel p, final int increment, final int frameSize) {
        int x;
        int y;

        x = p.getPosition().getX() + (increment - 1) > frameSize ? frameSize : p.getPosition().getX() + (increment - 1);
        y = p.getPosition().getY() + (increment - 1) > frameSize ? frameSize : p.getPosition().getY() + (increment - 1);

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
