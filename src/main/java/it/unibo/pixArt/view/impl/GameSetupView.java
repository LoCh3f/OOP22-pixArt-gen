package it.unibo.pixArt.view.impl;

import java.util.stream.Collectors;

import it.unibo.pixArt.controller.game.GameSetupController;
import it.unibo.pixArt.model.game.GameType;
import it.unibo.pixArt.model.timer.TimerType;
import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.pages.SceneManager;
import it.unibo.pixArt.view.pages.Pages;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * View in which the user selects the game mode, timer and game level.
 */
public final class GameSetupView extends AbstractFXView {

    @FXML
    private ListView<ImageView> projectsList;

    @FXML
    private ChoiceBox<String> timerChoiceBox;

    @FXML
    private ChoiceBox<String> typeChoiceBox;

    @FXML
    private TextArea gameDescription;

    @FXML
    private void onStartClicked() {
        this.getGameSetupController().setTimer(timerChoiceBox.getValue());
        this.getGameSetupController().setProject(projectsList.getSelectionModel().getSelectedIndex());
        this.getGameSetupController().setGameType(this.typeChoiceBox.getSelectionModel().getSelectedItem());
        this.getGameSetupController().setGame();
        SceneManager.getInstance().switchPage(getStage(), Pages.GAMEVIEW, this.getController().getModel());
    }

    @FXML
    private void onDiscardClicked() {
        SceneManager.getInstance().switchPage(this.getStage(), Pages.MENU, this.getController().getModel());
    }

    @Override
    public void init() {
        this.getGameSetupController().setProjects();
        this.projectsList.getItems().addAll(getGameSetupController().getProjects()
                                    .stream()
                                    .map(e -> new ImageView(new Image(e)))
                                    .collect(Collectors.toList()));

        this.timerChoiceBox.getItems().addAll(getGameSetupController().getTimers()
                                        .stream()
                                        .map(TimerType::getDescription)
                                        .collect(Collectors.toList()));

        this.typeChoiceBox.getItems().addAll(getGameSetupController().getGameTypes()
                                            .stream()
                                            .map(GameType::getName)
                                            .collect(Collectors.toList()));
        this.typeChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(final ObservableValue<? extends Number> observable, final Number oldValue, final Number newValue) {
                gameDescription.setText(getGameSetupController()
                                        .getGameTypes()
                                        .get(typeChoiceBox.getSelectionModel().getSelectedIndex())
                                        .getDescription());
            }
        });

        this.timerChoiceBox.setValue(getGameSetupController().getTimers().get(0).getDescription());
        this.typeChoiceBox.setValue(getGameSetupController().getGameTypes().get(0).getName());
        this.gameDescription.setText(getGameSetupController().getGameTypes().get(0).getDescription());
        this.gameDescription.setWrapText(true);
        this.projectsList.getSelectionModel().select(0);
    }

    private GameSetupController getGameSetupController() {
        return (GameSetupController) getController();
    }
}
