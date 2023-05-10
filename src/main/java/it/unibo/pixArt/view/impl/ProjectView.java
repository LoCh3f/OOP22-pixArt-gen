package it.unibo.pixArt.view.impl;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import it.unibo.pixArt.model.project.Project;
import it.unibo.pixArt.model.user.UserImpl;
import it.unibo.pixArt.utilities.JsonFileHandler;
import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.SimpleView;
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
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class ProjectView extends AbstractFXView {

    @FXML
    private ListView<String> listView = new ListView<>();
    private UserImpl user = new UserImpl(null, null, System.getProperty("user.dir"));
    private String selectedFolder;

    @Override
    public void init() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'init'");
        
        listView.getItems().clear();
        listView.getItems().addAll(Stream.of((new File(user.getPathToFile()).listFiles()))
                                  .filter(file -> file.isDirectory() && !file.isHidden())
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
        if(!(new File(getJsonPath(selectedFolder)).exists())){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("File doesn't exist");
            alert.setContentText(null);
            alert.showAndWait();
        }
        try {
            Project project = JsonFileHandler.getInstance().fromJsonToProject(new File(getJsonPath(selectedFolder)), this.getController().getModel().getUser());
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
        File deleteFile = new File(getDirPath(selectedFolder));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){ 
            File[] files = deleteFile.listFiles();
            for(File file : files){
                file.delete();
            }
            deleteFile.delete();
            init();  
        }
    }
  
    public void onChangeDirClick(ActionEvent event){
        Stage primaryStage = new Stage();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File(user.getPathToFile()));    
        File selectedDirectory = directoryChooser.showDialog(primaryStage);
        user.setPathToFile(selectedDirectory.getAbsolutePath());
        init();        
    }

    public String getDirPath(String inPath){
        return user.getPathToFile() + fileNameToPathString(inPath);
    }

    public String getJsonPath(String file){
        return getDirPath(file) + fileNameToPathString(file) + ".json";
    }

    public String fileNameToPathString(String fileName){
        return fileName.replace('[', File.separatorChar).substring(0, fileName.length()-1);
    }
}
