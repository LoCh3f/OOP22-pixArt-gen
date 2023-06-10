package it.unibo.pixart.utilities.mirror;

import org.junit.jupiter.api.Test;

import it.unibo.pixart.model.pixel.Pixel;
import it.unibo.pixart.model.pixel.PixelBuilder;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GenericMirrorTest {

    private static final Mirror<Pixel> PIXEL_MIRROR = new GenericMirror<>();
    private static final int CONSTCOORD = 5;
    private static final int CONSTCOORD2 = 6;
    private static final int CONSTCOORD3 = 7;

    private static final int SIZE_EXPECTED = 3;
    @Test
    void getDifference() {
        final var mirror = PIXEL_MIRROR.getDifference(
                List.of(new PixelBuilder.PxlBuilder().setX(1).setY(1).build(),
                        new PixelBuilder.PxlBuilder().setX(2).setY(2).build(),
                        new PixelBuilder.PxlBuilder().setX(3).setY(3).build(),
                        new PixelBuilder.PxlBuilder().setX(4).setY(4).build(),
                        new PixelBuilder.PxlBuilder().setX(CONSTCOORD).setY(CONSTCOORD).build()),
                List.of(new PixelBuilder.PxlBuilder().setX(3).setY(3).build(),
                        new PixelBuilder.PxlBuilder().setX(4).setY(4).build(),
                        new PixelBuilder.PxlBuilder().setX(CONSTCOORD2).setY(CONSTCOORD2).build(),
                        new PixelBuilder.PxlBuilder().setX(CONSTCOORD3).setY(CONSTCOORD3).build(),
                        new PixelBuilder.PxlBuilder().setX(8).setY(8).build()));


        assertEquals(SIZE_EXPECTED, mirror.size());
        assertTrue(mirror.stream().anyMatch(p -> p.equals(new PixelBuilder.PxlBuilder().setX(1).setY(1).build())));
        assertTrue(mirror.stream().anyMatch(p -> 
                                            p.equals(new PixelBuilder.PxlBuilder().setX(CONSTCOORD).setY(CONSTCOORD).build())));
        assertTrue(mirror.stream().anyMatch(p -> p.equals(new PixelBuilder.PxlBuilder().setX(2).setY(2).build())));
    }
}
