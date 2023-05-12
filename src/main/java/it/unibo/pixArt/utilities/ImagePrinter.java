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
import it.unibo.pixArt.model.user.User;
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

    public void printImage(Project project, User user){
        this.imageSize = project.getAllFrames().get(0).getColumns();


        WritableImage wImg = new WritableImage(imageSize, imageSize);
        PixelWriter pWriter = wImg.getPixelWriter();

        for(int count=0; count > project.getAllFrames().size(); count++){
        Iterator<Pixel> pixelIterator = project.getAllFrames().get(count).getPixels().iterator();

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
        if(project.getFileType().equals("png")){
            imagePNG(wImg, count);
        }else{
            imageJpgOrJpeg(wImg, project.getFileType(), count);
        }      
    }
    } 
    
    private void imagePNG(WritableImage wImg, int numImg){
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(wImg, null), "png", new File("image" + numImg + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    private void imageJpgOrJpeg(WritableImage wImg, String fyleFormat, int numImg){
        try {
            BufferedImage bImg = SwingFXUtils.fromFXImage(wImg, null);
            BufferedImage jpgImage = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_RGB);
            jpgImage.createGraphics().drawImage(bImg, 0, 0, null);
            ImageIO.write(jpgImage, fyleFormat, new File("image" + numImg + fyleFormat));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}