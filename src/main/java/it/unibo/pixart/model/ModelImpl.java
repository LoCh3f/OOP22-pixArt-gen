package it.unibo.pixart.model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.pixart.model.game.Game;
import it.unibo.pixart.model.project.Project;
import it.unibo.pixart.model.user.User;
import it.unibo.pixart.model.user.UserImpl;

/**
 * Implementation of Model interface.
 */
public final class ModelImpl implements Model {
    private Project project;
    private User user;
    private Game game;

    /**
     * Constructor for ModelImpl.
     *
     * @param name
     * @param password
     * @param path
     * @param project
     * @param game
     */
    @SuppressFBWarnings
    public ModelImpl(final String name, final String password, final String path, final Project project, final Game game) {
        this.user = new UserImpl(name, password, path);
        this.project = project;
        this.game = game;
    }

    @SuppressFBWarnings
    @Override
    public User getUser() {
        return this.user;
    }

    @Override
    public void setUser(final String name, final String password, final String path) {
        this.user = new UserImpl(name, password, path);
    }

    @SuppressFBWarnings
    @Override
    public Project getProject() {
        return this.project;
    }

    @SuppressFBWarnings
    @Override
    public void setProject(final Project project) {
        this.project = project;
    }

    @Override
    public Game getGame() {
        return this.game;
    }

    @Override
    public void setGame(final Game game) {
        this.game = game;
    }
}
