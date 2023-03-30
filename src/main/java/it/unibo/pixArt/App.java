package it.unibo.pixArt;

import java.io.IOException;

import it.unibo.pixArt.model.ModelImpl;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
import javafx.application.Application;
import javafx.print.PageLayout;
import javafx.stage.Stage;

public class App extends  Application {
   
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
 
    public void start(final Stage primaryStage) throws IOException {
        PageLoader.getInstance().switchPage(primaryStage, Pages.MENU, new ModelImpl());
    }
}