package it.unibo.pixArt.controller.game;

import it.unibo.pixArt.controller.Controller;
import it.unibo.pixArt.model.timer.GameTimer;

public interface GameController extends Controller{

    public GameTimer getTimer();
    
}
