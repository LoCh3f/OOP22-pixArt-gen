package it.unibo.pixArt.utilities;

import it.unibo.pixArt.model.grid.Matrix;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.project.FileTypes;
import it.unibo.pixArt.model.project.Project;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

    /**
     * Print all the frames of the project
     * @param project The project that need to be printed
     */
    public void printAllFrames(Project project) {

        this.imageSize = project.getAllFrames().get(0).getColumns();
        WritableImage wImg = new WritableImage(imageSize, imageSize);
        PixelWriter pWriter = wImg.getPixelWriter();

        for (int count = 0; count < project.getAllFrames().size(); count++) {
            
            ArrayList<Pixel> pixelList = project.getAllFrames().get(count).getPixels().stream().collect(Collectors.toCollection(ArrayList::new));

            for (int x = 0; x < imageSize; x++){
                for (int y = 0; y < imageSize; y++){
                    for(var p : pixelList){
                        if(new Pair<Integer, Integer>(x, y).equals(p.getPosition())){
                            Color color = p.getColor();
                            pWriter.setColor(x, y, color);              
                        }
                    }
                }
            }
            /*if (project.getFileType().equals(".png")) {
                imagePNG(wImg, project.getPath() + File.separatorChar + project.getName() + count + ".png");
            } else {
                imageJpgOrJpeg(wImg, project.getFileType(), project.getPath() + File.separatorChar + project.getName() + count + ".png");
            }*/
            imagePrint(wImg, project.getFileType(), project.getPath() + File.separatorChar + project.getName() + count + project.getFileType());

        }
    }

    /**
     * Print one frame of the project
     * @param pixelGrid The grid of the frame that need to be printed
     * @param path The path where the image will be saved
     * @param fileType The type of the file(.png, .jpg. jpeg)
     */
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
        imagePrint(wImg, fileType.toString(), path);
    }

    private void imagePrint(WritableImage wImg, String fyleFormat, String path) {
        try {
            BufferedImage bImg = SwingFXUtils.fromFXImage(wImg, null);
            BufferedImage jpgImage = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_RGB);
            jpgImage.createGraphics().drawImage(bImg, 0, 0, null);
            ImageIO.write(jpgImage, "png", new File(path));
            //scaleJPGorJPEGImage(jpgImage, path, 16, fyleFormat.toString().replace(".", ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*private void scalePNGImage(WritableImage wImg, String path, int scale) throws IOException{        
        int newSize = scale * imageSize;
        BufferedImage inputImage = SwingFXUtils.fromFXImage(wImg, null);
        BufferedImage newImage = new BufferedImage(newSize, newSize, inputImage.getType());
        Graphics2D graphics2d = newImage.createGraphics();
        graphics2d.drawImage(inputImage, 0, 0, newSize, newSize, null);
        graphics2d.dispose();
        ImageIO.write(newImage, "png", new File(path));
        System.out.println(path);
    }

    private void scaleJPGorJPEGImage(BufferedImage bImage, String path, int scale, String fileType) throws IOException{
        int newSize = scale * imageSize;
        BufferedImage newImage = new BufferedImage(newSize, newSize, bImage.getType());
        Graphics2D graphics2d = newImage.createGraphics();
        graphics2d.drawImage(bImage, 0, 0, newSize, newSize, null);
        graphics2d.dispose();
        ImageIO.write(newImage, fileType, new File(path));
    }*/
}
