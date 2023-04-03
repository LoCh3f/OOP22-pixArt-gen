package it.unibo.pixArt.model.grid;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.utilities.Pair;
import javafx.scene.paint.Color;

import java.util.Set;

public interface PixelGrid {

    /**
     * @return the collection of pixel.
     */
    Set<Pixel> getPixels();

    /**
     * set all the pixel white color.
     */
    void deleteAll();

    /**
     * @param pixel pixel to be set white
     */
    void delete(final Pair<Integer, Integer> pixel);

    /**
     * @param pixel the pixel to set
     * @param color new color of the pixel
     */
    void draw(final Pair<Integer, Integer> pixel, final Color color);


}
