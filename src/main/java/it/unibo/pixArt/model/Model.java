package it.unibo.pixArt.model;

import it.unibo.pixArt.model.game.Game;
import it.unibo.pixArt.model.project.Project;
import it.unibo.pixArt.model.user.User;

public interface Model {

    /**
     * @return the user
     */
    User getUser();

    /**
     * @param name of the user
     * @param path where to save the files created by the user.
     */
    void setUser(final String name, final String password, final String path);

    /**
     * @return the project the user is currently working on(null if no project has been selected).
     */
    Project getProject();

    /**
     * @param project set the project the use is working on.
     */
    void setProject(final Project project);

    /**
     * @return the game
     */
    Game getGame();

    /**
     * @param game the game
     */
    void setGame(final Game game);
}