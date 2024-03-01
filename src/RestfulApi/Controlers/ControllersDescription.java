package RestfulApi.Controlers;

import RestfulApi.Controlers.Interface.ControllersInterface;
import RestfulApi.Database.Database;
import RestfulApi.Database.Response.Response;
import RestfulApi.Database.Response.User;
import RestfulApi.Server.RestHttpServer;
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


public class ControllersDescription implements ControllersInterface {


    @Override
    public void GETController(HttpExchange exchange) throws IOException, NumberFormatException, SQLException {
        Database database = new Database();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        String query = exchange.getRequestURI().getRawQuery();

        if (query == null || query.isEmpty()) {
            String response = "No query parameters provided.";
            RestHttpServer.sendResponse(exchange, 400, response);
        } else {
            Map<String, String> params = queryToMap(query);

            String response = database.GET(Database.con, params);

            if (response.equals("BR")) {
                RestHttpServer.sendResponse(exchange, 400, gson.toJson(new Response()));
            } else {
                RestHttpServer.sendResponse(exchange, 200, response);
            }

        }
    }

    @Override
    public void POSTController(HttpExchange exchange) throws IOException, NumberFormatException, SQLException {
        Database database = new Database();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        String body = readRequestBody(exchange);
        String query = gson.fromJson(body, User.class).toString();

        Map<String, String> param = queryToMap(query);

        String response = database.POST(Database.con, param);
        RestHttpServer.sendResponse(exchange, 201, response);
    }

    public static Map<String, String> queryToMap(String query) {
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

    static String readRequestBody(HttpExchange httpExchange) throws IOException {
        InputStream inputStream = httpExchange.getRequestBody();
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
