package it.unibo.pixArt.model.colorstack;

import java.util.Map;
import java.util.Set;

import it.unibo.pixArt.model.pixel.Pixel;
import javafx.scene.paint.Color;

/**
 * Class that contains a Map in which K-> Color, V-> Set of pixels of color K.
 */
public interface ColorStack {
    /**
     * @return a map that has K->Color, V -> set of pixels of color K.
     */
    Map<Color, Set<Pixel>> getColorMap();

    /**Method to remove a specific pixel from the map.
     * 
     * @param color Key
     * @param pixel Value
     */
    void removePixel(Color color, Pixel pixel);

    /**
     * @return float that rappresents the number of pixels left withing the map as a percentage final_size/init_size
     */
    double getPercentage();

    /**Method to check if a pixel exists.
     * 
     * @param pixel
     * @return weather the pixel exists within the map.
     */
    boolean isPresent(Pixel pixel);

    /**
     * @return true or false weather the map is empty.
     */
    boolean isEmpty();
}
