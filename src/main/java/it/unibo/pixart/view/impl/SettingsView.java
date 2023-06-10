package it.unibo.pixart.view.impl;

import it.unibo.pixart.controller.settings.SettingsController;
import it.unibo.pixart.utilities.FileHandler;
import it.unibo.pixart.view.AbstractFXView;
import it.unibo.pixart.view.pages.Pages;
import it.unibo.pixart.view.pages.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * View in which the user selects the attributes for a project.
 */
public final class SettingsView extends AbstractFXView {

    @FXML
    private TextField projectName;

    @FXML
    private TextField pathName;

    @FXML
    private ChoiceBox<String> fileFormat;

    @FXML
    private ChoiceBox<String> sizeChoice;

    @FXML
    private void createProject() {
        /*
         * Create the project based on the values taken from the javafx components.
         */
        this.getSettingsController().createProject(projectName.getText(), pathName.getText() + "/" + projectName.getText(),
        fileFormat.getValue(), Integer.parseInt(sizeChoice.getValue()));
        if (FileHandler.getInstance().initProjectFolder(this.getController().getModel().getProject().getPath())) {
            SceneManager.getInstance().switchPage(this.getStage(), Pages.WORKSPACE, this.getController().getModel());
        }
    }

    @FXML
    private void discardProject() {
        SceneManager.getInstance().switchPage(this.getStage(), Pages.MENU, this.getController().getModel());
    }

    @Override
    public void init() {
        fileFormat.getItems().addAll(getSettingsController().getFileFormatsList());
        fileFormat.setAccessibleHelp(this.getSettingsController().getDefFileType());
        fileFormat.setValue(getSettingsController().getFileFormatsList().get(0));
        sizeChoice.getItems().addAll(getSettingsController().getAvailableSizeList());
        sizeChoice.setValue(getSettingsController().getAvailableSizeList().get(0));
        projectName.setText(this.getSettingsController().getDefName());
        pathName.setText(this.getSettingsController().getDefPath());
    }

    private SettingsController getSettingsController() {
        return (SettingsController) this.getController();
    }

}
