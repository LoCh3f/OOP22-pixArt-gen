package it.unibo.pixArt.model.user.storage;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import it.unibo.pixArt.model.user.User;
import it.unibo.pixArt.model.user.UserBuilder;
import it.unibo.pixArt.model.user.UserImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class UserDataStorageImpl implements UserDataStorage{

    private final String USERDATAPATH; 

    private Type userListType = new TypeToken<List<User>>(){}.getType();
    private List<User> userList= new LinkedList<>();
    private Charset charset = StandardCharsets.UTF_16;

    private User user;
    
    private void load() throws IOException {
        if(userList.isEmpty()) {
            Gson gson = new Gson();
            String json = Files.readString(Path.of(USERDATAPATH), charset);
            userList = gson.fromJson(json, userListType);
        }
    }

    private void update() {
        this.load();
        Gson gson= new Gson();
        String json = gson.toJson(userList, userListType);
        Files.deleteIfExists(Path.of(USERDATAPATH));
        Files.writeString(Path.of(USERDATAPATH), json, charset, StandardOpenOption.CREATE);
    }


    @Override
    public Optional<User> getUser(final String name) {
        this.load();
        return this.userList.stream().filter(u-> u.getName().equals(name)).findFirst();
    }

    @Override
    public void addNewUser(final User user) {
        this.load();
        this.userList.add(user);
        this.update();
    }

    @Override
    public boolean exists(String name) {
        this.load();
        return this.userList.stream().anyMatch(u->u.getName().equals(name));
    }
    
}
