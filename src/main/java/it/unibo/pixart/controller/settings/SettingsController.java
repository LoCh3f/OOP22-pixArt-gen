package it.unibo.pixart.controller.settings;

import java.util.List;

/**
 * Interface for SettingsView.
 */
public interface SettingsController {

    /**
     * @return a list containing all the file formats.
     */
    List<String> getFileFormatsList();

    /**
     * Method to create a project.
     * @param name
     * @param path
     * @param fileType
     * @param size
     */
    void createProject(String name, String path, String fileType, int size);

    /**
     * @return the default name for every project.
     */
    String getDefName();

    /**
     * @return default path for every project.
     */
    String getDefPath();

    /**
     * @return default file type.
     */
    String getDefFileType();

    /**
     * @return a list containing all the availabe sizes for a frame.
     */
    List<String> getAvailableSizeList();
}
