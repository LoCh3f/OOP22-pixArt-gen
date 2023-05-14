package it.unibo.pixArt.model.user;

public interface User {

    /**
     * @return the name of the user
     */
    String getName();

    /**
     * @param name to set for the user
     */
    void setName(final String name);

    /**
     * @return the password of the user
     */
    String getPassword();

    /**
     * @param password to set for the user
     */
    void SetPassword(final String password);

    /**
     * @return the path
     */
    String getPathToFile();

    /**
     * @param path
     */
    void setPathToFile(final String path);

}
