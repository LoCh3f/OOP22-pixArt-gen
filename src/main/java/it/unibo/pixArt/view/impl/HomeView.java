package it.unibo.pixArt.view.impl;

import it.unibo.pixArt.model.ModelImpl;
import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.pages.Pages;
import it.unibo.pixArt.view.pages.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Menu view, in which the user selects the next action.
 */
public final class HomeView extends AbstractFXView {

    @FXML
    private VBox buttonList;
    @FXML
    private ImageView imageView;
    @FXML
    private AnchorPane root;
    @FXML
    private Button newGame;
    @FXML
    private Button newProject;
    @FXML
    private Button backToLogin;
    @FXML
    private Button projectHistory;

    @FXML
    private void onNewGame(final ActionEvent event) {
        SceneManager.getInstance().switchPage(this.getStage(), Pages.GAMESETUP, this.getController().getModel());
    }

    @FXML
    private void onWorkSpaceClick(final ActionEvent event) {
        SceneManager.getInstance().switchPage(this.getStage(), Pages.SETTINGS, this.getController().getModel());
    }

    @FXML
    private void onProjectHistoryClick(final ActionEvent event) {
        SceneManager.getInstance().switchPage(this.getStage(), Pages.HISTORY, this.getController().getModel());
    }

    @FXML
    private void onBackClick(final ActionEvent event) {
        SceneManager.getInstance().switchPage(this.getStage(), Pages.LOGIN, new ModelImpl(null, null, null, null, null));
    }

    @Override
    public void init() {

        AnchorPane.setLeftAnchor(this.imageView, 0d);
        this.imageView.fitWidthProperty().bind(this.getStage().widthProperty().divide(2));
        this.imageView.fitHeightProperty().bind(this.getStage().heightProperty());

        AnchorPane.setRightAnchor(this.buttonList, 0d);
        this.buttonList.setPadding(new Insets(10));
        this.buttonList.setAlignment(Pos.TOP_RIGHT);
        this.buttonList.prefWidthProperty().bind(this.getStage().widthProperty().divide(2).subtract(10));
        this.buttonList.prefHeightProperty().bind(this.getStage().heightProperty());
        this.buttonList.getChildren().forEach(c -> {
            final var b = (Button) c;
            b.prefWidthProperty().bind(this.buttonList.widthProperty().subtract(20d));
            b.maxHeight(Double.MAX_VALUE);
            b.setPadding(new Insets(10));
        });
    }

}
