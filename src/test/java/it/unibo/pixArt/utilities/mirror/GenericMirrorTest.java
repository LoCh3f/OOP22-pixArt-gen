package it.unibo.pixArt.utilities.mirror;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.pixel.PixelBuilder;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenericMirrorTest {

    private final static Mirror<Pixel> PIXEL_MIRROR = new GenericMirror<>();

    private static final int SIZE_EXPECTED = 3;
    @Test
    void getDifference() {
        final var mirror = PIXEL_MIRROR.getDifference(
                List.of(new PixelBuilder.PxlBuilder().setX(1).setY(1).build(),
                        new PixelBuilder.PxlBuilder().setX(2).setY(2).build(),
                        new PixelBuilder.PxlBuilder().setX(3).setY(3).build(),
                        new PixelBuilder.PxlBuilder().setX(4).setY(4).build(),
                        new PixelBuilder.PxlBuilder().setX(5).setY(5).build()),
                List.of(new PixelBuilder.PxlBuilder().setX(3).setY(3).build(),
                        new PixelBuilder.PxlBuilder().setX(4).setY(4).build(),
                        new PixelBuilder.PxlBuilder().setX(6).setY(6).build(),
                        new PixelBuilder.PxlBuilder().setX(7).setY(7).build(),
                        new PixelBuilder.PxlBuilder().setX(8).setY(8).build()));


        assertEquals(SIZE_EXPECTED, mirror.size());
        assertTrue(mirror.stream().anyMatch(p -> p.equals(new PixelBuilder.PxlBuilder().setX(1).setY(1).build())));
        assertTrue(mirror.stream().anyMatch(p -> p.equals(new PixelBuilder.PxlBuilder().setX(5).setY(5).build())));
        assertTrue(mirror.stream().anyMatch(p -> p.equals(new PixelBuilder.PxlBuilder().setX(2).setY(2).build())));
    }
}