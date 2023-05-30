package it.unibo.pixArt.controller.game;

import it.unibo.pixArt.controller.SimpleController;
import it.unibo.pixArt.model.colorstack.ColorStack;
import it.unibo.pixArt.model.colorstack.ColorStackImpl;
import it.unibo.pixArt.model.grid.PixelMatrix;
import it.unibo.pixArt.model.pixel.PixelBuilder;
import it.unibo.pixArt.model.timer.GameTimer;
import it.unibo.pixArt.view.game.GameView;
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
        this.colorStack = new ColorStackImpl( new PixelMatrix.MatrixBuilder().setColumns(16).setRows(16).build().getPixels());//new ColorStackImpl(this.getModel().getProject().getAllFrames().get(0).getPixels());
    }

    @Override
    public boolean checkPixel(int x, int y, Color color) {
        var pixel = new PixelBuilder.PxlBuilder().setColor(color).setX(x).setY(y).build();
        if(this.colorStack.getColorMap().get(color).stream().anyMatch(p -> p.comparePixel(pixel))) {
            this.colorStack.removePixel(color, pixel);
            return true;
        }
        return false;
    }
}
