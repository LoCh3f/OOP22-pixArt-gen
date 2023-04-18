package it.unibo.pixArt.model.animation;

import it.unibo.pixArt.model.historyframe.HistoryFrame;

public interface AnimationModel {
    /**
     * @param frameIndex the historyFrame's index inside the list
     * @param duration the animation's duration for that specific frame
     */
    public void selectFrameDuration(final int frameIndex, final int duration);

    /**
     * 
     */
    public void setPause();

    /**
     * @return
     */
    public boolean getPause();

    /**
     * @param direction The direction of the animation(FORWARD or BACKWARD).
     */
    public void setDirection(final String direction);

    /**
     * @return
     */
    public Directions getDirection();

    /**
     * @param index
     * @return
     */
    public HistoryFrame getCurrentFrame();
}
