package API.Controlers.FriendsController.Interface;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface FriendsControllerInterface {
    void POSTController(HttpExchange exchange) throws IOException;
    void GETController(HttpExchange exchange) throws IOException;
    void DELETEController(HttpExchange exchange) throws IOException;
}
