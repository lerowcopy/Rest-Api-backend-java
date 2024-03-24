package API.Database.Response.TypeResponse;

import API.Database.Response.ResponseClass.User;

import java.util.List;

public class UserResponse {

    List<User> data;
    String message;
    String status;

    public UserResponse(List<User> data, String message, String status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public UserResponse(){
        this.data = null;
        this.message = "Incorrect parameter";
        this.status = "failed";
    }
}
