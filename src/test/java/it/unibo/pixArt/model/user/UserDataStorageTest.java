package it.unibo.pixArt.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import it.unibo.pixArt.model.user.storage.UserDataStorage;
import it.unibo.pixArt.model.user.storage.UserDataStorageImpl;

public class UserDataStorageTest {
    
    private final User user1 = new UserImpl("luigiBianchi", "luigi001", System.getProperty("user.dir") + File.separator + "Downloads");
    private final User user2 = new UserImpl("marcoRossi", "marco002", System.getProperty("user.dir") + File.separator + "Downloads");
    private UserDataStorage userDataStorage;

    private void createUserList() throws IOException {
        userDataStorage = new UserDataStorageImpl();
        userDataStorage.addNewUser(user1);
        userDataStorage.addNewUser(user2);
    }

    @Test
    public void getUserTest() throws IOException {
        createUserList();
        assertEquals(Optional.of(user1), userDataStorage.getUser(user1.getName()));
        assertEquals(Optional.of(user2), userDataStorage.getUser(user2.getName()));
    }

    @Test
    public void existsTest() throws IOException{
        createUserList();
        assertTrue(userDataStorage.exists(user1.getName()));
        assertFalse(userDataStorage.exists("franco"));
    }

}
