package it.unibo.pixArt.view.impl;

import it.unibo.pixArt.model.ModelImpl;
import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import static it.unibo.pixArt.utilities.variables.FXViewVariables.*;
import static it.unibo.pixArt.view.components.StageDistribution.PREF_HEIGHT;
import static it.unibo.pixArt.view.components.StageDistribution.PREF_WIDTH;

public class HomeView extends AbstractFXView {

    public VBox buttonList;
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


      AnchorPane.setLeftAnchor(this.imageView,0d);
      this.imageView.fitWidthProperty().bind(this.getStage().widthProperty().divide(2));
      this.imageView.fitHeightProperty().bind(this.getStage().heightProperty());

      AnchorPane.setRightAnchor(this.buttonList,0d);
      this.buttonList.setPadding(new Insets(10));
      this.buttonList.prefWidthProperty().bind(this.getStage().widthProperty().divide(2));
      this.buttonList.prefHeightProperty().bind(this.getStage().heightProperty());
      this.buttonList.getChildren().forEach( c -> {
          final var b = (Button) c;
          b.prefWidthProperty().bind(this.buttonList.widthProperty());
          b.maxHeight(Double.MAX_VALUE);
          b.setPadding(new Insets(20));
      });





    }

    private HomeView getHomeController() {
        return (HomeView) this.getController();
    }
}
