package it.unibo.pixArt.model.colorstack;

import java.util.Map;
import java.util.Set;

import it.unibo.pixArt.model.pixel.Pixel;
import javafx.scene.paint.Color;

public interface ColorStack {
    /**
     * @return a map that has K->Color, V -> set of pixels of color K.
     */
    Map<Color,Set<Pixel>> getColorMap();

    /**Method to remove a specific pixel from the map
     * 
     * @param color Key
     * @param pixel Value
     */
    void removePixel(final Color color, final Pixel pixel);

    /**
     * @return float that rappresents the number of pixels left withing the map as a percentage final_size/init_size
     */
    float getPercentage();

    /**Method to check if a pixel exists.
     * 
     * @param pixel
     * @return
     */
    boolean isPresent(final Pixel pixel);

    /**
     * @return true or false weather the map is empty.
     */
    boolean isEmpty();
}
