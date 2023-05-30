package it.unibo.pixArt.model.historyframe;


public interface HistoryFrame {
    /**
     * @return the historyframe's path
     */
    String getPath();

    /**
     * @param path the path of the relative image.
     */
    void setPath(final String path);

    /**
     * @param duration new value for the animation's duration
     */
    void setAnimationDuration(final int duration);

    /**
     * @return the animation's duration
     */
    int getAnimationDuration();

    /**
     * @return the historyframe's index
     */
    int getIndex();
}

