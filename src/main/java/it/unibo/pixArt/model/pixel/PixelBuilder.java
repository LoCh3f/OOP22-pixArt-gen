package it.unibo.pixArt.model.pixel;

import javafx.scene.paint.Color;


/**
 * This class is used to build a pixel.
 */
public final class PixelBuilder extends ImplPixel {

    private PixelBuilder(final Integer x, final Integer y, final Color color) {
        super(x, y);
        setColor(color);
    }

    public static class PxlBuilder {
        private Integer x = -1;
        private Integer y = -1;

        private Color color = Color.TRANSPARENT;

        /**
         * @param x the x coordinate of the pixel
         * @return the builder
         */
        public PxlBuilder setX(final Integer x) {
            this.x = x;
            return this;
        }

        /**
         * @param y the y coordinate of the pixel
         * @return the builder
         */
        public PxlBuilder setY(final Integer y) {
            this.y = y;
            return this;
        }

        /**
         * @param color the color of the pixel
         * @return the builder
         */
        public PxlBuilder setColor(final Color color) {
            this.color = color;
            return this;
        }

        /**
         * @return the pixel
         * @throws IllegalStateException if x or y are < 0
         */
        public PixelBuilder build() throws IllegalStateException {
            if (this.x < -1 || this.y < -1) {
                throw new IllegalStateException(" X and Y can't be < 0");
            }
            return new PixelBuilder(this.x, this.y, this.color);
        }
    }
}
