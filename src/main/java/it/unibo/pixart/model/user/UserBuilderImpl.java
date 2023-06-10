package it.unibo.pixart.model.user;

/**
 * Implementation of UserBuilder.
 */
public final class UserBuilderImpl implements UserBuilder {

    private String name;
    private String password;
    private String path;

    @Override
    public UserBuilder username(final String name) {
        this.name = name;
        return this;
    }

    @Override
    public UserBuilder password(final String password) {
        this.password = password;
        return this;
    }

    @Override
    public UserBuilder path(final String path) {
        this.path = path;
        return this;
    }

    @Override
    public User build() {
        return new UserImpl(this.name, this.password, this.path);
    }

}
