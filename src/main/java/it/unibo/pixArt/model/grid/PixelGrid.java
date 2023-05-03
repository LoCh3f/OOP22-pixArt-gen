package it.unibo.pixArt.model.grid;

import it.unibo.pixArt.model.framestate.FrameState;
import it.unibo.pixArt.model.pixel.Pixel;

import java.util.Set;
import java.util.function.Consumer;

public interface PixelGrid {

    /**
     * @return the collection of pixel.
     */
    Set<Pixel> getPixels();

    void setPixel(final Set<Pixel> pixels);


    public void update(final Consumer<Pixel> consumer, Pixel pixel);

    int getRows();

    int getColumns();

    FrameState getMemento();


}
