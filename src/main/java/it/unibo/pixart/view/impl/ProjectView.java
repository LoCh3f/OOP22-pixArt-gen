package it.unibo.pixart.view.impl;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unibo.pixart.controller.project.ProjectController;
import it.unibo.pixart.model.project.Project;
import it.unibo.pixart.utilities.FileHandler;
import it.unibo.pixart.view.AbstractFXView;
import it.unibo.pixart.view.pages.Pages;
import it.unibo.pixart.view.pages.SceneManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.Alert.AlertType;

/**The view for the Projects History Stage.
 */
public final class ProjectView extends AbstractFXView {

    @FXML
    private ListView<String> listView = new ListView<>();
    private String selFolder;

    @Override
    public void init() {
        listView.getItems().clear();
        listView.getItems().addAll(Stream.of(new File(this.getController().getModel().getUser().getPathToFile()).listFiles())
                                  .filter(file -> file.isDirectory() && !file.isHidden() 
                                  && this.getProjectController().checkIfJsonInFolder(file))
                                  .map(File::getName).collect(Collectors.toList()));

        MultipleSelectionModel<String> selModel = listView.getSelectionModel();
        selModel.selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(final ObservableValue<? extends String> changed, final String oldVal, final String newVal) {
                selFolder = listView.getSelectionModel().getSelectedItems().toString();
            }
        });
    }

    @FXML
    private void onHomeClick() {
        SceneManager.getInstance().switchPage(getStage(), Pages.MENU, this.getController().getModel());
    }

    @FXML
    private void onEditClick() {
        if (selFolder != null) {
            try {
                final Project project = FileHandler.getInstance().fromJsonToProject(
                    new File(this.getProjectController().getJsonPath(selFolder)));
                this.getController().getModel().setProject(project);
                SceneManager.getInstance().switchPage(getStage(), Pages.WORKSPACE, this.getController().getModel());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onDeleteClick() {
        if (selFolder != null) {
            final Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Delete File");
            alert.setHeaderText("Are you sure to delete" + selFolder.replace('[', ' ')
                                .substring(0, selFolder.length() - 1) + "?");
            final Optional<ButtonType> result = alert.showAndWait();
            if (result.get().equals(ButtonType.OK)) { 
                FileHandler.getInstance().deleteFile(this.getProjectController().getDirPath(selFolder));
                init();
            }
        }
    }

    private ProjectController getProjectController() {
        return (ProjectController) this.getController();
    }
}
