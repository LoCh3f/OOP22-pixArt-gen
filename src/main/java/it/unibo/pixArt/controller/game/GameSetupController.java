package it.unibo.pixArt.controller.game;

import java.util.List;

import it.unibo.pixArt.model.timer.TimerType;

public interface GameSetupController {
    /**
     * Method to initialize the list of projects.
     */
    public void setProjects();

    /**
     * @return Method to get the list of projects.
     */
    public List<String> getProjects();

    /**
     * @return method to get all the available timers.
     */
    public List<TimerType> getTimers();

    /**
     * @param time the index of timer for this game.
     */
    public void setTimer(final String timer);

    /**
     * @param project the index of the project for this game.
     */
    public void setProject(final int project);
}
