package it.unibo.pixArt.model.pixel;

import it.unibo.pixArt.utilities.Pair;
import javafx.scene.paint.Color;

public class ImplPixel implements Pixel {

    private Color color;

    private final Pair<Integer, Integer> position;

    public ImplPixel(final Integer x, final Integer y) {
        this.position = new Pair<>(x, y);
        this.setColor(Color.WHITE);
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return this.position;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void setColor(final Color color) {
        this.color = color;
    }

    /**
     * @param pixel to compare
     * @return true if the pixels  have the same position
     */
    public boolean comparePixel(final Pixel pixel) {
        return this.position.equals(pixel.getPosition());
    }

    @Override
    public boolean equals(Object obj) {
        final var pixel = (Pixel) obj;
        return comparePixel(pixel) && 0 == pixel.getColor().toString().compareTo(this.color.toString());
    }
}
