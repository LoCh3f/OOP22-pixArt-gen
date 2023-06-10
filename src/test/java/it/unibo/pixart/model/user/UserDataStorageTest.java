package it.unibo.pixart.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import org.junit.jupiter.api.Test;

import it.unibo.pixart.model.user.User;
import it.unibo.pixart.model.user.UserImpl;
import it.unibo.pixart.model.user.storage.UserDataStorage;
import it.unibo.pixart.model.user.storage.UserDataStorageImpl;

import java.nio.file.Files;


/**
 * The Test class of the UserDataStorage class.
 */
class UserDataStorageTest {

    private final char fileSeparator = File.separatorChar;
    private final String userDataPath = System.getProperty("user.home") 
                                        + fileSeparator + "userData" + fileSeparator + "users.json"; 

    private final User user1 = new UserImpl("luigiBianchi", "luigi001",
                                System.getProperty("user.dir") + File.separator + "Downloads");
    private final User user2 = new UserImpl("marcoRossi", "marco002",
                                System.getProperty("user.dir") + File.separator + "Downloads");
    private UserDataStorage userDataStorage;

    private void createUserList() throws IOException {
        userDataStorage = new UserDataStorageImpl();
        userDataStorage.addNewUser(user1);
        userDataStorage.addNewUser(user2);
    }

    /**
     * The test for the getUser method.
     * @throws IOException
     */
    @Test
    void testGetUser() throws IOException {
        createUserList();
        assertEquals(Optional.of(user1), userDataStorage.getUser(user1.getName()));
        assertEquals(Optional.of(user2), userDataStorage.getUser(user2.getName()));
        this.deleteFile();
    }

    /**
     * The test for the exist method.
     * @throws IOException
     */
    @Test
    void existsTest() throws IOException {
        createUserList();
        assertTrue(userDataStorage.exists(user1.getName()));
        assertFalse(userDataStorage.exists("franco"));
        this.deleteFile();
    }

    private void deleteFile() throws IOException {
        Files.deleteIfExists(Path.of(userDataPath));
    }

}
