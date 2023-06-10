package it.unibo.pixArt.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

/**
 * The Test class of th e UserImpl class.
 */
class UserImplTest {

    private static final String NAME = "LuigiBianchi";
    private static final String PASSWORD = "luigi003";
    private static final String PATH = System.getProperty("user.dir") + File.separator + "Downloads";

    /**
     * Test for get methods of UserImpl.
     */
    @Test
    void userTest() {
        final User user = new UserImpl(NAME, PASSWORD, PATH);
        assertEquals(NAME, user.getName());
        assertEquals(PASSWORD, user.getPassword());
        assertEquals(PATH, user.getPathToFile());
    }

}
