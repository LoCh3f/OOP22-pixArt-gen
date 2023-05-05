package it.unibo.pixArt.model.pixel;

import javafx.scene.paint.Color;

public class PixelBuilder extends ImplPixel {

    private PixelBuilder(final Integer x, final Integer y, final Color color) {
        super(x, y);
        setColor(color);
    }

    public static class PxlBuilder {
        private Integer x = -1;
        private Integer y = -1;

        private Color color = Color.TRANSPARENT;

        PxlBuilder setX(final Integer x) {
            this.x = x;
            return this;
        }

        PxlBuilder setY(final Integer y) {
            this.y = y;
            return this;
        }

        PxlBuilder setColor(final Color color) {
            this.color = color;
            return this;
        }

        PixelBuilder build() throws IllegalStateException {
            if (this.x < -1 || this.y < -1) {
                throw new IllegalStateException(" X and Y can't be < 0");
            }
            return new PixelBuilder(this.x, this.y, this.color);
        }
    }
}
