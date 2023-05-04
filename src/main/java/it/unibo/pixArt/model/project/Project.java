package it.unibo.pixArt.model.project;

import it.unibo.pixArt.model.grid.PixelGrid;

import java.util.List;

public interface Project {
    /**
     * @param newName the project's name
     */
    public void setName(final String newName);

    /**
     * @return the project's name
     */
    public String getName();

    /**
     * @param path project's path
     */
    public void setPath(final String path);

    /**
     * @return the project's path
     */
    public String getPath();

    /**
     * @return the list of frames
     */
    public List<PixelGrid> getAllFrames();

    /**
     * Method to add a new frame in the list of frames.
     */
    public void addNewFrame();

    /**
     * @return String format of a project.
     */
    public String toString();

    /**
     * @return size(num of rows, columns) of each frame.
     */
    public Integer getSize();

    /**
     * @return string format of the file format for each image of each frame
     */
    public String getFileType();

}
