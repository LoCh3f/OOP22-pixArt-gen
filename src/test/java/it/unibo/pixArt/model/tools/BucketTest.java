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
    private static final int CONSTCOORD = 12;
    private static final int CONSTCOORD2 = 13;
    private static final int CONSTCOORD3 = 14;
    private static final int CONSTCOORD4 = 15;


    private void createFrameTest1() {
        Pixel tempPix;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                tempPix = new PixelBuilder.PxlBuilder().setX(i).setY(j).build();
                TEST_FRAME1.add(tempPix);
            }
        }
    }

    private void createFrameTest2() {
        Pixel tempPix;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                tempPix = new PixelBuilder.PxlBuilder().setX(i).setY(j).build();
                if ((i == CONSTCOORD && (j == CONSTCOORD || j == CONSTCOORD2 || j == CONSTCOORD3 || j == CONSTCOORD4))
                || (j == CONSTCOORD && (i == CONSTCOORD || i == CONSTCOORD2 || i == CONSTCOORD3 || i == CONSTCOORD4))) {
                        tempPix.setColor(Color.BLACK);
                }
                TEST_FRAME2.add(tempPix);
            }
        }
    }

    private void createFrameTest3() {
        Pixel tempPix;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                tempPix = new PixelBuilder.PxlBuilder().setX(i).setY(j).build();
                tempPix.setColor(Color.BLACK);
                TEST_FRAME3.add(tempPix);
            }
        }
    }

    @Test
    public void updateGridTest1() {
        this.createFrameTest1();
        Pixel pixel = new PixelBuilder.PxlBuilder().setX(2).setY(2).build();
        Set<Pixel> newSet = TEST_BUCKET.updateGrid(pixel, TEST_FRAME1);
        for (Pixel p : newSet) {
            assertEquals(Color.RED, p.getColor());
            assertTrue(p.getPosition().getX() >= 0 && p.getPosition().getX() < 16);
            assertTrue(p.getPosition().getY() >= 0 && p.getPosition().getY() < 16);
        }
    }

    @Test
    public void updateGridTest2() {
        this.createFrameTest2();
        Pixel pixel = new PixelBuilder.PxlBuilder().setX(CONSTCOORD2).setY(CONSTCOORD2).build();
        Set<Pixel> newSet = TEST_BUCKET.updateGrid(pixel, TEST_FRAME2);
        for (Pixel p : newSet) {
            assertEquals(Color.RED, p.getColor());
            assertTrue(p.getPosition().getX() >= CONSTCOORD2 && p.getPosition().getX() <= CONSTCOORD4);
            assertTrue(p.getPosition().getY() >= CONSTCOORD2 && p.getPosition().getY() <= CONSTCOORD4);
        }
    }

    @Test
    public void updateGrid3() {
        this.createFrameTest3();
        Pixel pixel = new PixelBuilder.PxlBuilder().setX(2).setY(2).build();
        pixel.setColor(Color.BLACK);
        Set<Pixel> newSet = TEST_BUCKET.updateGrid(pixel, TEST_FRAME3);
        for (Pixel p : newSet) {
            assertEquals(Color.RED, p.getColor());
            assertTrue(p.getPosition().getX() >= 0 && p.getPosition().getX() < 16);
            assertTrue(p.getPosition().getY() >= 0 && p.getPosition().getY() < 16);
        }
    }


}
