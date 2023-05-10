package it.unibo.pixArt.utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.text.html.ImageView;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.project.Project;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class ImagePrinter {

    private int imageSize;

    private static class LazyHolder{
        private static final ImagePrinter SINGLETON = new ImagePrinter();
    }

    private ImagePrinter(){
    }

    public static ImagePrinter getInstance(){
        return LazyHolder.SINGLETON;
    }

    public void printImage(Project project){
        this.imageSize = project.getAllFrames().get(0).getColumns();


        WritableImage wImg = new WritableImage(imageSize, imageSize);
        PixelWriter pWriter = wImg.getPixelWriter();
        Iterator<Pixel> pixelIterator = project.getAllFrames().get(0).getPixels().iterator();

        for (int x = 0; x < imageSize; x++){
            for (int y = 0; y < imageSize; y++){
                while(pixelIterator.hasNext()){
                    Pixel actPixel = pixelIterator.next();
                    if(new Pair<Integer, Integer>(x, y).equals(actPixel.getPosition())){
                        Color color = actPixel.getColor();
                        pWriter.setColor(x, y, color);
                    }
                }
            }
        }

        switch(project.getFileType()){
            case ".png": imagePNG(wImg);
            break;
        }
        
       //Save JPG image:
       /*try {
            BufferedImage jpgImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
            jpgImage.createGraphics().drawImage(jpgImage, 0, 0, null);
            ImageIO.write(jpgImage, "jpg", new File("image.jpg"));            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //Save JPEG Image:

        try {
            BufferedImage jpegImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
            jpegImage.createGraphics().drawImage(img, 0, 0, null);
            ImageIO.write(jpegImage, "jpeg", new File("image.jpeg"));            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

        //Save PNG Image:

        
    } 
    private void imagePNG(WritableImage wImg){
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(wImg, null), "png", new File("image.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    };

}
