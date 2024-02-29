package RestfulApi.Database.Response;

import java.util.List;

public class Response {

    List<User> data;
    String message;
    String status;

    public Response(List<User> data, String message, String status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public Response(){
        this.data = null;
        this.message = "Incorrect parameter";
        this.status = "failed";
    }
}
