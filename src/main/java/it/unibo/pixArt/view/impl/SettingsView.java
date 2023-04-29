package it.unibo.pixArt.view.impl;

import javax.swing.Action;

import it.unibo.pixArt.controller.settings.SettingsController;
import it.unibo.pixArt.model.ModelImpl;
import it.unibo.pixArt.model.project.Project;
import it.unibo.pixArt.model.project.ProjectImpl;
import it.unibo.pixArt.model.workspace.WorkSpaceModelImpl;
import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class SettingsView extends AbstractFXView {
    @FXML
    private TextField projectName;

    @FXML
    private TextField pathName;

    @FXML
    private MenuButton fileFormat;

    @FXML
    public void createProject() {
        /*
         * Create the project based on the values.
         */
        this.getController().getModel().setProject(new ProjectImpl());
        PageLoader.getInstance().switchPage(this.getStage(), Pages.WORKSPACE, new WorkSpaceModelImpl(this.getController().getModel().getProject()));
    }

    @FXML
    public void discardProject(final ActionEvent event) {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.MENU, new ModelImpl(null, null, null));
    }

    @Override
    public void init() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'init'");
    }

    private SettingsController getSettingsController() {
        return (SettingsController) this.getController();
    }
    
}
