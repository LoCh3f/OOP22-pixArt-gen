package it.unibo.pixArt.model.project;

import java.util.List;

import it.unibo.pixArt.model.grid.GridImpl;

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
     * @return the cover image's path
     */
    public String getCoverImage();

    /**
     * @param path cover image's path
     */
    public void setCoverImage(final String path);

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
    public List<GridImpl> getAllFrames();

}
