package it.unibo.pixArt.model.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

import it.unibo.pixArt.model.project.builder.ProjectBuilderImpl;

/** The Test class of the Project class.
 */
public class ProjectTest {
    private static final int DEF_DELAY = 100;
    private static final String DEF_IMAGE = File.separatorChar + "image" + File.separatorChar + "def-historyFrame.png";
    private static final int FRAMESIZE = 17;
    static final String PROJECT_NAME = "First";
    static final String PROJECT_PATH = "/Desktop";
    static final FileTypes PROJECT_FILE = FileTypes.JPEG;
    private static final Project TEST_PROJECT = new ProjectBuilderImpl()
                                                    .fileType(PROJECT_FILE.getType())
                                                    .projectName(PROJECT_NAME)
                                                    .path(PROJECT_PATH)
                                                    .build();

    private final Project project = new ProjectBuilderImpl().fileType(PROJECT_FILE.getType())
                                                            .projectName(PROJECT_NAME)
                                                            .path(PROJECT_PATH)
                                                            .frames(FRAMESIZE)
                                                            .build();
    @Test
    void getProjectName() {
        assertEquals(PROJECT_NAME, TEST_PROJECT.getName());
    }

    @Test
    void getPath() {
        assertEquals(PROJECT_PATH, TEST_PROJECT.getPath());
    }

    @Test
    void getFileType() {
        assertEquals(PROJECT_FILE.getType(), TEST_PROJECT.getFileType());
    }

    @Test
    void getFrames() {
        assertEquals(null, TEST_PROJECT.getAllFrames());
    }

    @Test
    void addFrame() {
        assertEquals(1, this.project.getAllFrames().size());
        this.project.addNewFrame();
        assertEquals(2, this.project.getAllFrames().size());
    }

    @Test
    void addHistoryFrame() {
        assertEquals(1, this.project.getAllHistoryFrames().size());
        this.project.addNewHistoryFrame(0);
        assertEquals(2, this.project.getAllHistoryFrames().size());
    }

    @Test
    void checkHistoryFrame() {
        assertEquals(DEF_DELAY, this.project.getAllHistoryFrames().get(0).getAnimationDuration());
        assertEquals(DEF_IMAGE, this.project.getAllHistoryFrames().get(0).getPath());
    }
}
