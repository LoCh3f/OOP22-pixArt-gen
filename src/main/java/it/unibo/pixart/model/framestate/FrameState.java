package it.unibo.pixart.model.framestate;

import java.util.Set;

import it.unibo.pixart.model.pixel.Pixel;
/**
 * A class that memorizes different states of a Matrix.
 */
public interface FrameState {
    /**
     * @param snapshot Adds a new state in the state stack.
     * 
     */
    void setState(Set<Pixel> snapshot);

    /**
     * @return Returns the previous state of the frame and pops it from the stack.
     */
    Set<Pixel> getState();

    /**
     * @return Returns a copy of the previous state of the frame, without poping it from the stack.
     */
    Set<Pixel> peekState();

    /**
     * @return the size of the stack.
     */
    int size();

    /**
     * Method to empty the stack.
     */
    void emptyStack();
}
