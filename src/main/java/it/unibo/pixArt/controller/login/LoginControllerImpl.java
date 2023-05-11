package it.unibo.pixArt.controller.login;

import java.io.IOException;

import it.unibo.pixArt.controller.SimpleController;
import it.unibo.pixArt.model.user.manager.UserManagerImpl;

public class LoginControllerImpl extends SimpleController implements LoginController{

    @Override
    public boolean login(String username, String password) throws IOException {
        if (!UserManagerImpl.getInstance().login(username, password).isEmpty()){
            final String path = UserManagerImpl.getInstance().login(username, password).get().getPathToFile();
            this.getModel().setUser(username, password, path);
            return true;
        }
        return false;
    }

    @Override
    public boolean register(String username, String password, String path) throws IOException {
        if (!UserManagerImpl.getInstance().register(username, password, path).isEmpty()){
            this.getModel().setUser(username, password, path);
            return true;
        } 
        return false;
    }
    
}
