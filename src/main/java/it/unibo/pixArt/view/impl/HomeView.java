package it.unibo.pixArt.view.impl;

import it.unibo.pixArt.model.ModelImpl;
import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import static it.unibo.pixArt.utilities.variables.FXViewVariables.*;
import static it.unibo.pixArt.view.components.StageDistribution.PREF_HEIGHT;
import static it.unibo.pixArt.view.components.StageDistribution.PREF_WIDTH;

public class HomeView extends AbstractFXView {

    @FXML
    private ImageView imageView;
    @FXML
    private Parent root;
    @FXML
    private javafx.scene.control.Button newGame;
    @FXML
    private javafx.scene.control.Button newProject;
    @FXML
    private javafx.scene.control.Button BackToLogin;
    @FXML
    private Button projectHistory;

    @FXML
    public void onNewGame(final ActionEvent event) {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.GAMESETUP, this.getController().getModel());
    }

    @FXML
    public void onWorkSpaceClick(final ActionEvent event) {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.SETTINGS, this.getController().getModel());
    }

    @FXML
    public void onProjectHistoryClick(final ActionEvent event) {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.HISTORY, this.getController().getModel());
    }

    @FXML
    public void onBackClick(final ActionEvent event) {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.LOGIN, new ModelImpl(null, null, null, null , null));
    }

    @Override
    public void init() {
        /*final var bPane = new BorderPane();
        bPane.setPrefSize(PREF_WIDTH, PREF_HEIGHT);
        bPane.setPadding(new Insets(5));
        bPane.setStyle(FX_BACKGROUND_COLOR_START + "white");
        bPane.setCenter(imageView);
        bPane.setLeft(this.BackToLogin);
        bPane.setRight(this.newProject);
        bPane.setTop(this.newGame);
        bPane.setBottom(this.projectHistory);


        this.newProject.prefHeightProperty().bind(bPane.heightProperty().subtract(projectHistory.heightProperty().add(newGame.heightProperty())));
        this.newProject.prefWidthProperty().bind(bPane.widthProperty().divide(7));
        this.BackToLogin.prefHeightProperty().bind(bPane.heightProperty().subtract(this.newGame.heightProperty().add(this.projectHistory.heightProperty())));
        this.BackToLogin.prefWidthProperty().bind(bPane.widthProperty().divide(7));
        this.newGame.prefWidthProperty().bind(bPane.widthProperty());
        this.newGame.prefHeightProperty().bind(bPane.heightProperty().divide(7));
        this.projectHistory.prefWidthProperty().bind(bPane.widthProperty());
        this.projectHistory.prefHeightProperty().bind(bPane.heightProperty().divide(7));


        bPane.getChildren().forEach(c -> c.setStyle(FX_BACKGROUND_COLOR_START + "magenta" + ";" + FX_BORDER_COLOR + ";" + FX_BORDER_WIDTH));
        getStage().getScene().setRoot(bPane);*/


    }

    private HomeView getHomeController() {
        return (HomeView) this.getController();
    }
}
