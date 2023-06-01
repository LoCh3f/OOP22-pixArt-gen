package it.unibo.pixArt.model.game;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum GameType {
    MIRROR("Mirror", "A game in which the user has to copy a pixel image, trying to copy all the pixels perfectly"),
    STACK("Stack", "A game in which the user has to paint the grid with the color associated with the number write on the pixel");
    
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

    public List<GameType> getGameTypes(){
        return Stream.of(values()).collect(Collectors.toList());
    }
}
