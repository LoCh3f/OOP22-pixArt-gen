package it.unibo.pixArt.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import it.unibo.pixArt.model.framestate.FrameState;
import it.unibo.pixArt.model.framestate.FrameStateImpl;
import it.unibo.pixArt.model.grid.PixelGrid;
import it.unibo.pixArt.model.grid.PixelMatrix;
import it.unibo.pixArt.model.pixel.ImplPixel;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.project.Project;
import it.unibo.pixArt.model.project.ProjectImpl;
import it.unibo.pixArt.model.user.User;
import it.unibo.pixArt.model.user.UserImpl;

public class JsonFileHandler {
    private Gson gson = new GsonBuilder().setLenient().setPrettyPrinting()
                        .registerTypeAdapter(Project.class, InterfaceSerializer.interfaceSerializer(ProjectImpl.class))
                        .registerTypeAdapter(PixelGrid.class, InterfaceSerializer.interfaceSerializer(PixelMatrix.class))
                        .registerTypeAdapter(Pixel.class, InterfaceSerializer.interfaceSerializer(ImplPixel.class))
                        .registerTypeAdapter(FrameState.class, InterfaceSerializer.interfaceSerializer(FrameStateImpl.class))
                        .create();
    private char fileSeparator = File.separatorChar;

    private User user = new UserImpl(null, null, null);

    private static class LazyHolder{
        private static final JsonFileHandler SINGLETON = new JsonFileHandler();
    }

    private JsonFileHandler(){
    }

    public static JsonFileHandler getInstance(){
        return LazyHolder.SINGLETON;
    }

    /**
     * Turns the project into a Json file
     * @param project The project to convert in a Json File
     * @throws IOException
     */
    public void fromProjectToJson(Project project) throws IOException{
        FileWriter fWriter = new FileWriter(new File(user.getPathToFile() + project.getPath() + fileSeparator + project.getName() + ".json"));
        fWriter.write(gson.toJson(project));
        System.out.println(project.getAllFrames().get(0).getPixels().stream().collect(Collectors.toList()).get(0).getColor().toString());
        fWriter.flush();
        fWriter.close();     
    }

    /**
     * Turns a Json File into a Project Object
     * @param jsonFile The name of the file that need to be converted into a Project Object
     * @return The Project with the Json file spec
     * @throws IOException
     */
    public Project fromJsonToProject(File jsonFile) throws IOException{
        BufferedReader fReader = new BufferedReader(new FileReader(user.getPathToFile() + jsonFile));
        StringBuilder sBuilder = new StringBuilder();
        String line = null;
        while((line = fReader.readLine()) != null){
            sBuilder.append(line).append(fileSeparator);
        }
        sBuilder.deleteCharAt(sBuilder.length()-1);
        fReader.close();

        return gson.fromJson(sBuilder.toString(), Project.class);
    }
    
}
