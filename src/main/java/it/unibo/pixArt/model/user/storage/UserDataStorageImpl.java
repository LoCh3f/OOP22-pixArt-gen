package it.unibo.pixArt.model.user.storage;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import it.unibo.pixArt.model.user.User;
import it.unibo.pixArt.model.user.UserImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class UserDataStorageImpl implements UserDataStorage{

    private final char fileSeparator = File.separatorChar;
    private String USERDATAPATH = System.getProperty("user.home") + fileSeparator + "userData"; 

    private Type userListType = new TypeToken<List<UserImpl>>(){}.getType();
    private List<User> userList;
    private Charset charset = StandardCharsets.UTF_16;
    
    private void createJsonFile() throws IOException {
        if (Files.notExists(Path.of(USERDATAPATH))) {
            Files.createDirectory(Path.of(USERDATAPATH));
        }
        JsonArray obj = new JsonArray();
        File file = new File((USERDATAPATH) + fileSeparator + "users.json");
        USERDATAPATH = file.getAbsolutePath();
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(obj.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void load() throws IOException {
        if(userList == null) {
            this.createJsonFile();
            Gson gson = new Gson();
            String json = new String(Files.readAllBytes(Path.of(USERDATAPATH)));
            userList = gson.fromJson(json, userListType);
            if (userList == null){
                userList = new LinkedList<>();
            }
        }
    }

    private void update() throws IOException {
        this.load();
        Gson gson= new Gson();
        String json = gson.toJson(userList, userListType);
        Files.deleteIfExists(Path.of(USERDATAPATH));
        Files.writeString(Path.of(USERDATAPATH), json, charset, StandardOpenOption.CREATE);
    }


    @Override
    public Optional<User> getUser(final String name) throws IOException {
        this.load();
        return this.userList.stream().filter(u-> u.getName().equals(name)).findFirst();
    }

    @Override
    public void addNewUser(final User user) throws IOException {
        this.load();
        this.userList.add(user);
        this.update();
    }

    @Override
    public boolean exists(String name) throws IOException {
        this.load();
        return this.userList.stream().anyMatch(u->u.getName().equals(name));
    }
    
}
