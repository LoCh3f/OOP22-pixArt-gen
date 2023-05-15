package it.unibo.pixArt.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import it.unibo.pixArt.model.user.manager.UserManager;
import it.unibo.pixArt.model.user.manager.UserManagerImpl;
import it.unibo.pixArt.model.user.storage.UserDataStorageImpl;

public class UserManagerImplTest {

    private UserManager userManager;
    private final User user1 = new UserImpl("luigiBianchi", "luigi001", System.getProperty("user.dir") + File.separator + "Downloads");
    private final User user2 = new UserImpl("marcoRossi", "marco002", System.getProperty("user.dir") + File.separator + "Downloads");
    
    public void createUserManager(){
        this.userManager = new UserManagerImpl(new UserDataStorageImpl());
    }

    @Test
    public void loginWithEmptyFile() throws IOException{
        createUserManager();
        assertEquals(Optional.empty(), this.userManager.login("giovanni", "giovanni000"));
    }

    @Test 
    public void login() throws IOException{
        createUserManager();
        this.userManager.register(user1.getName(), user1.getPassword(), user1.getPathToFile());
        assertEquals(user1.getName(), this.userManager.login(user1.getName(), user1.getPassword()).get().getName());
        assertEquals(user1.getPassword(), this.userManager.login(user1.getName(), user1.getPassword()).get().getPassword());
    }

    @Test
    public void registerExistentUser() throws IOException{
        createUserManager();
        this.userManager.register(user2.getName(), user2.getPassword(), user2.getPathToFile());
        assertEquals(user2.getName(), this.userManager.login(user2.getName(), user2.getPassword()).get().getName());
        assertEquals(user2.getPassword(), this.userManager.login(user2.getName(), user2.getPassword()).get().getPassword());
        assertEquals(Optional.empty(), this.userManager.register(user2.getName(), user2.getPassword(), user2.getPathToFile()));
    }


    
}
