package it.unibo.pixArt.controller.login;

import java.io.IOException;

import it.unibo.pixArt.controller.Controller;
import it.unibo.pixArt.model.user.validator.ValidationResult;

/**
 * Controller for LoginView.
 **/
public interface LoginController extends Controller {

    /**
     * @param username
     * @param password
     * @return true if the user is logged, false otherwise
     * @throws IOException
     */
    boolean login(String username, String password) throws IOException;

    /**
     * @param username
     * @param password
     * @param path
     * @return true if the user can be registered, false otherwise
     * @throws IOException
     */
    boolean register(String username, String password, String path) throws IOException;

    /**
     * @param path
     */
    void guestLogin(String path);

    /**
     * @param username
     * @return the result of the username validation
     */
    ValidationResult usernameValidation(String username);

    /**
     * @param password
     * @return the result of the password validation
     */
    ValidationResult passwordValidation(String password);

    /**
     * @param path
     * @return the result of the path validation
     */
    ValidationResult pathValidation(String path);

}
