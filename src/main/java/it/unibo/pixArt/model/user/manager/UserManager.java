package it.unibo.pixArt.model.user.manager;

import java.io.IOException;
import java.util.Optional;

import it.unibo.pixArt.model.user.User;

public interface UserManager {
    
    /**
     * @param name of the user
     * @param password of the user
     * @return the logged user or an empty Optional if the user doesn't exist
     * @throws IOException
     */
    Optional<User> login(final String name, final String password) throws IOException;

    /**
     * @param name of the user
     * @param password of the user
     * @param path of the user
     * @return the registered user or an empty Optional if the user is already registered
     * @throws IOException
     */
    Optional<User> register(final String name, final String password, final String path) throws IOException;

}
