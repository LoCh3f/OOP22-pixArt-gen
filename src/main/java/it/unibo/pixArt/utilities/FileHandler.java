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
import it.unibo.pixArt.model.user.User;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import java.io.*;
import java.util.Optional;

public class FileHandler {
    private Gson gson = new GsonBuilder().setLenient()
            .registerTypeAdapter(Project.class, InterfaceSerializer.interfaceSerializer(ProjectImpl.class))
            .registerTypeAdapter(Matrix.class, InterfaceSerializer.interfaceSerializer(PixelMatrix.class))
            .registerTypeAdapter(Pixel.class, InterfaceSerializer.interfaceSerializer(ImplPixel.class))
            .registerTypeAdapter(FrameState.class, InterfaceSerializer.interfaceSerializer(FrameStateImpl.class))
            .registerTypeAdapter(HistoryFrame.class, InterfaceSerializer.interfaceSerializer(HistoryFrameImpl.class))
            .create();
    private char fileSeparator = File.separatorChar;

    private static class LazyHolder {
        private static final FileHandler SINGLETON = new FileHandler();
    }

    private FileHandler() {
    }

    public static FileHandler getInstance() {
        return LazyHolder.SINGLETON;
    }

    /**
     * Turns the project into a Json file
     *
     * @param project The project to convert in a Json File
     * @throws IOException
     */
    public void fromProjectToJson(Project project, User user) throws IOException {
        FileWriter fWriter = new FileWriter(project.getPath()+ fileSeparator + project.getName() + ".json");
        fWriter.write(gson.toJson(project));
        fWriter.flush();
        fWriter.close();
    }

    /**
     * Turns a Json File into a Project Object
     *
     * @param jsonFile The name of the file that need to be converted into a Project Object
     * @return The Project with the Json file spec
     * @throws IOException
     */
    public Project fromJsonToProject(File jsonFile, User user) throws IOException {
        BufferedReader fReader = new BufferedReader(new FileReader(jsonFile));
        StringBuilder sBuilder = new StringBuilder();
        String line = null;
        while ((line = fReader.readLine()) != null) {
            sBuilder.append(line).append(fileSeparator);
        }
        sBuilder.deleteCharAt(sBuilder.length() - 1);
        fReader.close();

        return gson.fromJson(sBuilder.toString(), Project.class);
    }

    public void deleteFile(File fileToDelete) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete File");
        alert.setHeaderText("Are you sure to delete the selected frame?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            fileToDelete.delete();
        }
    }

    public boolean initProjectFolder(String path){
        File folder = new File(path);
        if(checkFolderExist(folder)){
            folder.mkdir();
            return true;
        }
        return false;
    }

    private boolean checkFolderExist(File folder){
        if(folder.exists()){
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("The name is already taken");
            alert.setHeaderText("Press OK to overwrite the existing project or press CANCEL to go back and change name");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){ 
                folder.delete();
                return true;
            }else{
                return false;
            }
        }
        return true;
    }

}
