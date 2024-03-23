package API.Database.Response;

import lombok.Data;

@Data
public class User {
    private String id;
    private String login;
    private String email;
    private String password;
    private String firstName;
    private String secondName;


    public  User() {}
    public User(String id, String login, String email, String password, String firstName, String secondName) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    @Override
    public String toString() {

        return "login=" + login + "&" + "email=" + email + "&" + "password=" + password + "&" + "firstName=" + firstName + "&" + "secondName=" + secondName;
    }

}
