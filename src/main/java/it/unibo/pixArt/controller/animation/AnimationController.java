package it.unibo.pixArt.controller.animation;

import java.util.List;

import it.unibo.pixArt.controller.Controller;
import it.unibo.pixArt.model.historyframe.HistoryFrame;

public interface AnimationController extends Controller {

    /**Method to change the direction of the animation.
     * 
     * @param newDir new direction
     */
    public void setAnimationDirection(final String newDir);

    /**
     * @param frameIndex the frame's index in the list
     * @param duration animation's duration in miliseconds
     */
    public void setFrameDuration(final int frameIndex, final int duration);


    /**Method to set wether the animation is on or off.
     * 
     */
    public void setAnimationIsRunning();

    /**
     * @return if the animation is running.
     */
    public boolean getAnimationIsRunning();

    /**
     * @return the list of all available sizes for the image preview.
     */
    public List<String> getListSizes();

    /**
     * @return a list containing the available directions for an animation.
     */
    public List<Directions> getListDirections();

    /**
     * @return get current Image
     */
    public HistoryFrame getCurrentImage();

    /**
     * @return get a list of all History frames.
     */
    public List<HistoryFrame> getHistoryFrames();

    /**
     * Method to save a project.
     */
    public void saveProject();
}
