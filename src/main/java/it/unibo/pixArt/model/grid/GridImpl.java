package it.unibo.pixArt.model.grid;

import it.unibo.pixArt.model.pixel.ImplPixel;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.utilities.Pair;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class GridImpl implements PixelGrid {

    private final Set<Pixel> pixels;

    private final Integer row;

    private final Integer column;

    protected GridImpl(final Integer row, final Integer column) {
        this.row = row;
        this.column = column;
        pixels = new HashSet<>(row * column);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                pixels.add(new ImplPixel(i, j));
            }
        }
    }

    @Override
    public Set<Pixel> getPixels() {
        return new HashSet<>(this.pixels);
    }

    @Override
    public void delete(final Pair<Integer, Integer> pixel) {
        this.update(p -> {
            if (p.getPosition().equals(pixel)) {
                p.setColor(Color.WHITE);
            }
        });
    }

    @Override
    public void deleteAll() {
        this.update(p -> p.setColor(Color.WHITE));
    }

    @Override
    public void draw(final Pair<Integer, Integer> position, final Color color) {
        this.update(p -> {
            if (p.getPosition().equals(position)) {
                p.setColor(color);
            }
        });
    }

    /**
     * @param consumer the consumer to apply to each pixel
     */
    private void update(final Consumer<Pixel> consumer) {
        this.pixels.forEach(consumer);

    }

    public Integer getColumn() {
        return column;
    }

    public Integer getRow() {
        return row;
    }
}
