package it.unibo.pixArt.view.game;

import it.unibo.pixArt.controller.game.GameController;
import it.unibo.pixArt.model.timer.TimerThread;
import java.io.IOException;

import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameView extends AbstractFXView{

    @FXML
    private Label timer;

    @FXML
    public void onMenuClick(){
        PageLoader.getInstance().switchPage(getStage(), Pages.MENU, this.getController().getModel());
    }

    @Override
    public void init() {
        this.getGameController().getTimer().start();
        new TimerThread(this.getGameController().getTimer(), this::onTimeFinish, this::OnTimeUpdate).start();
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

    private GameController getGameController(){
        return (GameController) this.getController();
    }

    private void OnTimeUpdate(){
        Platform.runLater(()->{
           this.timer.setText(timeToString(this.getGameController().getTimer().getRemainingTime()));
        });
    }

    private void onTimeFinish(){
        Platform.runLater(()->{
            this.getGameController().getTimer().stop();
        });
    }

    private String timeToString(final double remainingTime){
        double minutes = remainingTime/60;
        double seconds = remainingTime % 60;
        return Integer.toString((int) minutes) + ":" + Integer.toString((int) seconds);
    }


    
}
