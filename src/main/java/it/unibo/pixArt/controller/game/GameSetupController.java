package it.unibo.pixArt.controller.game;

import java.util.List;

import it.unibo.pixArt.model.game.GameType;
import it.unibo.pixArt.model.timer.TimerType;

public interface GameSetupController {
    /**
     * Method to initialize the list of projects.
     */
    void setProjects();

    /**
     * @return Method to get the list of projects.
     */
    List<String> getProjects();

    /**
     * @return method to get all the available timers.
     */
    List<TimerType> getTimers();

    /**
     * @param time the index of timer for this game.
     */
    void setTimer(final String timer);

    /**
     * @param project the index of the project for this game.
     */
    void setProject(final int project);

    /**
     * @return the list of all game types.
     */
    List<GameType> getGameTypes();

    /**
     * @param type the game type.
     */
    void setGameType(final String type);

    /**
     * Method to set a game instance.
     */
    void setGame();
}
