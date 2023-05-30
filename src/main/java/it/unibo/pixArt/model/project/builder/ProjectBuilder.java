package it.unibo.pixArt.model.project.builder;

import it.unibo.pixArt.model.project.Project;

/**
 * The interface of the class that builds a project.
 */
public interface ProjectBuilder {
    /**
     * @param projectName
     * @return
     */
    ProjectBuilder projectName(final String projectName);

    /**
     * @param path
     * @return
     */
    ProjectBuilder path(final String path);

    /**
     * @param fileType
     * @return
     */
    ProjectBuilder fileType(final String fileType);

    /**
     * @return
     */
    ProjectBuilder frames(final int size);

    /**
     * @return
     */
    Project build();
}
