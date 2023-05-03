package it.unibo.pixArt.view.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.Action;

import it.unibo.pixArt.model.user.UserImpl;
import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.SimpleView;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class ProjectView extends AbstractFXView {

    @FXML
    private ListView<String> listView = new ListView<>();
    private UserImpl user = new UserImpl(null, System.getProperty("user.dir"));

    @Override
    public void init() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'init'");

        listView.getItems().addAll(Stream.of((new File(user.getPathToFile()).listFiles()))
                                  .filter(file -> file.isDirectory() && !file.isHidden())
                                  .map(File::getName).collect(Collectors.toCollection(ArrayList::new)));
    }

    public void onHomeClick(final ActionEvent event){
        PageLoader.getInstance().switchPage(getStage(), Pages.MENU, this.getController().getModel());
    }

    public void onEditClick(final ActionEvent event){

    }

    public void onDeleteClick(ActionEvent event){

    }
  
    public void onChangeDirClick(ActionEvent event){
        Stage primaryStage = new Stage();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File(user.getPathToFile()));    
        File selectedDirectory = directoryChooser.showDialog(primaryStage);
        user.setPathToFile(selectedDirectory.getAbsolutePath());
        listView.getItems().clear();
        init();        
    }

}
