package it.unibo.pixart.model.grid;

import it.unibo.pixart.model.framestate.FrameState;
import it.unibo.pixart.model.pixel.Pixel;

import java.util.Set;
import java.util.function.Consumer;

/**
 * Interface for classes that represent a matrix of pixels.
 */
public interface Matrix {

    /**
     * @return the collection of pixel.
     */
    Set<Pixel> getPixels();

    /**
     * @param pixels the set of pixels that must be copied;
     */
    void setPixel(Set<Pixel> pixels);


    /**
     * @param consumer change to apply to a pixel,
     * @param pixel    is the new state of the pixel that need to be consumed;
     */
    void update(Consumer<Pixel> consumer, Pixel pixel);

    /**
     * @return the rows of the grid;
     */
    int getRows();

    /**
     * @return the columns of the grid,
     */
    int getColumns();

    /**
     * @return the memento field;
     */
    FrameState getMemento();

    /**
     * Back to the last change of the matrix.
     */
    void revert();

}
