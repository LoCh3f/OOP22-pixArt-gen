package it.unibo.pixArt.model;


import it.unibo.pixArt.model.game.Game;
import it.unibo.pixArt.model.project.Project;
import it.unibo.pixArt.model.timer.GameTimer;
import it.unibo.pixArt.model.user.User;
import it.unibo.pixArt.model.user.UserImpl;

public class ModelImpl implements Model {
    private Project project;
    private User user;
    private GameTimer timer;

    public ModelImpl(final String name, final String password, final String path, final Project project, final GameTimer timer) {
        this.user = new UserImpl(name, password, path);
        this.project = project;
        this.timer = timer;
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
    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public GameTimer getTimer() {
        return this.timer;
    }

    @Override
    public void setTimer(GameTimer newTimer) {
        this.timer = newTimer;
    }
}
