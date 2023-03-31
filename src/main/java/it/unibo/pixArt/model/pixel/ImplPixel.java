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
}
