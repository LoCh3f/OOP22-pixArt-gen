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
    private String selectedFolder;

    @Override
    public void init() {
        listView.getItems().clear();
        listView.getItems().addAll(Stream.of((new File(this.getController().getModel().getUser().getPathToFile()).listFiles()))
                                  .filter(file -> file.isDirectory() && !file.isHidden() && checkIfJsonInFolder(file))
                                  .map(File::getName).collect(Collectors.toList()));

        MultipleSelectionModel<String> selModel = listView.getSelectionModel();
        selModel.selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> changed, String oldVal, String newVal) {
                selectedFolder = listView.getSelectionModel().getSelectedItems().toString();
            }
        });
    }

    public void onHomeClick(final ActionEvent event){
        PageLoader.getInstance().switchPage(getStage(), Pages.MENU, this.getController().getModel());
    }

    public void onEditClick(final ActionEvent event){
        try {
            Project project = FileHandler.getInstance().fromJsonToProject(new File(getJsonPath(selectedFolder)));
            this.getController().getModel().setProject(project);
            PageLoader.getInstance().switchPage(getStage(), Pages.WORKSPACE, this.getController().getModel());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onDeleteClick(ActionEvent event){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete File");
        alert.setHeaderText("Are you sure to delete"+ selectedFolder.replace('[', ' ').substring(0, selectedFolder.length()-1) + "?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){ 
            FileHandler.getInstance().deleteFile(getDirPath(selectedFolder));
            init();  
        }
    }

    private String getDirPath(String inPath){
        return this.getController().getModel().getUser().getPathToFile() + fileNameToPathString(inPath);
    }

    private String getJsonPath(String file){
        return getDirPath(file) + fileNameToPathString(file) + ".json";
    }

    private String fileNameToPathString(String fileName){
        return fileName.replace('[', File.separatorChar).substring(0, fileName.length()-1);
    }

    private boolean checkIfJsonInFolder(File folder){
        return Stream.of(folder.listFiles()).anyMatch(f -> f.getAbsolutePath()
                .equals(folder.getAbsolutePath() + File.separatorChar + folder.getName()+ ".json"));
    }
}
