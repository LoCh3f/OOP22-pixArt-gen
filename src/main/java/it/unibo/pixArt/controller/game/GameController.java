package it.unibo.pixArt.controller.game;

import it.unibo.pixArt.controller.Controller;
import it.unibo.pixArt.model.colorstack.ColorStack;
import it.unibo.pixArt.model.timer.GameTimer;
import javafx.scene.paint.Color;

public interface GameController extends Controller{

    GameTimer getTimer();

    int getFrameSize();

    ColorStack getColorStack();

    void setColorStack();
    
    boolean checkPixel(final int x, final int y, final Color color);

    boolean colorStackIsEmpty();

    float getPercentage();
}
