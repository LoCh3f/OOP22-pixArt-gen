package it.unibo.pixArt.model.user;

public interface UserBuilder {
    
    /**
     * @param name
     * @return
     */
    public UserBuilder username(final String name);

    /**
     * @param password
     * @return
     */
    public UserBuilder password(final String password);

    /**
     * @param path
     * @return
     */
    public UserBuilder path(final String path);

    /**
     * @return
     */
    public User build();

}
