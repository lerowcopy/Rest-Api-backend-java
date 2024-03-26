package API.Controlers.FriendsController;

import API.Database.Database;
import API.Database.Response.ResponseClass.FriendRequest;
import API.Server.RestHttpServer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class FriendsControllerRequests extends FriendsControllerDescription implements HttpHandler {

    public FriendsControllerRequests() throws SQLException {
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
