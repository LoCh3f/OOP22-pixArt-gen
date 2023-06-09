package it.unibo.pixArt.model.game;

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
    SHREK("/games/Shrek/Shrek.json", "/games/Shrek/Shrek0.png");

    private String pathToFile;
    private String pathToImg;

    GameLevels(final String pathToFile, final String pathToImg) {
        this.pathToFile = pathToFile;
        this.pathToImg = pathToImg;
    }

    public String getPathToFile() throws IOException {
        return newPathToFile();
    }

    public String getPathToImg() {
        return this.pathToImg;
    }

    public static List<GameLevels> getAllLevels() {
        return Stream.of(values()).collect(Collectors.toList());
    }

    private String newPathToFile() throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream(this.pathToFile);
        new File(System.getProperty("user.home") + File.separatorChar + "PixArtDatas").mkdir();
        File tempFile = new File(System.getProperty("user.home") + "/PixArtDatas/tempGame.json");

        Files.copy(inputStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        inputStream.close();
        return tempFile.getAbsolutePath();
    }
}
