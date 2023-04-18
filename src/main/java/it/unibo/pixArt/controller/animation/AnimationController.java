package it.unibo.pixArt.controller.animation;

public interface AnimationController {
    /**
     * @param path the frame's image path
     */
    public void getImage(final String path);

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
    public void setAnimationPause();
}
