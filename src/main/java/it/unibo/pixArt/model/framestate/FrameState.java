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
     * @return Returns the previous state of the frame.
     */
    public Set<Pixel> getState();
}