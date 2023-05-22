package it.unibo.pixArt.view.impl;

import java.io.File;
import java.util.LinkedList;

import javax.swing.Action;

import it.unibo.pixArt.controller.settings.SettingsController;
import it.unibo.pixArt.model.ModelImpl;
import it.unibo.pixArt.model.project.FileTypes;
import it.unibo.pixArt.model.project.Project;
import it.unibo.pixArt.model.project.ProjectImpl;
import it.unibo.pixArt.utilities.FileHandler;
import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class SettingsView extends AbstractFXView {

    @FXML
    private TextField projectName;

    @FXML
    private TextField pathName;

    @FXML
    private ChoiceBox<String> fileFormat;

    @FXML
    private ChoiceBox<String> sizeChoice;

    @FXML
    public void setName() {

    }

    @FXML
    public void setPath() {

    }

    @FXML
    public void createProject() {
        /*
         * Create the project based on the values taken from the javafx components.
         */
        this.getSettingsController().createProject(projectName.getText(),pathName.getText() + File.separatorChar + projectName.getText(),
        fileFormat.getValue(),Integer.parseInt(sizeChoice.getValue()));
        System.out.println(getController().getModel().getProject().toString());
        if(FileHandler.getInstance().initProjectFolder(this.getController().getModel().getProject().getPath())){
            PageLoader.getInstance().switchPage(this.getStage(), Pages.WORKSPACE, this.getController().getModel());
        }
    }

    @FXML
    public void discardProject(final ActionEvent event) {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.MENU, new ModelImpl(null, null, null, null, null));
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
