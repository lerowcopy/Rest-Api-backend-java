package API.Controlers.FriendsRequestController;

import API.Controlers.FriendsRequestController.Interface.FriendsRequestControllerInterface;
import API.Database.Database;
import API.Database.Response.ResponseClass.FriendRequest;
import API.Database.Response.TypeResponse.FriendRequestResponse;
import API.Server.RestHttpServer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class FriendsRequestControllerDescription implements FriendsRequestControllerInterface {

    GsonBuilder gsonBuilder = new GsonBuilder();
    Gson gson = gsonBuilder.create();
    Database database = new Database();

    public FriendsRequestControllerDescription() throws SQLException {
    }

    @Override
    public void POSTController(HttpExchange exchange) throws IOException {
        String requestBody = readRequestBody(exchange);

        String query = gson.fromJson(requestBody, FriendRequest.class).toString();

        Map<String, String> param = queryToMap(query);

        try {
            database.POSTFriendRequest(Database.con, param);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        RestHttpServer.sendResponse(exchange, 201, "Created");
    }

    @Override
    public void GETController(HttpExchange exchange) throws IOException {
        String query = exchange.getRequestURI().getRawQuery();

        Map<String, String> param = queryToMap(query);
        FriendRequestResponse response = new FriendRequestResponse();
        try {
            response = database.GETFriendRequest(Database.con, param);
        } catch (SQLException e) {
            RestHttpServer.sendResponse(exchange, 400, "Bad Request");
        }
        RestHttpServer.sendResponse(exchange, 200, gson.toJson(response));
    }

    @Override
    public void DELETEController(HttpExchange exchange) throws IOException {
        String query = exchange.getRequestURI().getRawQuery();

        Map<String, String> param = queryToMap(query);

        try {
            database.DELETEFriendRequest(Database.con, param);
        } catch (SQLException e) {
            RestHttpServer.sendResponse(exchange, 400, "Bad Request");
        }
        RestHttpServer.sendResponse(exchange, 200, "OK");
    }

    private String readRequestBody(HttpExchange exchange) throws IOException {
        InputStream inputStream = exchange.getRequestBody();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        reader.close();
        return requestBody.toString();
    }

    private Map<String, String> queryToMap(String query) {
        if (query == null) {
            return null;
        }
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            } else {
                result.put(entry[0], "");
            }
        }
        return result;
    }
}
