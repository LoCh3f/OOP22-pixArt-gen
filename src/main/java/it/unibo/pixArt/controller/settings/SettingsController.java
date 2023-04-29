package it.unibo.pixArt.controller.settings;

import java.util.List;

import it.unibo.pixArt.model.grid.PixelGrid;

public interface SettingsController {
    
    /**
     * @return a list containing all the file formats.
     */
    public List<String> getFileFormatsList();

    /**
     * Method to create the project.
     */
    public void createProject(final String name, final String path, final String fileType, final int size);

    /**
     * @return the default name for every project.
     */
    public String getDefName();

    /**
     * @return default path for every project.
     */
    public String getDefPath();

    /**
     * @return default file type.
     */
    public String getDefFileType();
}
