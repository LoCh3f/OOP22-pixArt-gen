package it.unibo.pixArt.model.game;

import java.util.List;
import java.util.stream.Stream;

public enum GameLevels {

    FIRST("/games/Project1/Project1.json", "/games/Project1/Project10.png");

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
        return Stream.of(values()).toList();
    }
}
