package it.unibo.pixArt.model.pixel;

import it.unibo.pixArt.utilities.Pair;
import javafx.scene.paint.Color;

public interface Pixel {

    /**
     * @return position of the pixel.
     */
    Pair<Integer, Integer> getPosition();

    /**
     * @param x coordinate to set for the pixel,
     * @param y "   "  "   "   "   ".
     */
    void setPosition(final Integer x, final Integer y);

    /**
     * @return the color of the pixel.
     */
    Color getColor();

    /**
     * @param color to set for the pixel.
     */
    void setColor(final Color color);
}
