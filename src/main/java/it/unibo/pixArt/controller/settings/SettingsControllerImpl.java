package it.unibo.pixArt.controller.settings;

import java.util.LinkedList;
import java.util.List;

import it.unibo.pixArt.controller.SimpleController;
import it.unibo.pixArt.model.grid.PixelGrid;
import it.unibo.pixArt.model.project.FileTypes;
import it.unibo.pixArt.model.project.Project;
import it.unibo.pixArt.model.project.ProjectImpl;
import it.unibo.pixArt.model.project.builder.ProjectBuilderImpl;

public class SettingsControllerImpl extends SimpleController implements SettingsController {
    static final String DEF_NAME = "Project1";
    static final String dimensions[] = {"16", "32", "64"};
    static final FileTypes DEF_FILETYPE = FileTypes.PNG;
    static final int DEF_SIZE = 16;

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
        return List.of(dimensions);
    }
}