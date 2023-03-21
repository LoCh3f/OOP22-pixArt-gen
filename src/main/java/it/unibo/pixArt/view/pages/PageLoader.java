package it.unibo.pixArt.view.pages;

import it.unibo.pixArt.controller.Controller;
import it.unibo.pixArt.model.Model;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class PageLoader {
    private final static String PATH_START = "resources/pages/";

    private final static String PATH_END = ".fxml";

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

    }

    public void switchPageWithSpecificController(final Stage stage, final Pages page, final Controller controller) {

        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(PATH_START + page.getName() + PATH_END));

    }


}
