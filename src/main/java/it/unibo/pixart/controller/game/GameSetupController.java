package it.unibo.pixart.controller.game;

import java.util.List;

import it.unibo.pixart.model.game.GameType;
import it.unibo.pixart.model.timer.TimerType;

/**
 * Controller for GameSetupView.
 */
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
     * @param timer the index of timer for this game.
     */
    void setTimer(String timer);

    /**
     * @param project the index of the project for this game.
     */
    void setProject(int project);

    /**
     * @return the list of all game types.
     */
    List<GameType> getGameTypes();

    /**
     * @param type the game type.
     */
    void setGameType(String type);

    /**
     * Method to set a game instance.
     */
    void setGame();
}
