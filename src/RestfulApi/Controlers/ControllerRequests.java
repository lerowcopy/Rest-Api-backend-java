package RestfulApi.Controlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.sql.SQLException;

public class ControllerRequests extends ControllersDescription implements HttpHandler {


    @Override
    public void handle(HttpExchange exchange) throws IOException, NumberFormatException {
        switch (exchange.getRequestMethod()){
            case "GET" -> {
                try {
                    GETController(exchange);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            case "POST" -> {
                try {
                    POSTController(exchange);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            case "PUT" -> {
                try {
                    PUTController(exchange);
                }catch (SQLException e){
                    throw new RuntimeException(e);
                }
            }

            case "PATCH" -> {
                try {
                    PATCHController(exchange);
                }catch (SQLException e){
                    throw new RuntimeException(e);
                }
            }

            case "DELETE" -> {
                try {
                    DELETEController(exchange);
                }catch (SQLException e){
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
