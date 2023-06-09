package it.unibo.pixArt;

import it.unibo.pixArt.model.ModelImpl;
import it.unibo.pixArt.view.pages.SceneManager;
import it.unibo.pixArt.view.pages.Pages;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * doc.
 */
public class App extends Application {

    /**
     * doc.
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
