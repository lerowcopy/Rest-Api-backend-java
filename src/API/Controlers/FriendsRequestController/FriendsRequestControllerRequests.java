package API.Controlers.FriendsRequestController;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.sql.SQLException;

public class FriendsRequestControllerRequests extends FriendsRequestControllerDescription implements HttpHandler {

    public FriendsRequestControllerRequests() throws SQLException {
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        switch (exchange.getRequestMethod()) {
            case "GET" -> {
                GETController(exchange);
            }
            case "POST" -> {
                POSTController(exchange);
            }
            case "DELETE" -> {
                DELETEController(exchange);
            }
        }
    }
}
