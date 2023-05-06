package it.unibo.pixArt.controller.animation;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
/**
 * An enum containing the possible directions for an animation.
 */
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

    /**
     * @return list of directions as string.
     */
    public static List<Directions> getListDirections() {
       return Stream.of(values()).toList();
    }
}
