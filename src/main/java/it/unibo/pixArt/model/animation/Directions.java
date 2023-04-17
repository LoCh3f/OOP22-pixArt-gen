package it.unibo.pixArt.model.animation;

public enum Directions {
    FORWARD(1),BACKWARD(-1);

    public int value;
    
    Directions(final int value) {
        this.value = value;
    }
}
