package it.unibo.pixArt.view.impl;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import it.unibo.pixArt.model.project.Project;
import it.unibo.pixArt.utilities.FileHandler;
import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.Alert.AlertType;

public class ProjectView extends AbstractFXView {

    @FXML
    private ListView<String> listView = new ListView<>();
    private String selFolder;

    @Override
    public void init() {
        listView.getItems().clear();
        listView.getItems().addAll(Stream.of((new File(this.getController().getModel().getUser().getPathToFile()).listFiles()))
                                  .filter(file -> file.isDirectory() && !file.isHidden() && checkIfJsonInFolder(file))
                                  .map(File::getName).collect(Collectors.toList()));

        MultipleSelectionModel<String> selModel = listView.getSelectionModel();
        selModel.selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> changed, final String oldVal, final String newVal) {
                selFolder = listView.getSelectionModel().getSelectedItems().toString();
            }
        });
    }

    public void onHomeClick(final ActionEvent event) {
        PageLoader.getInstance().switchPage(getStage(), Pages.MENU, this.getController().getModel());
    }

    public void onEditClick(final ActionEvent event) {
        if (selFolder != null) {
            try {
                Project project = FileHandler.getInstance().fromJsonToProject(new File(getJsonPath(selFolder)));
                this.getController().getModel().setProject(project);
                PageLoader.getInstance().switchPage(getStage(), Pages.WORKSPACE, this.getController().getModel());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onDeleteClick(final ActionEvent event) {
        if (selFolder != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Delete File");
            alert.setHeaderText("Are you sure to delete" + selFolder.replace('[', ' ')
                                .substring(0, selFolder.length() - 1) + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) { 
                FileHandler.getInstance().deleteFile(getDirPath(selFolder));
                init();
            }
        }
    }

    private String getDirPath(String file) {
        return this.getController().getModel().getUser().getPathToFile() + file.replace('[', File.separatorChar)
                .substring(0, file.length()-1);
    }

    private String getJsonPath(final String file) {
        return getDirPath(file) + file.replace('[', File.separatorChar).substring(0, file.length() - 1) + ".json";
    }

    private boolean checkIfJsonInFolder(final File folder) {
        return Stream.of(folder.listFiles()).anyMatch(f -> f.getAbsolutePath()
                .equals(folder.getAbsolutePath() + File.separatorChar + folder.getName() + ".json"));
    }
}
