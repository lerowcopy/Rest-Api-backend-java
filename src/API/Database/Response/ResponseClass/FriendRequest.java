package API.Database.Response.ResponseClass;

import lombok.Data;

@Data
public class FriendRequest {
    private String id;
    private String loginU;
    private String friendLogin;

    public FriendRequest(String id, String loginU, String friendLogin) {
        this.id = id;
        this.loginU = loginU;
        this.friendLogin = friendLogin;
    }

    @Override
    public String toString() {
        return "loginU=" + loginU + "&" + "friendLogin=" + friendLogin;
    }
}
