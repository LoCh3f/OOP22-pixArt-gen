package it.unibo.pixArt.model.framestate;

import java.util.Set;

import it.unibo.pixArt.model.pixel.Pixel;

public interface FrameState {
    /**
     * @param action Adds a new state in the state stack
     * 
     */
    public void setState(final Set<Pixel> snapshot);

    /**
     * @return Returns the previous state of the frame and pops it from the stack.
     */
    public Set<Pixel> getState();

    /**
     * @return Returns a copy of the previous state of the frame, without poping it from the stack.
     */
    public Set<Pixel> peekState();

  
    public int size();

    /**
     * Method to empty the stack.
     */
    public void emptyStack();
}