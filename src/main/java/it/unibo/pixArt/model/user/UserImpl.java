package it.unibo.pixArt.model.user;

public final class UserImpl implements User {

    private String name;
    private String password;
    private String pathToFile;

    public UserImpl(final String name, final String password, final String path) {
        this.name = name;
        this.password = password;
        this.pathToFile = path;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(final String password) {
        this.password  = password;
    }

    @Override
    public void setPathToFile(final String path) {
        this.pathToFile = path;
    }

    @Override
    public String getPathToFile() {
        return this.pathToFile;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        UserImpl user = (UserImpl) o;
        return name.equals(user.name);
    }

}
