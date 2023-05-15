package it.unibo.pixArt.utilities;

import it.unibo.pixArt.model.pixel.ImplPixel;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.pixel.PixelBuilder;
import it.unibo.pixArt.utilities.mirror.AbstractGenericMirror;
import it.unibo.pixArt.utilities.mirror.Mirror;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AbstractGenericMirrorTest {
    private final static Mirror<Pixel> testMirror = new AbstractGenericMirror<>();

    @Test
    void getDifference() {
        final var mirror = testMirror.getDifference(List.of(new ImplPixel(1, 1), new ImplPixel(2, 2)), List.of(new ImplPixel(3, 3), new ImplPixel(4, 4)));
        assertEquals(2, mirror.size());
        assertTrue(mirror.stream().toList().get(0).comparePixel(new ImplPixel(1, 1)));
        assertTrue(mirror.stream().toList().get(1).comparePixel(new ImplPixel(2, 2)));
        assertNotEquals(mirror.stream().toList().get(0), new PixelBuilder.PxlBuilder().setX(1).setY(1).setColor(Color.ALICEBLUE).build());
    }
}