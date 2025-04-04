public abstract class User {
    int ID;
    String username;
    String password;
    String name;
    String email;

    public User(int ID, String username, String password, String name, String email) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String toString() {
        return "ID: " + ID + "\nUsername: " + username + "\nPassword: " + password + "\nName: " + name + "\nEmail: " + email;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public int getID () {
        return ID;
    }
}
