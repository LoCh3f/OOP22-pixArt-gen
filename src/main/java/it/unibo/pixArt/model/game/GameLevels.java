package it.unibo.pixArt.model.game;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum GameLevels {

    SKULL("/games/Skull/Skull.json", "/games/Skull/Skull0.png");

    private String pathToFile;
    private String pathToImg;

    GameLevels(final String pathToFile, final String pathToImg) {
        this.pathToFile = pathToFile;
        this.pathToImg = pathToImg;
    }

    public String getPathToFile() {
        return this.pathToFile;
    }

    public String getPathToImg() {
        return this.pathToImg;
    }

    public static List<GameLevels> getAllLevels() {
        return Stream.of(values()).collect(Collectors.toList());
    }
}
