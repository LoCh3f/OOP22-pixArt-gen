package it.unibo.pixart.model.user.storage;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
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
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import it.unibo.pixart.model.user.User;
import it.unibo.pixart.model.user.UserImpl;

/**
 * Implementation of UserDataStorage.
 */
public final class UserDataStorageImpl implements UserDataStorage {

    private static final char FILESEPARATOR = File.separatorChar;
    private static final String USERDATAPATH = System.getProperty("user.home") + FILESEPARATOR + "userData"; 
    private static final String USERDATAFILE = "users.json";

    private final Type userListType = new TypeToken<List<UserImpl>>() { }.getType();
    private List<User> userList;
    private final Charset charset = StandardCharsets.UTF_8;

    @SuppressWarnings("PMD.AvoidPrintStackTrace")
    private void createJsonFile() throws IOException {
        if (Files.notExists(Path.of(USERDATAPATH))) {
            Files.createDirectory(Path.of(USERDATAPATH));
        }
        final JsonArray obj = new JsonArray();
        final File file = new File(USERDATAPATH + FILESEPARATOR + USERDATAFILE);
        if (!file.exists()) {
            try (FileWriter fw = new FileWriter(file)) {
                fw.write(obj.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void load() throws IOException {
        if (userList == null) {
            this.createJsonFile();
            final Gson gson = new Gson();
            final String json = new String(Files.readString(Path.of(USERDATAPATH + FILESEPARATOR + USERDATAFILE), charset));
            userList = gson.fromJson(json, userListType);
            if (userList == null) {
                userList = new LinkedList<>();
            }
        }
    }

    private void update() throws IOException {
        this.load();
        final Gson gson = new Gson();
        final String json = gson.toJson(userList, userListType);
        Files.deleteIfExists(Path.of(USERDATAPATH + FILESEPARATOR + USERDATAFILE));
        this.createJsonFile();
        Files.writeString(Path.of(USERDATAPATH + FILESEPARATOR + USERDATAFILE), json, charset, StandardOpenOption.CREATE);
    }


    @Override
    public Optional<User> getUser(final String name) throws IOException {
        this.load();
        return this.userList.stream().filter(u -> u.getName().equals(name)).findFirst();
    }

    @Override
    public void addNewUser(final User user) throws IOException {
        this.load();
        this.userList.add(user);
        this.update();
    }

    @Override
    public boolean exists(final String name) throws IOException {
        this.load();
        return this.userList.stream().anyMatch(u -> u.getName().equals(name));
    }

}
