package it.unibo.pixart.model.project.builder;

import it.unibo.pixart.model.project.Project;

/**
 * The interface of the class that builds a project.
 */
public interface ProjectBuilder {
    /**
     * @param projectName
     * @return the project's name.
     */
    ProjectBuilder projectName(String projectName);

    /**
     * @param path
     * @return the project's path.
     */
    ProjectBuilder path(String path);

    /**
     * @param fileType
     * @return the project's file type.
     */
    ProjectBuilder fileType(String fileType);

    /**
     * @param size
     * @return the project's matrix size.
     */
    ProjectBuilder frames(int size);

    /**
     * @return a new Project instance.
     */
    Project build();
}
