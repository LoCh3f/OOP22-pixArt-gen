package it.unibo.pixArt.model.actionhistory;

import java.util.Set;

import it.unibo.pixArt.model.pixel.Pixel;

public interface ActionHistory {
    /**
     * @param action
     * Adds an action(set of )
     */
    public void addAction(final Set<Pixel> action);
}
