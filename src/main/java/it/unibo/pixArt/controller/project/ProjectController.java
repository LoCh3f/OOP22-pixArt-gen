package it.unibo.pixArt.controller.project;

import java.io.File;

import it.unibo.pixArt.controller.Controller;

public interface ProjectController extends Controller {

    /**
     * @param file the string with the name of the file
     * @return the path of the folder selected
     */
    String getDirPath(String file);

    /**
     * @param file the string of the name of the folder
     * @return the path for the json file
     */
    String getJsonPath(String file);

    /**
     * @param folder the folder where to check if a json file is
     * @return True if a json file with the same name of the folder is in the folder, otherwise false
     */
    boolean checkIfJsonInFolder(File folder);
}
