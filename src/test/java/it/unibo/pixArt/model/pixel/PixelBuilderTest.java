package it.unibo.pixArt.model.pixel;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PixelBuilderTest {

    private final static Pixel TEST_PIXEL = new PixelBuilder.PxlBuilder().setY(7).setX(4).build();
    private final static Pixel TEST_PIXEL_2 = new PixelBuilder.PxlBuilder().setY(7).setX(4).setColor(Color.ALICEBLUE).build();

    @Test
    void testBuilder() {
        assertTrue(TEST_PIXEL.comparePixel(new PixelBuilder.PxlBuilder().setY(7).setX(4).build()));
        assertNotEquals(TEST_PIXEL, TEST_PIXEL_2);
        assertEquals(TEST_PIXEL.getPosition().getX(), TEST_PIXEL_2.getPosition().getX());
    }

}