package it.unibo.pixart.controller.animation;

import java.util.List;

import it.unibo.pixart.controller.Controller;
import it.unibo.pixart.model.historyframe.HistoryFrame;

/**
 * Controller for AnimationView.
 */
public interface AnimationController extends Controller {

    /**
     * @param frameIndex the frame's index in the list.
     * @param duration animation's duration in miliseconds.
     */
    void setFrameDuration(int frameIndex, int duration);


    /**
     * Method to set wether the animation is on or off.
     * 
     */
    void setAnimationIsRunning();

    /**
     * @return if the animation is running.
     */
    boolean isAnimationIsRunning();

    /**
     * @return get current Image.
     */
    HistoryFrame getCurrentImage();

    /**
     * @return get a list of all History frames.
     */
    List<HistoryFrame> getHistoryFrames();

    /**
     * Method to save a project.
     */
    void saveProject();
}
