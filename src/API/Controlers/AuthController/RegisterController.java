package API.Controlers.AuthController;

import API.Database.Database;
import API.Database.Response.TypeResponse.UserResponse;
import API.Database.Response.ResponseClass.User;
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
import java.util.Map;

import static API.Controlers.ApiController.ControllersDescription.queryToMap;

public class RegisterController implements HttpHandler {
    Database database = new Database();
    GsonBuilder gsonBuilder = new GsonBuilder();
    Gson gson = gsonBuilder.create();

    public RegisterController() throws SQLException {
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!"POST".equals(exchange.getRequestMethod())) {
            RestHttpServer.sendResponse(exchange, 400, "Method Not Allowed");
            return;
        }

        String body = readRequestBody(exchange);
        if (body.equals("BR")){
            RestHttpServer.sendResponse(exchange, 400, gson.toJson(new UserResponse()));
        }else{
            String query = gson.fromJson(body, User.class).toString();

            Map<String, String> param = queryToMap(query);

            String response = null;
            try {
                response = database.POST(Database.con, param, "register");
                RestHttpServer.sendResponse(exchange, 201, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String readRequestBody(HttpExchange exchange) throws IOException {
        InputStream inputStream = exchange.getRequestBody();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.contains("login:") && !line.contains("email:") && !line.contains("password:") && !line.contains("firstName:") && !line.contains("secondName:") && !line.contains("{") && !line.contains("}")) {
                return "BR";
            }
            requestBody.append(line);
        }
        reader.close();
        return requestBody.toString();
    }
}
