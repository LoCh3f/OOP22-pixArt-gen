package it.unibo.pixArt.model.animation;

public enum Directions {
    FORWARD("Forward",1),BACKWARD("Backward",-1);

    private final String name;
    private final int value;
    
    /**
     * @param name of the direction
     * @param value associated to the direction(1 or -1)
     */
    Directions(final String name, final int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * @return the value of the direction
     */
    public int getValue() {
        return this.value;
    }

    /**
     * @return the name of the direction
     */
    public String getName() {
        return this.name;
    }
}
