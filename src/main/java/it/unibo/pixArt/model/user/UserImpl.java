package it.unibo.pixArt.model.user;

public class UserImpl implements User {
    private String name;

    private String pathToFile;

    public UserImpl(final String name, final String path) {
        this.name = name;
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

    public void setPathToFile(final String path) {
        this.pathToFile = path;
    }

    public String getPathToFile(){
        return this.pathToFile;
    }
}
