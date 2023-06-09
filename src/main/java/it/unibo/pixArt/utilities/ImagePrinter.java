package it.unibo.pixArt.utilities;

import it.unibo.pixArt.model.grid.Matrix;
import it.unibo.pixArt.model.project.Project;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;

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

    /**.
     * Print all the frames of the project
     * @param project The project that need to be printed
     * @param scale The scale of the image
     */
    public void printAllFrames(final Project project, final int scale) {

        IntStream.range(0, project.getAllFrames().size()).forEach(i -> {
            printOneFrame(project.getAllFrames().get(i),
            project.getPath() + File.separatorChar + project.getName() + i + project.getFileType(), project.getFileType(), scale);

        });
    }

    /**.
     * Print one frame of the project
     * @param pixelGrid The grid of the frame that need to be printed
     * @param path The path where the image will be saved
     * @param fileType The string of type of the file(.png, .jpg. jpeg)
     * @param scale The scale of the image
     */
    public void printOneFrame(final Matrix pixelGrid, final String path, final String fileType, final int scale) {
        this.imageSize = pixelGrid.getColumns();
        WritableImage wImg = new WritableImage(imageSize, imageSize);
        PixelWriter pWriter = wImg.getPixelWriter();

        IntStream.range(0, imageSize).forEach(x -> {
            IntStream.range(0, imageSize).forEach(y -> {
                pixelGrid.getPixels().forEach(p -> {
                    if (new Pair<Integer, Integer>(x, y).equals(p.getPosition())) {
                        pWriter.setColor(x, y, p.getColor());
                    }
                });
            });
        });

        imagePrint(wImg, fileType.toString(), path, scale);
    }

    private void imagePrint(final WritableImage wImg, final String fileFormat, final String path, final int scale) {
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

    private void scaleImage(final BufferedImage bImage, final String path, final int scale, final String fileType) {
        int newSize = scale * imageSize;
        BufferedImage newImage = new BufferedImage(newSize, newSize, bImage.getType());
        Graphics2D graphics2d = newImage.createGraphics();
        graphics2d.drawImage(bImage, 0, 0, newSize, newSize, null);
        graphics2d.dispose();
        try {
            ImageIO.write(newImage, fileType, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
