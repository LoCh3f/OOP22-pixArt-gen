package it.unibo.pixart.model.game;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Enum for all the available game levels.
 */
public enum GameLevels {

    SKULL("/games/Skull/Skull.json", "/games/Skull/Skull0.png"),
    EGG("/games/Egg/Egg.json", "/games/Egg/Egg0.png"),
    SHREK("/games/Shrek/Shrek.json", "/games/Shrek/Shrek0.png");

    private String pathToFile;
    private String pathToImg;

    /**
     * Constructor.
     * @param pathToFile
     * @param pathToImg
     */
    GameLevels(final String pathToFile, final String pathToImg) {
        this.pathToFile = pathToFile;
        this.pathToImg = pathToImg;
    }

    /**
     * Method to get the path to file.
     * @return
     * @throws IOException
     */
    public String getPathToFile() throws IOException {
        return newPathToFile();
    }

    /**
     * @return pathToImg
     */
    public String getPathToImg() {
        return this.pathToImg;
    }

    /**
     * @return list of all available levels.
     */
    public static List<GameLevels> getAllLevels() {
        return Stream.of(values()).collect(Collectors.toList());
    }

    private String newPathToFile() throws IOException {
        final InputStream inputStream = this.getClass().getResourceAsStream(this.pathToFile);
        new File(System.getProperty("user.home") + File.separatorChar + "PixArtDatas").mkdir();
        final File tempFile = new File(System.getProperty("user.home") + "/PixArtDatas/tempGame.json");

        Files.copy(inputStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        inputStream.close();
        return tempFile.getAbsolutePath();
    }
}
