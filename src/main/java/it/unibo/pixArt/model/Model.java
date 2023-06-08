package it.unibo.pixArt.model;
import it.unibo.pixArt.model.game.Game;
import it.unibo.pixArt.model.project.Project;
import it.unibo.pixArt.model.user.User;

/**
 * interface for Model.
 */
public interface Model {

    /**
     * @return the user
     */
    User getUser();

    /**
     * @param name of the user
     * @param path where to save the files created by the user.
     * @param password user's password.
     */
    void setUser(String name, String password, String path);

    /**
     * @return the project the user is currently working on(null if no project has been selected).
     */
    Project getProject();

    /**
     * @param project set the project the use is working on.
     */
    void setProject(Project project);

    /**
     * @return the game.
     */
    Game getGame();

    /**
     * @param game the game.
     */
    void setGame(Game game);
}