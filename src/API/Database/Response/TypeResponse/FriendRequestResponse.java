package API.Database.Response.TypeResponse;

import API.Database.Response.ResponseClass.FriendRequest;

import java.util.List;

public class FriendRequestResponse {
    public List<FriendRequest> data;
    public String massage;
    public String status;

    public FriendRequestResponse(List<FriendRequest> data, String massage, String status){
        this.data = data;
        this.massage = massage;
        this.status = status;
    }

    public FriendRequestResponse(){
        this.data = null;
        this.massage = "Incorrect parameter";
        this.status = "failed";
    }
}
