package it.unibo.pixart.utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.unibo.pixart.model.framestate.FrameState;
import it.unibo.pixart.model.framestate.FrameStateImpl;
import it.unibo.pixart.model.grid.Matrix;
import it.unibo.pixart.model.grid.PixelMatrix;
import it.unibo.pixart.model.historyframe.HistoryFrame;
import it.unibo.pixart.model.historyframe.HistoryFrameImpl;
import it.unibo.pixart.model.pixel.ImplPixel;
import it.unibo.pixart.model.pixel.Pixel;
import it.unibo.pixart.model.project.FileTypes;
import it.unibo.pixart.model.project.Project;
import it.unibo.pixart.model.project.ProjectImpl;
import it.unibo.pixart.model.project.builder.ProjectBuilderImpl;
/** The Test class of the FileHandler class.
 */
class FileHandlerTest {
    private static final Project PROJECT = new ProjectBuilderImpl().fileType(FileTypes.JPEG.toString()).frames(16)
                            .path(System.getProperty("user.home")).projectName("Test").build();
    private final Gson gson = new GsonBuilder()
                        .registerTypeAdapter(Project.class, InterfaceSerializer.interfaceSerializer(ProjectImpl.class))
                        .registerTypeAdapter(Matrix.class, InterfaceSerializer.interfaceSerializer(PixelMatrix.class))
                        .registerTypeAdapter(Pixel.class, InterfaceSerializer.interfaceSerializer(ImplPixel.class))
                        .registerTypeAdapter(FrameState.class, InterfaceSerializer.interfaceSerializer(FrameStateImpl.class))
                        .registerTypeAdapter(HistoryFrame.class, InterfaceSerializer.interfaceSerializer(HistoryFrameImpl.class))
                        .create();
    @Test
    void initProjectFolderTest() {
        final File testFolder = new File(System.getProperty("user.home") + File.separatorChar + "testFolder");
        assertTrue(FileHandler.getInstance().initProjectFolder(testFolder.getAbsolutePath()));
        testFolder.delete();
    }

    @Test
    void fromJsonToProjectTest() throws IOException {
        final FileWriter testFileWriter = new FileWriter(
            new File(PROJECT.getPath() + File.separatorChar + PROJECT.getName() + ".json"));
        testFileWriter.write(gson.toJson(PROJECT));
        testFileWriter.flush();
        testFileWriter.close();
        final Project p = FileHandler.getInstance()
                    .fromJsonToProject(new File(PROJECT.getPath() + File.separatorChar + PROJECT.getName() + ".json"));
        assertEquals(PROJECT.getName(), p.getName());
        assertEquals(PROJECT.getPath(), p.getPath());
        assertEquals(PROJECT.getFileType(), p.getFileType());
    }

}
