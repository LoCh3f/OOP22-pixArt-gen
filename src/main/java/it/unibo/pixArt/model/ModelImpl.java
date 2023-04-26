package it.unibo.pixArt.model;


import it.unibo.pixArt.model.project.Project;
import it.unibo.pixArt.model.user.User;
import it.unibo.pixArt.model.user.UserImpl;

public class ModelImpl implements Model {
    private Project project;
    private User user;

    public ModelImpl(final String name, final String path, final Project project) {
        this.user = new UserImpl(name, path);
        this.project = project;
    }


    @Override
    public User getUser() {
        return this.user;
    }

    @Override
    public void setUser(final String name, final String path) {
        this.user = new UserImpl(name, path);
    }

    @Override
    public Project getProject() {
        return this.project;
    }

    @Override
    public void setProject(Project project) {
        this.project = project;
    }

    


}
