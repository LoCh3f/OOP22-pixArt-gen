package it.unibo.pixArt.controller.game;

import it.unibo.pixArt.controller.SimpleController;
import it.unibo.pixArt.model.timer.GameTimer;

public class GameControllerImpl extends SimpleController implements GameController{

    @Override
    public GameTimer getTimer() {
        return this.getModel().getTimer();
    }
    
}
