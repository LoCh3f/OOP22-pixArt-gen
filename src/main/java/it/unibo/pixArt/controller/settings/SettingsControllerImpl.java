package it.unibo.pixArt.controller.settings;

import it.unibo.pixArt.controller.SimpleController;
import it.unibo.pixArt.model.project.FileTypes;
import it.unibo.pixArt.model.project.builder.ProjectBuilderImpl;

import java.util.List;

/**
 * Implementation for SettingsController.
 */
public final class SettingsControllerImpl extends SimpleController implements SettingsController {
    private static final String DEF_NAME = "Project1";
    private static final List<String> DIMENSIONS = List.of("16", "32", "64");
    private static final FileTypes DEF_FILETYPE = FileTypes.PNG;
    private static final int DEF_SIZE = 16;

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