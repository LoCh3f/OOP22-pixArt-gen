package it.unibo.pixart.model.pixel;

import it.unibo.pixart.utilities.Pair;
import javafx.scene.paint.Color;

/**
 * The interface of the Pixel
 */
public interface Pixel {


    /**
     * @return the position of the pixel.
     */
    Pair<Integer, Integer> getPosition();

    /**
     * @return the color of the pixel.
     */
    Color getColor();

    /**
     * @param color to set for the pixel.
     */
    void setColor(Color color);

    /**
     * @param pixel to compare to this pixel,
     * @return true if pixel coordinates are the same otherwise false;
     */
    boolean comparePixel(Pixel pixel);


}
