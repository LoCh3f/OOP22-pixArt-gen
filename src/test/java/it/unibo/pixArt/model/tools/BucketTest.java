package it.unibo.pixArt.model.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.pixel.PixelBuilder;
import it.unibo.pixArt.model.tool.AbstractTool;
import it.unibo.pixArt.model.tool.fillTools.Bucket;
import javafx.scene.paint.Color;


class BucketTest {

    private static final AbstractTool TEST_BUCKET = new Bucket(Color.RED);
    private static final Set<Pixel> TEST_FRAME = new HashSet<>();

    private void createFrameTest1() {
        Pixel tempPix;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                tempPix = new PixelBuilder.PxlBuilder().setX(i).setY(j).build();
                TEST_FRAME.add(tempPix);
            }
        }
    }

    private void createFrameTest2() {
        Pixel tempPix;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                tempPix = new PixelBuilder.PxlBuilder().setX(i).setY(j).build();
                if ((i == 12 && (j == 12 || j == 13 || j == 14 || j == 15))
                || (j == 12 && (i == 12 || i == 13 || i == 14 || i == 15))) {
                        tempPix.setColor(Color.BLACK);
                }
                TEST_FRAME.add(tempPix);
            }
        }
    }

    private void createFrameTest3() {
        Pixel tempPix;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                tempPix = new PixelBuilder.PxlBuilder().setX(i).setY(j).build();
                tempPix.setColor(Color.BLACK);
                TEST_FRAME.add(tempPix);
            }
        }
    }

    @Test
    public void updateGridTest1() {
        this.createFrameTest1();
        Pixel pixel = new PixelBuilder.PxlBuilder().setX(2).setY(2).build();
        Set<Pixel> newSet = TEST_BUCKET.updateGrid(pixel, TEST_FRAME);
        for (Pixel p : newSet) {
            assertEquals(Color.RED, p.getColor());
            assertTrue(p.getPosition().getX() >= 0 && p.getPosition().getX() < 16);
            assertTrue(p.getPosition().getY() >= 0 && p.getPosition().getY() < 16);
        }
    }

    @Test
    public void updateGridTest2() {
        this.createFrameTest2();
        Pixel pixel = new PixelBuilder.PxlBuilder().setX(13).setY(13).build();
        Set<Pixel> newSet = TEST_BUCKET.updateGrid(pixel, TEST_FRAME);
        for (Pixel p : newSet) {
            assertEquals(Color.RED, p.getColor());
            assertTrue(p.getPosition().getX() >= 13 && p.getPosition().getX() <= 15);
            assertTrue(p.getPosition().getY() >= 13 && p.getPosition().getY() <= 15);
        }
    }

    @Test
    public void updateGrid3() {
        this.createFrameTest3();
        Pixel pixel = new PixelBuilder.PxlBuilder().setX(2).setY(2).build();
        pixel.setColor(Color.BLACK);
        Set<Pixel> newSet = TEST_BUCKET.updateGrid(pixel, TEST_FRAME);
        for (Pixel p : newSet) {
            assertEquals(Color.RED, p.getColor());
            assertTrue(p.getPosition().getX() >= 0 && p.getPosition().getX() < 16);
            assertTrue(p.getPosition().getY() >= 0 && p.getPosition().getY() < 16);
        }
    }


}
