package it.unibo.pixArt.controller.login;

import java.io.IOException;

import it.unibo.pixArt.controller.Controller;
import it.unibo.pixArt.model.user.validator.ValidationResult;

public interface LoginController extends Controller{
    
    /**
     * @param username
     * @param password
     * @return true if the user is logged, false otherwise
     * @throws IOException
     */
    public boolean login(final String username, final String password) throws IOException;

    /**
     * @param username
     * @param password
     * @param path
     * @return true if the user can be registered, false otherwise
     * @throws IOException
     */
    public boolean register(final String username, final String password, final String path) throws IOException;

    /**
     * @param path
     */
    public void guestLogin(final String path);

    /**
     * @param username
     * @return the result of the username validation
     */
    public ValidationResult usernameValidation(final String username);

    /**
     * @param password
     * @return the result of the password validation
     */
    public ValidationResult passwordValidation(final String password);

    /**
     * @param path
     * @return the result of the path validation
     */
    public ValidationResult pathValidation(final String path);

}
