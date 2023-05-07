package it.unibo.pixArt.model.user;

public class UserBuilderImpl implements UserBuilder{

    private String name;
    private String password;
    private String path;

    @Override
    public UserBuilder username(String name) {
        this.name = name;
        return this;
    }

    @Override
    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    @Override
    public UserBuilder path(String path) {
        this.path = path;
        return this;
    }

    @Override
    public User build() {
        return new UserImpl(this.name, this.password, this.path);
    }


    
}
