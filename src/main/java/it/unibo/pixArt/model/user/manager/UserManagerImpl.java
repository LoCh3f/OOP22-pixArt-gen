package it.unibo.pixArt.model.user.manager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import com.google.common.hash.Hashing;

import it.unibo.pixArt.model.user.User;
import it.unibo.pixArt.model.user.UserBuilderImpl;
import it.unibo.pixArt.model.user.storage.UserDataStorage;
import it.unibo.pixArt.model.user.storage.UserDataStorageImpl;

public final class UserManagerImpl implements UserManager {

    private final UserDataStorage dataStorage;

    public UserManagerImpl(final UserDataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    @Override
    public Optional<User> login(final String name, final String password) throws IOException {

        Optional<User> users = this.dataStorage.getUser(name);
        if (users.isEmpty()) {
            return Optional.empty();
        }

        return users.filter(u -> u.getPassword().equals(this.hashPassword(password)));

    }

    @Override
    public Optional<User> register(final String name, final String password, final String path) throws IOException {
 
        if (this.dataStorage.exists(name)) {
            return Optional.empty();
        }

        this.dataStorage.addNewUser(new UserBuilderImpl()
                                    .username(name)
                                    .password(this.hashPassword(password))
                                    .path(path).build());
        return this.dataStorage.getUser(name);

    }

    /**
     * @param password
     * @return hashed password
     */
    private String hashPassword(final String password) {
        return Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
    }

    private static class LazyHolder {
        private static final UserManager SINGLETON = new UserManagerImpl(new UserDataStorageImpl()); 
    }

    public static UserManager getInstance() {
        return LazyHolder.SINGLETON;
    }

}
