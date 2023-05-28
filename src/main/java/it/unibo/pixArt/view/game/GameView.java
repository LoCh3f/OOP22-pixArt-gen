package it.unibo.pixArt.view.game;

import java.io.IOException;

import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameView extends AbstractFXView{

    @FXML
    public void onMenuClick(){
        PageLoader.getInstance().switchPage(getStage(), Pages.MENU, this.getController().getModel());
    }

    @Override
    public void init() {
        System.out.println(getController().getModel().getProject().getPath());
       /*  Parent root = null;
        try {
            root = FXMLLoader.load(ClassLoader.getSystemResource("pages/game.fxml"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        getStage().setScene(new Scene(root));*/
    }
    
}
