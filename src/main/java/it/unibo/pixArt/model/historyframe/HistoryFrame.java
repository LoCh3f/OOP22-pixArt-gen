package it.unibo.pixArt.model.historyframe;


public interface HistoryFrame {
    /**
     * @return the historyframe's path
     */
    public String getPath();

    /**
     * @param path the path of the relative image.
     */
    public void setPath(final String path);

    /**
     * @param duration new value for the animation's duration
     */
    public void setAnimationDuration(final int duration);

    /**
     * @return the animation's duration
     */
    public int getAnimationDuration();

    /**
     * @return the historyframe's index
     */
    public int getIndex();
}

