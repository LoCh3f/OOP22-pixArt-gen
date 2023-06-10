package it.unibo.pixart.model.pixel;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import it.unibo.pixart.utilities.Pair;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ImplPixelTest {

    private static final Pixel TEST_PIXEL = new ImplPixel(0, 0);

    @Test
    void getPosition() {
        assertEquals(TEST_PIXEL.getPosition().getX(), new Pair<>(0, 0).getX());
        assertEquals(TEST_PIXEL.getPosition().getY(), new Pair<>(0, 0).getY());
    }

    @Test
    void getColor() {
        TEST_PIXEL.setColor(Color.WHITE);
        assertEquals(Color.WHITE, TEST_PIXEL.getColor());
        TEST_PIXEL.setColor(Color.BLACK);
        assertEquals(Color.BLACK, TEST_PIXEL.getColor());
    }

    @Test
    void setColor() {
        TEST_PIXEL.setColor(Color.BLACK);
        assertEquals(TEST_PIXEL.getColor(), Color.BLACK);
    }

    @Test
    void comparePixel() {
        assertTrue(TEST_PIXEL.comparePixel(new ImplPixel(0, 0)));
        assertFalse(TEST_PIXEL.comparePixel(new ImplPixel(1, 1)));
        assertTrue(new ImplPixel(1, 1).comparePixel(new ImplPixel(1, 1)));
    }
}
