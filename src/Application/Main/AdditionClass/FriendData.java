package Application.Main.AdditionClass;

import lombok.Data;

@Data
public class FriendData {
    String pathToImage;
    String login;

    public FriendData(String pathToImage, String login){
        this.pathToImage = pathToImage;
        this.login = login;
    }
}
