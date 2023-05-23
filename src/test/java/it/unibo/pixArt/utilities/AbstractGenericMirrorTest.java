package it.unibo.pixArt.utilities;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.pixel.PixelBuilder;
import it.unibo.pixArt.utilities.mirror.GenericMirror;
import it.unibo.pixArt.utilities.mirror.Mirror;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AbstractGenericMirrorTest {
    private final static Mirror<Pixel> PIXEL_MIRROR = new GenericMirror<>();

    @Test
    void getDifference() {
        final var mirror = PIXEL_MIRROR.getDifference(
                List.of(new PixelBuilder.PxlBuilder().setX(1).setY(1).build(),
                        new PixelBuilder.PxlBuilder().setX(2).setY(2).build()),
                List.of(new PixelBuilder.PxlBuilder().setX(3).setY(3).build(),
                        new PixelBuilder.PxlBuilder().setX(4).setY(4).build()));

        assertEquals(2, mirror.size());
        assertTrue(mirror.stream().toList().get(0).comparePixel(
                new PixelBuilder.PxlBuilder().setX(1).setY(1).build()));
        assertTrue(mirror.stream().toList().get(1).comparePixel(
                new PixelBuilder.PxlBuilder().setX(2).setY(2).build()));
        assertNotEquals(mirror.stream().toList().get(0),
                new PixelBuilder.PxlBuilder().setX(1).setY(1).setColor(Color.ALICEBLUE).build());
    }
}