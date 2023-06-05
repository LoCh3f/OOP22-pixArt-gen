package it.unibo.pixArt.controller.game;

import java.util.Map;
import java.util.Set;

import it.unibo.pixArt.controller.Controller;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.timer.GameTimer;
import javafx.scene.paint.Color;

public interface GameController extends Controller{

    /**
     * @return The timer of the game
     */
    GameTimer getTimer();

    /**
     * @return The size of the frame
     */
    int getFrameSize();

    /**
     * @return The map of the color stack 
     */
    Map<Color,Set<Pixel>> getColorStack();

    /**
     * Set the color stack to be used
     */
    void setColorStack();
    
    /**
     * @param x The x coordinate of the pixel
     * @param y The y coordinates of the pixel
     * @param color The color selected
     * @return True if the pixel int position x, y has the same color of the corresponding pixel in the color stack
     */
    boolean checkPixel(final int x, final int y, final Color color);

    /**
     * @return True if the color stack is empty
     */
    boolean colorStackIsEmpty();

    /**
     * @return The percentage of the pixel left withing the map
     */
    double getPercentage();

    /**
     * @return tha value of the isDrawing flag.
     */
    boolean getIsDrawing();

    /**
     * Sets the value of the isDrawing flag.
     */
    void setIsDrawing();
}
