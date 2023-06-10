package it.unibo.pixart.model.pixel;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import it.unibo.pixart.model.pixel.Pixel;
import it.unibo.pixart.model.pixel.PixelBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PixelBuilderTest {

    private static final int CONSTCOORD = 7;
    private static final Pixel TEST_PIXEL = new PixelBuilder.PxlBuilder().setY(CONSTCOORD).setX(4).build();
    private static final Pixel TEST_PIXEL_2 = new PixelBuilder.PxlBuilder()
                                                .setY(CONSTCOORD).setX(4).setColor(Color.ALICEBLUE).build();

    @Test
    void testBuilder() {
        assertTrue(TEST_PIXEL.comparePixel(new PixelBuilder.PxlBuilder().setY(CONSTCOORD).setX(4).build()));
        assertNotEquals(TEST_PIXEL, TEST_PIXEL_2);
        assertEquals(TEST_PIXEL.getPosition().getX(), TEST_PIXEL_2.getPosition().getX());
    }

}
