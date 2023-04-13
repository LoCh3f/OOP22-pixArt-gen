package it.unibo.pixArt.model.project;

public interface Project {
    /**
     * @param newName
     */
    public void setName(final String newName);

    /**
     * 
     * @return
     */
    public String getName();

    /**
     * @return the path for the cover image of the project
     */
    public String getCoverImage();

    /**
     * sets the path of the cover image of the progect.
     */
    public void setCoverImage();
}
