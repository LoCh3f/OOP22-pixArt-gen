package it.unibo.pixArt.model.user.storage;

import java.io.IOException;
import java.util.Optional;

import it.unibo.pixArt.model.user.User;

public interface UserDataStorage {

    /**
     * @param name of the user
     * @return an empty Optional if the user doesn't exist or an Optional containing 
     *          the user registered with the username 'name'
     * @throws IOException
     */
    public Optional<User> getUser(final String name) throws IOException;

    /**
     * @param user to add to the list of registered users
     * @throws IOException
     */
    public void addNewUser(final User user) throws IOException;

    /**
     * @param name of the user
     * @return true if the user is present in the list, false otherwise 
     * @throws IOException
     */
    public boolean exists(final String name) throws IOException;

}
