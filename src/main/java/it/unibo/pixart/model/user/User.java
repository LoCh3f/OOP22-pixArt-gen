package it.unibo.pixart.model.user;

/**
 * Interface representing the user who needs to register or login.
 */
public interface User {

    /**
     * @return the name of the user
     */
    String getName();

    /**
     * @param name to set for the user
     */
    void setName(String name);

    /**
     * @return the password of the user
     */
    String getPassword();

    /**
     * @param password to set for the user
     */
    void setPassword(String password);

    /**
     * @return the path
     */
    String getPathToFile();

    /**
     * @param path
     */
    void setPathToFile(String path);

}
