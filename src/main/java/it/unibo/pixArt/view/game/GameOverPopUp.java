package it.unibo.pixArt.view.game;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameOverPopUp extends StackPane{

    private final Stage stage;
    private final Button homeButton;
    private final Button newGameButton;
    private final Label gameOver;
    private final Text correctPercentage;
    private final VBox layout = new VBox();

    public GameOverPopUp(final String percentage){
        this.stage = new Stage();
        this.stage.setScene(new Scene(this));
        gameOver = new Label("GAME OVER");
        homeButton = new Button("Home");
        newGameButton = new Button("New Game");
        this.gameOver.setTextFill(Color.BLACK);
        this.setPrefWidth(200);
        this.setPrefHeight(150);
        this.layout.setAlignment(Pos.CENTER);
        correctPercentage = new Text("Correct: " + percentage + "%");
        this.layout.getChildren().addAll(this.gameOver, this.correctPercentage, this.homeButton, this.newGameButton);
        this.getChildren().addAll(layout);
    }

    public void onHomeClick(final Runnable backHome){
        this.homeButton.setOnMouseClicked(e -> {
            backHome.run();
        });
    }

    public void onNewGameClick(final Runnable newGame){
        this.newGameButton.setOnMouseClicked(e -> {
            newGame.run();
        });
    }

    public void close(){
        this.stage.close();
    }

    public void show(){
        this.stage.show();
    }
    
    
}
