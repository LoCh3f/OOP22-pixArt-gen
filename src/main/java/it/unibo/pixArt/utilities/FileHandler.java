package it.unibo.pixArt.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unibo.pixArt.model.framestate.FrameState;
import it.unibo.pixArt.model.framestate.FrameStateImpl;
import it.unibo.pixArt.model.grid.Matrix;
import it.unibo.pixArt.model.grid.PixelMatrix;
import it.unibo.pixArt.model.historyframe.HistoryFrame;
import it.unibo.pixArt.model.historyframe.HistoryFrameImpl;
import it.unibo.pixArt.model.pixel.ImplPixel;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.project.Project;
import it.unibo.pixArt.model.project.ProjectImpl;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public final class FileHandler {
    private Gson gson = new GsonBuilder().setLenient()
            .registerTypeAdapter(Project.class, InterfaceSerializer.interfaceSerializer(ProjectImpl.class))
            .registerTypeAdapter(Matrix.class, InterfaceSerializer.interfaceSerializer(PixelMatrix.class))
            .registerTypeAdapter(Pixel.class, InterfaceSerializer.interfaceSerializer(ImplPixel.class))
            .registerTypeAdapter(FrameState.class, InterfaceSerializer.interfaceSerializer(FrameStateImpl.class))
            .registerTypeAdapter(HistoryFrame.class, InterfaceSerializer.interfaceSerializer(HistoryFrameImpl.class))
            .create();

    private static class LazyHolder {
        private static final FileHandler SINGLETON = new FileHandler();
    }

    private FileHandler() {
    }

    public static FileHandler getInstance() {
        return LazyHolder.SINGLETON;
    }

    /**
     * Turns the project into a Json file.
     *
     * @param project The project to convert in a Json File
     * @throws IOException
     */
    public void fromProjectToJson(final Project project) throws IOException {
        FileWriter fWriter = new FileWriter(new File(project.getPath() + File.separatorChar + project.getName() + ".json"));
        fWriter.write(gson.toJson(project));
        fWriter.close();
    }

    /**
     * Turns a Json File into a Project Object.
     *
     * @param jsonFile The name of the file that need to be converted into a Project Object
     * @return The Project with the Json file spec
     * @throws IOException
     */
    public Project fromJsonToProject(final File jsonFile) throws IOException {
        BufferedReader fReader = new BufferedReader(new FileReader(jsonFile));
        StringBuilder sBuilder = new StringBuilder();
        String line = null;
        while ((line = fReader.readLine()) != null) {
            sBuilder.append(line).append(File.separatorChar);
        }
        sBuilder.deleteCharAt(sBuilder.length() - 1);
        fReader.close();

        return gson.fromJson(sBuilder.toString(), Project.class);
    }

    /**
     * @param path The path of the folder or file to be deleted
     */
    public void deleteFile(final String path) {
        File fileToDelete = new File(path);
        if (fileToDelete.isDirectory()) {
            File[] files = fileToDelete.listFiles();
            for (var f : files) {
                f.delete();
            }
            fileToDelete.delete();
        } else {
            fileToDelete.delete();
        }
    }

    /**Initialize the project in the selected path.
     * @param path The path where the project will be created
     * @return True if the folder doesn't exist and is created, otherwise false
     */
    public boolean initProjectFolder(final String path) {
        File folder = new File(path);
        if (checkFolderExist(folder)) {
            return folder.mkdir();
        }
        return false;
    }

    /**Check if the folder exist.
     * @param folder the file to be check
     * @return If the folder don't exist or if the folder exist and 
     * the User decide to delete it return True, otherwisw return False
     */
    private boolean checkFolderExist(final File folder) {
        if (folder.exists()) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Error: The name is already taken");
            alert.setContentText("Press OK to overwrite the existing project or press CANCEL to go back and change name");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) { 
                deleteFile(folder.getAbsolutePath());
            } else {
                return false;
            }
        }
        return true;
    }

}
