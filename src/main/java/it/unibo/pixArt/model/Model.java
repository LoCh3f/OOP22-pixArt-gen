package it.unibo.pixArt.model;


import it.unibo.pixArt.model.api.User;

public interface Model {

    /**
     * @return the user
     */
    User getUser();

    /**
     * @param name of the user
     * @param path where to save the files created by the user.
     */
    void setUser(final String name, final String path);

}