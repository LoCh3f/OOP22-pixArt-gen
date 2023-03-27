package it.unibo.pixArt.model;

import it.unibo.pixArt.model.api.User;
import it.unibo.pixArt.model.impl.UserImpl;

public class ModelImpl implements Model {

    private User user;

    public ModelImpl(final String name, final String path) {
        this.user = new UserImpl(name, path);
    }

    public ModelImpl() {
    }

    @Override
    public User getUser() {
        return this.user;
    }

    @Override
    public void setUser(final String name, final String path) {
        this.user = new UserImpl(name, path);
    }

}
