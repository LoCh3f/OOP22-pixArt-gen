package it.unibo.pixArt.controller.game;

import it.unibo.pixArt.controller.Controller;
import it.unibo.pixArt.model.timer.GameTimer;
import javafx.scene.paint.Color;

public interface GameController extends Controller{

    GameTimer getTimer();

    int getFrameSize();

    void selectPixel(int x, int y, Color color);
    
}
