package Entites;

public class User {

    private int id;
    private String username;
    private String password;
    private String email;
    private String city;
    private String role;

    public User(String username, String password, String email, String city, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.city = city;
        this.role = role;
    }

    
    public User(int id, String username, String password, String email, String city, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.city = city;
        this.role = role;
    }

    
    public User(int id, String username, String password, String email, String city) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.city = city;
    }

    public User(String username, String password, String email, String city) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.city = city;
    }

    public User() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    

}
