package it.unibo.pixArt.view.login;

import java.io.IOException;

import it.unibo.pixArt.controller.login.LoginController;
import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginView extends AbstractFXView{

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField pathField;

    @FXML
    private Text loginResult;

    @FXML
    public void onLoginClick(final ActionEvent event) throws IOException{

        if (this.getLoginController().login(this.usernameField.getText(), this.passwordField.getText())) {
            PageLoader.getInstance().switchPage(getStage(), Pages.MENU, this.getController().getModel());
        } else {
            this.loginResult.setText("Username or password incorrect");
        }
    }


    @FXML
    public void onRegisterClick(final ActionEvent event) throws IOException{
        if (this.getLoginController().register(this.usernameField.getText(), this.passwordField.getText(), this.pathField.getText())){
            PageLoader.getInstance().switchPage(getStage(), Pages.MENU, this.getController().getModel());
        } else {
            this.loginResult.setText("Try again");
        }
    }

    @Override
    public void init() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'init'");
    }

    private LoginController getLoginController() {
        return (LoginController) this.getController();
    }
    
}
