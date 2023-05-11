package it.unibo.pixArt.controller.login;

import java.io.IOException;

import it.unibo.pixArt.controller.Controller;

public interface LoginController extends Controller{
    
    public boolean login(final String username, final String password) throws IOException;

    public boolean register(final String username, final String password, final String path) throws IOException;

}
