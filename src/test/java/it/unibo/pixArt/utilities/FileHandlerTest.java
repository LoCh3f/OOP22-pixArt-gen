package it.unibo.pixArt.utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

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
import it.unibo.pixArt.model.project.FileTypes;
import it.unibo.pixArt.model.project.Project;
import it.unibo.pixArt.model.project.ProjectImpl;
import it.unibo.pixArt.model.project.builder.ProjectBuilderImpl;

public class FileHandlerTest {
    private static final Project TEST_PROJECT = new ProjectBuilderImpl().fileType(FileTypes.JPEG.toString()).frames(16)
                            .path(System.getProperty("user.home")).projectName("Test").build();
    private Gson gson = new GsonBuilder()
                        .registerTypeAdapter(Project.class, InterfaceSerializer.interfaceSerializer(ProjectImpl.class))
                        .registerTypeAdapter(Matrix.class, InterfaceSerializer.interfaceSerializer(PixelMatrix.class))
                        .registerTypeAdapter(Pixel.class, InterfaceSerializer.interfaceSerializer(ImplPixel.class))
                        .registerTypeAdapter(FrameState.class, InterfaceSerializer.interfaceSerializer(FrameStateImpl.class))
                        .registerTypeAdapter(HistoryFrame.class, InterfaceSerializer.interfaceSerializer(HistoryFrameImpl.class))
                        .create();
    @Test
    void initProjectFolderTest(){
        File testFolder = new File(System.getProperty("user.home") + File.separatorChar + "testFolder");
        assertTrue(FileHandler.getInstance().initProjectFolder(testFolder.getAbsolutePath()));
        testFolder.delete();
    }

    @Test
    void fromJsonToProjectTest() throws IOException{
        FileWriter testFileWriter = new FileWriter(new File(TEST_PROJECT.getPath() + File.separatorChar + TEST_PROJECT.getName() + ".json"));
        testFileWriter.write(gson.toJson(TEST_PROJECT));
        testFileWriter.flush();
        testFileWriter.close();
        Project p = FileHandler.getInstance().fromJsonToProject(new File(TEST_PROJECT.getPath() + File.separatorChar + TEST_PROJECT.getName() + ".json"));
        assertEquals(TEST_PROJECT.getName(), p.getName());
        assertEquals(TEST_PROJECT.getPath(), p.getPath());
        assertEquals(TEST_PROJECT.getFileType(), p.getFileType());
    }

}
