package it.unibo.pixArt.model;

import it.unibo.pixArt.model.game.Game;
import it.unibo.pixArt.model.project.Project;
import it.unibo.pixArt.model.user.User;
import it.unibo.pixArt.model.user.UserImpl;

/**
 * Implementation of Model interface.
 */
public final class ModelImpl implements Model {
    private Project project;
    private User user;
    private Game game;

    /**
     * Constructor for ModelImpl.
     * @param name
     * @param password
     * @param path
     * @param project
     * @param game
     */
    public ModelImpl(final String name, final String password, final String path, final Project project, final Game game) {
        this.user = new UserImpl(name, password, path);
        this.project = project;
        this.game = game;
    }

    @Override
    public User getUser() {
        return this.user;
    }

    @Override
    public void setUser(final String name, final String password, final String path) {
        this.user = new UserImpl(name, password, path);
    }

    @Override
    public Project getProject() {
        return this.project;
    }

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
