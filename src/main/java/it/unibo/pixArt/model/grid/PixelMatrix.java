package it.unibo.pixArt.model.grid;

import it.unibo.pixArt.model.pixel.ImplPixel;
import it.unibo.pixArt.model.pixel.Pixel;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class PixelMatrix {

    private final int rows;

    private final int columns;

    private final Set<Pixel> pixels;

    private PixelMatrix(final int rows, final int columns) {
        this.rows = rows;
        this.columns = columns;
        this.pixels = new HashSet<>(rows * columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                pixels.add(new ImplPixel(i, j));
            }
        }
    }

    public static class MatrixBuilder {

        private int rows;
        private int columns;

        public MatrixBuilder() {
            this.rows = 0;
            this.columns = 0;
        }

        public MatrixBuilder setRows(final int rows) {
            this.rows = rows;
            return this;
        }

        public MatrixBuilder setColumns(final int columns) {
            this.columns = columns;
            return this;
        }

        public PixelMatrix build() throws  IllegalStateException {
            if (this.rows < 16 || this.columns < 16 ) {
                throw new IllegalStateException("Rows and columns must be greater than 16");
            }

            return new PixelMatrix(this.rows, this.columns);
        }

    }

    public void update(final Consumer<Pixel> consumer, Pixel pixel) {
        this.pixels.forEach(consumer);
    }

    public Set<Pixel> getPixels() {
        return new HashSet<>(this.pixels);
    }

}
