package it.unibo.pixArt.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

/**
 * The Test class of th e UserImpl class
 */
public class UserImplTest {

    private final String name = "LuigiBianchi";
    private final String password = "luigi003";
    private final String path = System.getProperty("user.dir") + File.separator + "Downloads";

    @Test
    public void userTest() {
        User user = new UserImpl(name, password, path);
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
        assertEquals(path, user.getPathToFile());
    }

}
