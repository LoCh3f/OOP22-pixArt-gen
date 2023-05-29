package it.unibo.pixArt.controller.game;

import it.unibo.pixArt.controller.SimpleController;
import it.unibo.pixArt.model.timer.GameTimer;
import javafx.scene.paint.Color;

public class GameControllerImpl extends SimpleController implements GameController{

    @Override
    public GameTimer getTimer() {
        return this.getModel().getTimer();
    }

    @Override
    public int getFrameSize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFrameSize'");
    }

    @Override
    public void selectPixel(int x, int y, Color color) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectPixel'");
    }
    
}
