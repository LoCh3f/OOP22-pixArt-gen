package it.unibo.pixArt.view.impl;

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
    private javafx.scene.control.Button newFile;
    @FXML
    private javafx.scene.control.Button newProject;
    @FXML
    private javafx.scene.control.Button newAnimation;
    @FXML
    private Button projectHistory;

    @FXML
    public void onSetUpClick(final ActionEvent event) {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.SETTINGS, this.getController().getModel());
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
    public void onAnimationClick(final ActionEvent event) {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.ANIMATION, this.getController().getModel());
    }

    @Override
    public void init() {
        final var bPane = new BorderPane();
        bPane.setPrefSize(PREF_WIDTH, PREF_HEIGHT);
        bPane.setPadding(new Insets(5));
        bPane.setStyle(FX_BACKGROUND_COLOR_START + "white");
        bPane.setCenter(imageView);
        bPane.setLeft(this.newAnimation);
        bPane.setRight(this.newProject);
        bPane.setTop(this.newFile);
        bPane.setBottom(this.projectHistory);


        this.newProject.prefHeightProperty().bind(bPane.heightProperty().subtract(projectHistory.heightProperty().add(newFile.heightProperty())));
        this.newProject.prefWidthProperty().bind(bPane.widthProperty().divide(7));
        this.newAnimation.prefHeightProperty().bind(bPane.heightProperty().subtract(this.newFile.heightProperty().add(this.projectHistory.heightProperty())));
        this.newAnimation.prefWidthProperty().bind(bPane.widthProperty().divide(7));
        this.newFile.prefWidthProperty().bind(bPane.widthProperty());
        this.newFile.prefHeightProperty().bind(bPane.heightProperty().divide(7));
        this.projectHistory.prefWidthProperty().bind(bPane.widthProperty());
        this.projectHistory.prefHeightProperty().bind(bPane.heightProperty().divide(7));


        bPane.getChildren().forEach(c -> c.setStyle(FX_BACKGROUND_COLOR_START + "magenta" + ";" + FX_BORDER_COLOR + ";" + FX_BORDER_WIDTH));
        getStage().getScene().setRoot(bPane);


    }

    private HomeView getHomeController() {
        return (HomeView) this.getController();
    }
}
