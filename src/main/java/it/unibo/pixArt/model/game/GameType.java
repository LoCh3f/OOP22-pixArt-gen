package it.unibo.pixArt.model.game;


public enum GameType {
    MIRROR("MIRROR"),STACK("STACK");
    
    private String name;
    GameType(final String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
