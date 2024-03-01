package RestfulApi.Controlers.Interface;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface ControllersInterface {
    public void GETController (HttpExchange httpExchange) throws IOException, NumberFormatException;
    public void POSTController (HttpExchange httpExchange) throws IOException, NumberFormatException;
}
