package it.unibo.pixart.model.grid;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.pixart.model.framestate.FrameState;
import it.unibo.pixart.model.framestate.FrameStateImpl;
import it.unibo.pixart.model.pixel.Pixel;
import it.unibo.pixart.model.pixel.PixelBuilder;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Simple implementation of Matrix.
 */
public final class PixelMatrix implements Matrix {
    private final int rows;
    private final int columns;
    private final Set<Pixel> pixels;
    private final FrameState memento = new FrameStateImpl();

    private PixelMatrix(final int rows, final int columns, final Set<Pixel> toCopy) {
        this.rows = rows;
        this.columns = columns;
        this.pixels = new HashSet<>(rows * columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                pixels.add(new PixelBuilder.PxlBuilder().setX(j).setY(i).setColor(Color.WHITE).build());
            }
        }
        if (!toCopy.isEmpty()) {
            this.setPixel(toCopy);
        }
    }

    /**
     * Builder for the matrix.
     */
    public static final class MatrixBuilder {
        private int rows;
        private int columns;
        private final Set<Pixel> toCopy = new HashSet<>();

        /**
         * Default constructor.
         */
        public MatrixBuilder() {
            this.rows = 0;
            this.columns = 0;
            this.toCopy.clear();
        }

        /**
         * @param rows the number of rows of the matrix
         * @return the builder
         */
        public MatrixBuilder setRows(final int rows) { // NOPMD
            this.rows = rows;
            return this;
        }


        /**
         * @param columns the number of columns of the matrix
         * @return the builder
         */
        public MatrixBuilder setColumns(final int columns) { //NOPMD
            this.columns = columns;
            return this;
        }

        /**
         * @param p the pixel to copy
         * @return the builder
         */
        public MatrixBuilder setToCopy(final Pixel p) { //NOPMD
            this.toCopy.add(p);
            return this;
        }

        /**
         * @return the matrix
         * @throws IllegalStateException if the rows or the columns are less than 16
         */
        public PixelMatrix build() throws IllegalStateException { //NOPMD
            if (this.rows < 16 || this.columns < 16) {
                throw new IllegalStateException("Rows and columns must be greater than 16");
            }

            return new PixelMatrix(this.rows, this.columns, this.toCopy);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Consumer<Pixel> consumer, final Pixel pixel) {
        this.pixels.forEach(consumer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRows() {
        return this.rows;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getColumns() {
        return this.columns;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings
    @Override
    public FrameState getMemento() {
        return this.memento;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Pixel> getPixels() {
        return this.pixels.stream().map(e -> new PixelBuilder
                .PxlBuilder().setColor(e.getColor())
                .setX(e.getPosition().getX()).setY(e.getPosition().getY())
                .build()).collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPixel(final Set<Pixel> pixels) {

        for (final Pixel pixel : pixels) {
            this.pixels.forEach(p -> {
                if (p.comparePixel(pixel)) {
                    p.setColor(pixel.getColor());
                }
            });
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void revert() {
        setPixel(memento.getState());
    }


}
