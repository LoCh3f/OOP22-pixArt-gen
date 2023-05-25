package it.unibo.pixArt.controller.game;

import java.util.List;
import java.util.Timer;

public interface GameSetupController {
    /**
     * Method to initilize the list of projects.
     */
    public void setProjects();

    /**
     * @return Method to get the list of projects.
     */
    public List<String> getProjects();

    /**
     * @return method to get all the available timers.
     */
    public List<String> getTimers();

    /**
     * @param time the index of timer for this game.
     */
    public void setTimer(final int timer);

    /**
     * @param project the index of the project for this game.
     */
    public void setProject(final int project);
}
