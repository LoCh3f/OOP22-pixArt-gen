package it.unibo.pixart.view.login;

import java.io.File;
import java.io.IOException;

import it.unibo.pixart.controller.login.LoginController;
import it.unibo.pixart.model.user.validator.ValidationResult;
import it.unibo.pixart.view.AbstractFXView;
import it.unibo.pixart.view.pages.Pages;
import it.unibo.pixart.view.pages.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;

/**
 * View for user Login.
 **/
public final class LoginView extends AbstractFXView {

    @FXML
    private ImageView logoImageView;

    @FXML
    private TextField usernameLoginField;

    @FXML
    private PasswordField passwordLoginField;

    @FXML
    private TextField pathField;

    @FXML
    private Text loginResult;

    @FXML
    private Text usernameValidation;

    @FXML
    private Text passwordValidation;

    @FXML
    private TextField usernameRegisterField;

    @FXML 
    private PasswordField passwordRegisterField;

    @FXML
    private Text pathValidation;

    @FXML
    private Text registerResult;

    @FXML
    private Text guestLoginResult;

    private boolean loginValidate(final String username, final String password) {
        final ValidationResult usernameValidationResult = this.getLoginController().usernameValidation(username);
        final ValidationResult passwordValidationResult = this.getLoginController().passwordValidation(password);

        if (usernameValidationResult.equals(ValidationResult.CORRECT) 
            && passwordValidationResult.equals(ValidationResult.CORRECT)) {
            return true;
        }

        this.loginResult.setText("Username or password incorrect");
        return false;
    }

    private boolean registerValidate(final String username, final String password, final String path) {
        final ValidationResult usernameValidationResult = this.getLoginController().usernameValidation(username);
        final ValidationResult passwordValidationResult = this.getLoginController().passwordValidation(password);
        final ValidationResult pathValidationResult = this.getLoginController().pathValidation(path);

        if (usernameValidationResult.equals(ValidationResult.CORRECT) && passwordValidationResult.equals(ValidationResult.CORRECT)
            && pathValidationResult.equals(ValidationResult.CORRECT)) {
            return true;
        }

        if (!usernameValidationResult.equals(ValidationResult.CORRECT)) {
            usernameValidation.setText(usernameValidationResult.getDescription());
        }

        if (!passwordValidationResult.equals(ValidationResult.CORRECT)) {
            passwordValidation.setText(passwordValidationResult.getDescription());
        }

        if (!pathValidationResult.equals(ValidationResult.CORRECT)) {
            pathValidation.setText(pathValidationResult.getDescription());
        }

        return false;
    }

    private boolean guestLoginValidate(final String path) {
        final ValidationResult pathValidationResult = this.getLoginController().pathValidation(path);

        if (pathValidationResult.equals(ValidationResult.CORRECT)) {
            return true;
        }
        pathValidation.setText(pathValidationResult.getDescription());
        return false;
    }

    @FXML
    private void onLoginClick(final ActionEvent event) throws IOException {
        if (this.loginValidate(this.usernameLoginField.getText(), this.passwordLoginField.getText())) {
            if (this.getLoginController().login(this.usernameLoginField.getText(), this.passwordLoginField.getText())) {
                SceneManager.getInstance().switchPage(getStage(), Pages.MENU, this.getController().getModel());
            } else {
                this.loginResult.setText("Username or password incorrect");
            }
        }
    }


    @FXML
    private void onRegisterClick(final ActionEvent event) throws IOException {
        if (this.registerValidate(this.usernameRegisterField.getText(), 
            this.passwordRegisterField.getText(), this.pathField.getText())) {
            if (this.getLoginController().register(this.usernameRegisterField.getText(), 
                this.passwordRegisterField.getText(), this.pathField.getText())) {
                SceneManager.getInstance().switchPage(getStage(), Pages.MENU, this.getController().getModel());
            } else {
                this.passwordValidation.setText(null);
                this.usernameValidation.setText(null);
                this.pathValidation.setText(null);
                this.registerResult.setText("Try again");
            }
        }
    }

    @FXML
    private void onLoginAsGuestClick(final ActionEvent event) {
        if (this.guestLoginValidate(this.pathField.getText())) {
            this.getLoginController().guestLogin(this.pathField.getText());
            SceneManager.getInstance().switchPage(getStage(), Pages.MENU, this.getController().getModel());
        }
        this.guestLoginResult.setText("Choose a path");
    }

    @FXML
    private void onSelectDirClick(final ActionEvent event) {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        final File selectedDirectory = directoryChooser.showDialog(getStage());
        if (selectedDirectory != null) {
            this.pathField.setText(selectedDirectory.getAbsolutePath());
        }
    }

    @Override
    public void init() {
        this.logoImageView.setImage(new Image("/image/nameLogo.png"));
    }

    private LoginController getLoginController() {
        return (LoginController) this.getController();
    }

}
