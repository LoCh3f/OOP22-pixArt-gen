package it.unibo.pixart.model.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.pixart.model.project.Project;
import it.unibo.pixart.model.project.builder.ProjectBuilderImpl;

/** The Test class of the ProjectBuilder class.
 */
class ProjectBuilderTest {
    private static final String FILE = ".PNG";
    private static final String NAME = "First";
    private static final String PATH = "/user";
    private static final int SIZE = 32;

    @Test
    void integrityTest() {
        final Project project = new ProjectBuilderImpl().fileType(FILE).projectName(NAME).path(PATH).frames(SIZE).build();
        assertEquals(NAME, project.getName());
        assertEquals(PATH, project.getPath());
        assertEquals(FILE, project.getFileType());
        assertEquals(1, project.getAllFrames().size());
    }
}
