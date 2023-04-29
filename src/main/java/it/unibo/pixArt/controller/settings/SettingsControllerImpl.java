package it.unibo.pixArt.controller.settings;

import java.util.LinkedList;
import java.util.List;

import it.unibo.pixArt.controller.SimpleController;
import it.unibo.pixArt.model.grid.PixelGrid;
import it.unibo.pixArt.model.project.FileTypes;
import it.unibo.pixArt.model.project.Project;
import it.unibo.pixArt.model.project.ProjectImpl;

public class SettingsControllerImpl extends SimpleController implements SettingsController {
    static final String DEF_NAME = "Project1";
    static final String DEF_PATH = "/Downloads";
    static final FileTypes DEF_FILETYPE = FileTypes.PNG;
    static final int DEF_SIZE = 16;

    @Override
    public List<String> getFileFormatsList() {
        return FileTypes.getAllTypes();
    }

    @Override
    public void createProject(final String name, final String path, final String fileType, final int size) {
        this.getModel().setProject(new ProjectImpl(name,path,fileType,size));
    }

    @Override
    public String getDefName() {
        return DEF_NAME;
    }

    @Override
    public String getDefPath() {
        return DEF_PATH;
    }

    @Override
    public String getDefFileType() {
        return DEF_FILETYPE.getType();
    }
}