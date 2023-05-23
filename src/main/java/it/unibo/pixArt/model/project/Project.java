package it.unibo.pixArt.model.project;

import it.unibo.pixArt.model.grid.Matrix;
import it.unibo.pixArt.model.historyframe.HistoryFrame;

import java.util.List;

public interface Project {

    /**
     * @return the project's name
     */
    public String getName();

    /**
     * @return the project's path
     */
    public String getPath();

    /**
     * @return the list of frames
     */
    public List<Matrix> getAllFrames();

    /**
     * Method to add a new frame in the list of frames.
     */
    public void addNewFrame();

    /**
     * @return String format of a project.
     */
    public String toString();

    /**
     * @return string format of the file format for each image of each frame
     */
    public String getFileType();

    /**
     * @return the list of all the history frames.
     */
    public List<HistoryFrame> getAllHistoryFrames();

    /**
     * Method to create a new HistoryFrame.
     */
    public void addNewHistoryFrame(final int index);

    /**
     * @return the last HistoryFrame.
     */
    public HistoryFrame getLastHistoryFrame();

}
