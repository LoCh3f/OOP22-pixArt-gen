package it.unibo.pixart.model.user.storage;

import java.io.IOException;
import java.util.Optional;

import it.unibo.pixart.model.user.User;

/**
 * Interface for saving user data.
 */
public interface UserDataStorage {

    /**
     * @param name of the user
     * @return an empty Optional if the user doesn't exist or an Optional containing 
     *          the user registered with the username 'name'
     * @throws IOException
     */
    Optional<User> getUser(String name) throws IOException;

    /**
     * @param user to add to the list of registered users
     * @throws IOException
     */
    void addNewUser(User user) throws IOException;

    /**
     * @param name of the user
     * @return true if the user is present in the list, false otherwise 
     * @throws IOException
     */
    boolean exists(String name) throws IOException;

}
