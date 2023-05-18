package it.unibo.pixArt.model.game;

import it.unibo.pixArt.model.project.Project;

public interface Game {
    /**
     * @return the current project of the game.
     */
    public Project getTestProject();

    /**
     * @return get the game's type.
     */
    public GameType getGameType();
}
