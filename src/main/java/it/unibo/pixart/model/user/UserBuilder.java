package it.unibo.pixart.model.user;

/**
 * Interface representing the user builder.
 */
public interface UserBuilder {

    /**
     * @param name name of the user
     * @return the userbuilder
     */
    UserBuilder username(String name);

    /**
     * @param password password of the user
     * @return the userbuilder
     */
    UserBuilder password(String password);

    /**
     * @param path path of the user
     * @return the userbuilder
     */
    UserBuilder path(String path);

    /**
     * @return the user
     */
    User build();

}
