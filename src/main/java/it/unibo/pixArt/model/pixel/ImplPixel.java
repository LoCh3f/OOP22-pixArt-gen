package it.unibo.pixArt.model.pixel;

import it.unibo.pixArt.utilities.Pair;
import javafx.scene.paint.Color;

public class ImplPixel implements Pixel {

    private Color color;

    private final Pair<Integer, Integer> position;

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
        final var pixel = (Pixel) obj;
        return comparePixel(pixel) && 0 == pixel.getColor().toString().compareTo(this.color.toString());
    }
}
