package RestfulApi.Controlers;

import RestfulApi.Database.Database;
import RestfulApi.Server.RestHttpServer;
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
            return;
        } else {
            Map<String, String> params = queryToMap(query);
            try {
                Database database = new Database();

                String str = database.GET(Database.con, query);

                RestHttpServer.sendResponse(exchange, 200, str);
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
