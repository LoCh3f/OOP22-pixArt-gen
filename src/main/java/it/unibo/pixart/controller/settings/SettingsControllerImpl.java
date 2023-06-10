package it.unibo.pixart.controller.settings;

import java.util.List;

import it.unibo.pixart.controller.SimpleController;
import it.unibo.pixart.model.project.FileTypes;
import it.unibo.pixart.model.project.builder.ProjectBuilderImpl;

/**
 * Implementation for SettingsController.
 */
public final class SettingsControllerImpl extends SimpleController implements SettingsController {
    private static final String DEF_NAME = "Project1";
    private static final List<String> DIMENSIONS = List.of("16", "32", "64");
    private static final FileTypes DEF_FILETYPE = FileTypes.PNG;

    @Override
    public List<String> getFileFormatsList() {
        return FileTypes.getAllTypes();
    }

    @Override
    public void createProject(final String name, final String path, final String fileType, final int size) {
        this.getModel().setProject(new ProjectBuilderImpl().projectName(name).path(path).fileType(fileType).frames(size).build());
    }

    @Override
    public String getDefName() {
        return DEF_NAME;
    }

    @Override
    public String getDefPath() {
        return getModel().getUser().getPathToFile();
    }

    @Override
    public String getDefFileType() {
        return DEF_FILETYPE.getType();
    }

    @Override
    public List<String> getAvailableSizeList() {
        return DIMENSIONS;
    }
}