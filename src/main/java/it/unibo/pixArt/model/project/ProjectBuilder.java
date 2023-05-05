package it.unibo.pixArt.model.project;

/**
 * The interface of the class that builds a project.
 */
public interface ProjectBuilder {
    /**
     * @param projectName
     * @return
     */
    public ProjectBuilder projectName(final String projectName);

    /**
     * @param path
     * @return
     */
    public ProjectBuilder path(final String path);

    /**
     * @param fileType
     * @return
     */
    public ProjectBuilder fileType(final String fileType);

    /**
     * @return
     */
    public ProjectBuilder frames(final int size);

    /**
     * @return
     */
    public Project build();
}
