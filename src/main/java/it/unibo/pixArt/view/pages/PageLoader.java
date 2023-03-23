package it.unibo.pixArt.view.pages;

import it.unibo.pixArt.controller.Controller;
import it.unibo.pixArt.model.Model;
import it.unibo.pixArt.view.JavaFXView;
import javafx.animation.FadeTransition;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Optional;


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
                           final Model model) {
        final Controller controller = page.getController();
        controller.setModel(model);
        this.switchPageWithSpecificController(stage, page, controller);

    }

    public void switchPageWithSpecificController(final Stage stage, final Pages page, final Controller controller) {

        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(PATH_START + page.getName() + PATH_END));

        Optional<Parent> root = Optional.empty();

        try {
            root = Optional.ofNullable(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (stage.getScene() == null) {
            stage.setScene(new Scene(root.get()));
        } else {
            stage.getScene().setRoot(root.get());
        }

        stage.setMinHeight(((AnchorPane) stage.getScene().getRoot()).getMinHeight());
        stage.setMinWidth(((AnchorPane) stage.getScene().getRoot()).getMinWidth());

        if (!(root.isEmpty())) {
            root.get().scaleXProperty().bind(Bindings.min(stage.widthProperty().divide(stage.minWidthProperty())
                    , stage.heightProperty().divide(stage.minHeightProperty())));
        }

        final JavaFXView view = loader.getController();
        controller.setView(view);
        view.setController(controller);
        view.setStage(stage);
        view.init();

        final FadeTransition fadeIn = new FadeTransition((Duration.millis(ANIMATION_TIME)));
        fadeIn.setFromValue(0.5);
        fadeIn.setToValue(1.0);
        fadeIn.play();
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
