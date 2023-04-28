package it.unibo.pixArt.view.pages;

import it.unibo.pixArt.controller.Controller;
import it.unibo.pixArt.model.Model;
import it.unibo.pixArt.view.GraphicComponent.WorkSpaceBuilder;
import it.unibo.pixArt.view.JavaFXView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class PageLoader {
    private final static String PATH_START = "pages/";

    private final static String PATH_END = ".fxml";

    private final static int ANIMATION_TIME = 300;

    private static class LazyHolder {
        private static final PageLoader SINGLETON = new PageLoader();
    }


    private PageLoader() {
    }

    public static PageLoader getInstance() {
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

        if (Pages.WORKSPACE.getName() == page.getName()) {
            Parent root = null;
            root = WorkSpaceBuilder.root(20, 20);
            stage.getScene().setRoot(root);
        } else {
            // Optional<Parent> root = Optional.empty();
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
