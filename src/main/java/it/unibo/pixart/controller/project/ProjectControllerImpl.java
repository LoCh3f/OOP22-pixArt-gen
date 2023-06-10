package it.unibo.pixart.controller.project;

import java.io.File;
import java.util.stream.Stream;

import it.unibo.pixart.controller.SimpleController;

/**
 * Implementation for ProjectController.
 */
public final class ProjectControllerImpl extends SimpleController implements ProjectController {

    @Override
    public String getDirPath(final String file) {
        return this.getModel().getUser().getPathToFile() + file.replace('[', File.separatorChar)
                .substring(0, file.length() - 1);
    }

    @Override
    public String getJsonPath(final String file) {
        return getDirPath(file) + file.replace('[', File.separatorChar).substring(0, file.length() - 1) + ".json";
    }

    @Override
    public boolean checkIfJsonInFolder(final File folder) {
        return Stream.of(folder.listFiles()).anyMatch(f -> f.getAbsolutePath()
                .equals(folder.getAbsolutePath() + File.separatorChar + folder.getName() + ".json"));
    }

}
