package RestfulApi.Controlers.AuthController;

import RestfulApi.Database.Database;
import RestfulApi.Database.Response.User;
import RestfulApi.Server.RestHttpServer;
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
import java.util.Map;

import static RestfulApi.Controlers.ControllersDescription.queryToMap;

public class LoginController implements HttpHandler {
    Database database = new Database();
    GsonBuilder gsonBuilder = new GsonBuilder();
    Gson gson = gsonBuilder.create();

    public LoginController() throws SQLException {
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!"POST".equals(exchange.getRequestMethod())) {
            RestHttpServer.sendResponse(exchange, 400, "Method Not Allowed");
            return;
        }

        String body = readRequestBody(exchange);

        String query = gson.fromJson(body, User.class).toString();

        Map<String, String> param = queryToMap(query);

        String response = null;
        try {
            response = database.POST(Database.con, param);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        RestHttpServer.sendResponse(exchange, 201, response);
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
}
