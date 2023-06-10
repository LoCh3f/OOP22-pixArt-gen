package it.unibo.pixart.model.pixel;

import it.unibo.pixart.utilities.Pair;
import javafx.scene.paint.Color;

/**
 * The implementation Class of the Pixel
 */
public class ImplPixel implements Pixel {

    private Color color;

    private final Pair<Integer, Integer> position;

    /**
     * @param x The x coordinate
     * @param y The y coordinate
     */
    protected ImplPixel(final Integer x, final Integer y) {
        this.position = new Pair<>(x, y);
        color = Color.WHITE;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Integer, Integer> getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getColor() {
        return this.color;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColor(final Color color) {
        this.color = color;
    }

    /**
     * @param pixel to compare;
     * @return true if the pixels  have the same position.
     */
    public boolean comparePixel(final Pixel pixel) {
        return this.position.equals(pixel.getPosition());
    }

    /**
     * @param obj the pixel to compare;
     * @return true if the position and the color are the same.
     */
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Pixel pixel)) {
            return false;
        }
        return comparePixel(pixel) && 0 == pixel.getColor().toString().compareTo(this.color.toString());
    }

    @Override
    public int hashCode() {
        return this.position.hashCode() + this.color.hashCode();
    }
}
