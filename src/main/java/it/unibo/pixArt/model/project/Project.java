package it.unibo.pixArt.model.project;

import it.unibo.pixArt.model.grid.Matrix;
import it.unibo.pixArt.model.historyframe.HistoryFrame;

import java.util.List;

/*
 * Interface for the Project.
 */
public interface Project {

    /**
     * @return the project's name
     */
    String getName();

    /**
     * @return the project's path
     */
    String getPath();

    /**
     * @return the list of frames
     */
    List<Matrix> getAllFrames();

    /**
     * Method to add a new frame in the list of frames.
     */
    void addNewFrame();

    /**
     * @return String format of a project.
     */
    String toString();

    /**
     * @return string format of the file format for each image of each frame
     */
    String getFileType();

    /**
     * @return the list of all the history frames.
     */
    List<HistoryFrame> getAllHistoryFrames();

    /**
     * Method to create a new HistoryFrame.
     */
    void addNewHistoryFrame(int index);

    /**
     * @return the last HistoryFrame.
     */
    HistoryFrame getLastHistoryFrame();

}
