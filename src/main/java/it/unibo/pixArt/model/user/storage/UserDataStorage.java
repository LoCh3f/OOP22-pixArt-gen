package it.unibo.pixArt.model.user.storage;

import java.util.Optional;

import it.unibo.pixArt.model.user.User;

public interface UserDataStorage {

    /**
     * @param name of the user
     * @return an empty Optional if the user doesn't exist or an Optional containing 
     *          the user registered with the username 'name'
     */
    public Optional<User> getUser(final String name);

    /**
     * @param user to add to the list of registered users
     */
    public void addNewUser(final User user);

    /**
     * @param name of the user
     * @return true if the user is present in the list, false otherwise 
     */
    public boolean exists(final String name);

}
