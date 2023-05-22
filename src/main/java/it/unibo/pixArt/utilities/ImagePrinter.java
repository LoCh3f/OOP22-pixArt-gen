package it.unibo.pixArt.utilities;

import it.unibo.pixArt.model.grid.Matrix;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.project.FileTypes;
import it.unibo.pixArt.model.project.Project;
import it.unibo.pixArt.model.user.User;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

public class ImagePrinter {

    private int imageSize;

    private static class LazyHolder {
        private static final ImagePrinter SINGLETON = new ImagePrinter();
    }

    private ImagePrinter() {
    }

    public static ImagePrinter getInstance() {
        return LazyHolder.SINGLETON;
    }

    public void printAllFrames(Project project) {

        this.imageSize = project.getAllFrames().get(0).getColumns();
        WritableImage wImg = new WritableImage(imageSize, imageSize);
        PixelWriter pWriter = wImg.getPixelWriter();
        System.out.println(project.getAllFrames().size());


        for (int count = 0; count < project.getAllFrames().size(); count++) {
            
            ArrayList<Pixel> pixelList = project.getAllFrames().get(count).getPixels().stream().collect(Collectors.toCollection(ArrayList::new));

            for (int x = 0; x < imageSize; x++){
                for (int y = 0; y < imageSize; y++){
                    for(var p : pixelList){
                        if(new Pair<Integer, Integer>(x, y).equals(p.getPosition())){
                            Color color = p.getColor();
                                if(color == Color.TRANSPARENT){
                                    pWriter.setColor(x, y, Color.WHITE);
                                } else {
                                    pWriter.setColor(x, y, color);                
                                }                    
                        }
                    }
                }
            }
            if (project.getFileType().equals(".png")) {
                imagePNG(wImg, project.getPath() + File.separatorChar + project.getName() + count + ".png");
            } else {
                imageJpgOrJpeg(wImg, project.getFileType(), project.getPath() + File.separatorChar + project.getName() + count + project.getFileType());
            }
        }
    }

    public void printOneFrame(Matrix pixelGrid, String path, FileTypes fileType) {
        this.imageSize = pixelGrid.getColumns();
        WritableImage wImg = new WritableImage(imageSize, imageSize);
        PixelWriter pWriter = wImg.getPixelWriter();
        ArrayList<Pixel> pixelArray = pixelGrid.getPixels().stream().collect(Collectors.toCollection(ArrayList::new));
        for (int x = 0; x < imageSize; x++) {
            for (int y = 0; y < imageSize; y++) {
                for (var p : pixelArray) {
                    if (new Pair<Integer, Integer>(x, y).equals(p.getPosition())) {
                        pWriter.setColor(x, y, p.getColor());
                    }
                }
            }
        }
        if (fileType.toString().equals(".png")) {
            imagePNG(wImg, path);
        } else {
            imageJpgOrJpeg(wImg, fileType.toString(), path);
        }
    }

    private void imagePNG(WritableImage wImg, String path) {
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(wImg, null), "png", new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ;

    private void imageJpgOrJpeg(WritableImage wImg, String fyleFormat, String path) {
        try {
            BufferedImage bImg = SwingFXUtils.fromFXImage(wImg, null);
            BufferedImage jpgImage = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_RGB);
            jpgImage.createGraphics().drawImage(bImg, 0, 0, null);
            ImageIO.write(jpgImage, fyleFormat.toString().replace(".", ""), new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
