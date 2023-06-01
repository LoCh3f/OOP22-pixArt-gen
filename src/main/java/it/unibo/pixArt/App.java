package it.unibo.pixArt;

import it.unibo.pixArt.model.ModelImpl;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws IOException {
        primaryStage.setResizable(true);
        primaryStage.setTitle("PixArt-geN");
        primaryStage.getIcons().add(new Image("image/mainIcon.png"));


        PageLoader.getInstance().switchPage(primaryStage, Pages.LOGIN, new ModelImpl(null, null, null, null, null, null));
    }
}