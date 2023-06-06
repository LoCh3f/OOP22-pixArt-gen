package it.unibo.pixArt.model.game;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum GameType {
    STACK("Stack", "A game in which there is a number associated to every available color, and the user has to color each button with the correct color. Every button contains the associated number. The user has to finish before the time ends, and gets a score based on the number of the buttons colored correctly."),
    MIRROR("Mirror", "A game in which the user has to replicate an image displayed on the screen, before the timer ends. The user gets a score based on how similar the grid is to the immage.");
    
    private String name;
    private String description;
    GameType(final String name, final String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public static List<GameType> getGameTypes(){
        return Stream.of(values()).collect(Collectors.toList());
    }
}
