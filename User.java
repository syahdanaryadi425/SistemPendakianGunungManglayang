public abstract class User {
    protected String username;
    protected String password;

    public User(String u, String p) {
        this.username = u;
        this.password = p;
    }
    public abstract boolean login(String u, String p);
}