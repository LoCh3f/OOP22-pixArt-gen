package it.unibo.pixArt.model;

import it.unibo.pixArt.model.api.User;
import it.unibo.pixArt.model.impl.UserImpl;

public abstract class AbstractModel implements Model {

    private User user;

    public AbstractModel(final String name, final String path) {
        this.user = new UserImpl(name, path);
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
