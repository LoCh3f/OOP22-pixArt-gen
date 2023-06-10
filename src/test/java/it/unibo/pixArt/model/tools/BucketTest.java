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
    private static final Set<Pixel> TEST_FRAME1 = new HashSet<>();
    private static final Set<Pixel> TEST_FRAME2 = new HashSet<>();
    private static final Set<Pixel> TEST_FRAME3 = new HashSet<>();
    private static final int FRAME_DIM = 16;


    private void createFrameTest1() {
        Pixel tempPix;
        for (int i = 0; i < FRAME_DIM; i++) {
            for (int j = 0; j < FRAME_DIM; j++) {
                tempPix = new PixelBuilder.PxlBuilder().setX(i).setY(j).build();
                TEST_FRAME1.add(tempPix);
            }
        }
    }

    private void createFrameTest2() {
        final int x = 12;
        final int y = 12;

        Pixel tempPix;
        for (int i = 0; i < FRAME_DIM; i++) {
            for (int j = 0; j < FRAME_DIM; j++) {
                tempPix = new PixelBuilder.PxlBuilder().setX(i).setY(j).build();
                if (i == x && (j == y || j == y + 1 || j == y + 2 || j == y + 3)
                || j == y && (i == x || i == x + 1 || i == x + 2 || i == x + 3)) {
                        tempPix.setColor(Color.BLACK);
                }
                TEST_FRAME2.add(tempPix);
            }
        }
    }

    private void createFrameTest3() {
        Pixel tempPix;
        for (int i = 0; i < FRAME_DIM; i++) {
            for (int j = 0; j < FRAME_DIM; j++) {
                tempPix = new PixelBuilder.PxlBuilder().setX(i).setY(j).build();
                tempPix.setColor(Color.BLACK);
                TEST_FRAME3.add(tempPix);
            }
        }
    }

    @Test
    void updateGridTest1() {
        this.createFrameTest1();
        final int x = 2;
        final int y = 2;
        final Pixel pixel = new PixelBuilder.PxlBuilder().setX(x).setY(y).build();
        final Set<Pixel> newSet = TEST_BUCKET.updateGrid(pixel, TEST_FRAME1);
        for (final Pixel p : newSet) {
            assertEquals(Color.RED, p.getColor());
            assertTrue(p.getPosition().getX() >= 0 && p.getPosition().getX() < 16);
            assertTrue(p.getPosition().getY() >= 0 && p.getPosition().getY() < 16);
        }
    }

    @Test
    void updateGridTest2() {
        this.createFrameTest2();
        final int x = 13;
        final int y = 13;
        final Pixel pixel = new PixelBuilder.PxlBuilder().setX(x).setY(y).build();
        final Set<Pixel> newSet = TEST_BUCKET.updateGrid(pixel, TEST_FRAME2);
        for (final Pixel p : newSet) {
            assertEquals(Color.RED, p.getColor());
            assertTrue(p.getPosition().getX() >= x && p.getPosition().getX() <= x + 2);
            assertTrue(p.getPosition().getY() >= y && p.getPosition().getY() <= y + 2);
        }
    }

    @Test
    void updateGrid3() {
        this.createFrameTest3();
        final int x = 2;
        final int y = 2;
        final Pixel pixel = new PixelBuilder.PxlBuilder().setX(x).setY(y).build();
        pixel.setColor(Color.BLACK);
        final Set<Pixel> newSet = TEST_BUCKET.updateGrid(pixel, TEST_FRAME3);
        for (final Pixel p : newSet) {
            assertEquals(Color.RED, p.getColor());
            assertTrue(p.getPosition().getX() >= 0 && p.getPosition().getX() < 16);
            assertTrue(p.getPosition().getY() >= 0 && p.getPosition().getY() < 16);
        }
    }


}
