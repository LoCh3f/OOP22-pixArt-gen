package it.unibo.pixart;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import it.unibo.pixart.model.ModelImpl;
import it.unibo.pixart.view.pages.Pages;
import it.unibo.pixart.view.pages.SceneManager;

/**
 * Launcher.
 */
public class App extends Application {

    /**
     * @param args
     */
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public final void start(final Stage primaryStage) throws IOException {
        primaryStage.setResizable(true);
        primaryStage.setTitle("PixArt-geN");
        primaryStage.getIcons().add(new Image("image/mainIcon.png"));


        SceneManager.getInstance().switchPage(primaryStage, Pages.LOGIN, new ModelImpl(null, null, null, null, null));
    }
}
