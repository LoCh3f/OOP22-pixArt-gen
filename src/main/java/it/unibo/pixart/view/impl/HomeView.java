package it.unibo.pixart.view.impl;

import it.unibo.pixart.model.ModelImpl;
import it.unibo.pixart.view.AbstractFXView;
import it.unibo.pixart.view.pages.Pages;
import it.unibo.pixart.view.pages.SceneManager;
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
    private static final double SIZE = 20d;
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

    /**
     * Method to switch to GameSetup.
     */
    @FXML
    public void onNewGame() {
        SceneManager.getInstance().switchPage(this.getStage(), Pages.GAMESETUP, this.getController().getModel());
    }

    /**
     * Method to switch to SettingsView.
     */
    @FXML
    public void onWorkSpaceClick() {
        SceneManager.getInstance().switchPage(this.getStage(), Pages.SETTINGS, this.getController().getModel());
    }

    /**
     * Method to switch to Projects.
     */
    @FXML
    public void onProjectHistoryClick() {
        SceneManager.getInstance().switchPage(this.getStage(), Pages.HISTORY, this.getController().getModel());
    }

    /**
     * Method to log out.
     */
    @FXML
    public void onBackClick() {
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
            b.prefWidthProperty().bind(this.buttonList.widthProperty().subtract(SIZE));
            b.maxHeight(Double.MAX_VALUE);
            b.setPadding(new Insets(10));
        });
    }

}
