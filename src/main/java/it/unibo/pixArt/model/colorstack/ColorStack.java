package it.unibo.pixArt.model.colorstack;

import java.util.Map;
import java.util.Set;

import it.unibo.pixArt.model.pixel.Pixel;
import javafx.scene.paint.Color;

public interface ColorStack {
    /**
     * @return 
     */
    public Map<Color,Set<Pixel>> getColorMap();

    /**
     * @param color
     * @param pixel
     */
    public void removePixel(final Color color, final Pixel pixel);
}
