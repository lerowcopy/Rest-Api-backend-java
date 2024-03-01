package RestfulApi.Controlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class ControllerRequests extends ControllersDescription implements HttpHandler {


    @Override
    public void handle(HttpExchange exchange) throws IOException, NumberFormatException {
        switch (exchange.getRequestMethod()){
            case "GET" -> {
                GETController(exchange);
            }
            case "POST" -> {
                POSTController(exchange);
            }
        }
    }


}
