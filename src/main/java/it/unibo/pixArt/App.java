package  it.unibo.pixArt;

import it.unibo.pixArt.model.Model;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends  Application {


    public static  void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        PageLoader.getInstance().switchPage(primaryStage, Pages.MENU, new );
    }
}