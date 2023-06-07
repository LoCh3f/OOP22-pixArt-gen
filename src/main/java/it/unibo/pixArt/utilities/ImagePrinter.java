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

public final class ImagePrinter {

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
     * @param scale The scale of the image
     */
    public void printAllFrames(final Project project, final int scale) {

        this.imageSize = project.getAllFrames().get(0).getColumns();
        WritableImage wImg = new WritableImage(imageSize, imageSize);
        PixelWriter pWriter = wImg.getPixelWriter();

        for (int count = 0; count < project.getAllFrames().size(); count++) {

            for (int x = 0; x < imageSize; x++) {
                for (int y = 0; y < imageSize; y++) {
                    for (var p : project.getAllFrames().get(count).getPixels()) {
                        if (new Pair<Integer, Integer>(x, y).equals(p.getPosition())) {
                            Color color = p.getColor();
                            pWriter.setColor(x, y, color);
                        }
                    }
                }
            }
            imagePrint(wImg, project.getFileType(), project.getPath() + File.separatorChar + project.getName() + count + project.getFileType(), scale);

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
        imagePrint(wImg, fileType.toString(), path, 4);
    }

    private void imagePrint(WritableImage wImg, String fileFormat, String path, int scale) {
        try {
            BufferedImage bImg = SwingFXUtils.fromFXImage(wImg, null);
            BufferedImage jpgImage = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_RGB);
            jpgImage.createGraphics().drawImage(bImg, 0, 0, null);
            ImageIO.write(jpgImage, fileFormat.toString().replace(".", ""), new File(path));
            scaleImage(jpgImage, path, scale, fileFormat.toString().replace(".", ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void scaleImage(BufferedImage bImage, String path, int scale, String fileType) throws IOException{
        int newSize = scale * imageSize;
        BufferedImage newImage = new BufferedImage(newSize, newSize, bImage.getType());
        Graphics2D graphics2d = newImage.createGraphics();
        graphics2d.drawImage(bImage, 0, 0, newSize, newSize, null);
        graphics2d.dispose();
        ImageIO.write(newImage, fileType, new File(path));
    }
}
