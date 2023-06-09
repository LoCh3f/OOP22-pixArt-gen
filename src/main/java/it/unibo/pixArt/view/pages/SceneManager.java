package it.unibo.pixArt.view.pages;

import it.unibo.pixArt.controller.Controller;
import it.unibo.pixArt.model.Model;
import it.unibo.pixArt.view.JavaFXView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class whose job is to switch between scenes,views and controllers. Inspired from PageLoader class from Jhaturanga.
 */
public final class SceneManager {
    private final static String PATH_START = "pages/";

    private final static String PATH_END = ".fxml";

    private static class LazyHolder {
        private static final SceneManager SINGLETON = new SceneManager();
    }


    private SceneManager() {
    }

    public static SceneManager getInstance() {
        return LazyHolder.SINGLETON;
    }

    public void switchPage(final Stage stage,
                           final Pages page,
                           final Model applicationInstance) {
        final Controller controller = page.getController();
        controller.setModel(applicationInstance);
        this.switchPageWithSpecificController(stage, page, controller);

    }

    public void switchPageWithSpecificController(final Stage stage, final Pages page, final Controller controller) {


        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(PATH_START + page.getName() + PATH_END));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (stage.getScene() == null) {
            stage.setScene(new Scene(root));
        } else {
            stage.getScene().setRoot(root);
        }
        final JavaFXView view = loader.getController();
        controller.setView(view);
        view.setController(controller);
        view.setStage(stage);
        view.init();

        stage.show();
    }

    public void newPage(final Pages page, final Model model) {
        final Stage stage = new Stage();
        this.switchPage(stage, page, model);
    }

    public void newPageWithSpecifiedController(final Pages page, final Controller controller) {
        final Stage stage = new Stage();
        this.switchPageWithSpecificController(stage, page, controller);
    }


}
