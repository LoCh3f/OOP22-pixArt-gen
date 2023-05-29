package it.unibo.pixArt.controller.game;

import it.unibo.pixArt.controller.SimpleController;
import it.unibo.pixArt.model.colorstack.ColorStack;
import it.unibo.pixArt.model.colorstack.ColorStackImpl;
import it.unibo.pixArt.model.timer.GameTimer;
import javafx.scene.paint.Color;

public class GameControllerImpl extends SimpleController implements GameController{

    private ColorStack colorStack;

    @Override
    public GameTimer getTimer() {
        return this.getModel().getTimer();
    }

    @Override
    public int getFrameSize() {
        return this.getModel().getProject().getAllFrames().get(0).getColumns();
    }

    @Override
    public ColorStack getColorStack() {
        return this.colorStack;
    }

    @Override
    public void setColorStack() {
        this.colorStack = new ColorStackImpl(this.getModel().getProject().getAllFrames().get(0).getPixels());
    }

    @Override
    public void checkPixel(int x, int y, Color color) {
    }
}
