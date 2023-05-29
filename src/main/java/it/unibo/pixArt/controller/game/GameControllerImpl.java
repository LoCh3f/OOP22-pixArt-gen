package it.unibo.pixArt.controller.game;

import it.unibo.pixArt.controller.SimpleController;
import it.unibo.pixArt.model.colorstack.ColorStack;
import it.unibo.pixArt.model.colorstack.ColorStackImpl;
import it.unibo.pixArt.model.pixel.ImplPixel;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.pixel.PixelBuilder;
import it.unibo.pixArt.model.timer.GameTimer;
import it.unibo.pixArt.view.game.GameView;
import javafx.scene.image.PixelBuffer;
import javafx.scene.paint.Color;
import javafx.util.Pair;

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
        var pixel = new PixelBuilder.PxlBuilder().setColor(color).setX(x).setY(y).build();
        if(this.colorStack.getColorMap().get(color).stream().anyMatch(p -> p.comparePixel(pixel))){
            this.getGameView().colorButton(x, y);
            this.colorStack.removePixel(color, pixel);
        }
    }

    private GameView getGameView(){
        return (GameView) this.getView();
    }
}
