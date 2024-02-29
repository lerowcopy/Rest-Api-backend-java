package RestfulApi.Controlers;

import RestfulApi.Database.Database;
import RestfulApi.Database.Response.Response;
import RestfulApi.Server.RestHttpServer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class GETController implements HttpHandler {


    @Override
    public void handle(HttpExchange exchange) throws IOException, NumberFormatException {
        if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
            String response = "Invalid request method. This endpoint supports only GET requests.";
            RestHttpServer.sendResponse(exchange, 405, response);
            return;
        }


        String query = exchange.getRequestURI().getRawQuery();


        if (query == null || query.isEmpty()) {
            String response = "No query parameters provided.";
            RestHttpServer.sendResponse(exchange, 400, response);
        } else {
            Map<String, String> params = queryToMap(query);
            try {
                Database database = new Database();
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                String json = database.GET(Database.con, params);


                if (json.equals("BR")){
                    RestHttpServer.sendResponse(exchange, 400, gson.toJson(new Response()));
                }else{
                    RestHttpServer.sendResponse(exchange, 200, json);
                }

            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public Map<String, String> queryToMap(String query) {
        if(query == null) {
            return null;
        }
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            }else{
                result.put(entry[0], "");
            }
        }
        return result;
    }
}
