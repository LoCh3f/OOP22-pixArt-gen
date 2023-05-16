package it.unibo.pixArt.model.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.pixArt.model.project.builder.ProjectBuilderImpl;

public class ProjectTest {
    final static  String PROJECT_NAME = "First";
    final static String PROJECT_PATH = "/Desktop";
    final static FileTypes PROJECT_FILE = FileTypes.JPEG;
    private final static Project TEST_PROJECT = new ProjectBuilderImpl()
                                                    .fileType(PROJECT_FILE.getType())
                                                    .projectName(PROJECT_NAME)
                                                    .path(PROJECT_PATH)
                                                    .build();
    
    @Test
    void getProjectName() {
        assertEquals(TEST_PROJECT.getName(),PROJECT_NAME);
    }

    @Test
    void getPath() {
        assertEquals(TEST_PROJECT.getPath(),PROJECT_PATH);
    }

    @Test
    void getFileType() {
        assertEquals(TEST_PROJECT.getFileType(),PROJECT_FILE.getType());
    }

    @Test
    void getFrames() {
        assertEquals(TEST_PROJECT.getAllFrames(),null);
    }
}
