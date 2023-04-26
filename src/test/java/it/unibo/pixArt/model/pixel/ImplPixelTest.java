package it.unibo.pixArt.model.pixel;

import it.unibo.pixArt.utilities.Pair;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ImplPixelTest {

    private  final  static  Pixel TEST_PIXEL = new ImplPixel(0, 0);

    @Test
    void getPosition() {
        assertEquals(TEST_PIXEL.getPosition().getX(), new Pair<>(0, 0).getX());
        assertEquals(TEST_PIXEL.getPosition().getY(), new Pair<>(0, 0).getY());
    }

    @Test
    void getColor() {
        assertEquals(TEST_PIXEL.getColor(), Color.WHITE);
        TEST_PIXEL.setColor(Color.BLACK);
        assertEquals(TEST_PIXEL.getColor(), Color.BLACK);
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