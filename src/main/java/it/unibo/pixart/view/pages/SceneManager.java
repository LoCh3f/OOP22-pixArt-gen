package it.unibo.pixart.view.pages;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import it.unibo.pixart.controller.Controller;
import it.unibo.pixart.model.Model;
import it.unibo.pixart.view.JavaFXView;

/**
 * Class whose job is to switch between scenes,views and controllers. Inspired from PageLoader class from Jhaturanga.
 */
public final class SceneManager {
    private static final String PATH_START = "pages/";

    private static final String PATH_END = ".fxml";

    private static class LazyHolder {
        private static final SceneManager SINGLETON = new SceneManager();
    }


    private SceneManager() {
    }

    /**
     * 
     * @return a singleton of SceneManager.
     */
    public static SceneManager getInstance() {
        return LazyHolder.SINGLETON;
    }

    /**
     * Method to switch stage.
     * @param stage
     * @param page
     * @param applicationInstance
     */
    public void switchPage(final Stage stage,
                           final Pages page,
                           final Model applicationInstance) {
        final Controller controller = page.getController();
        controller.setModel(applicationInstance);
        this.switchPageWithSpecificController(stage, page, controller);

    }

    /**
     * Method to switch stage with a specific controller.
     * @param stage
     * @param page
     * @param controller
     */
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

}
