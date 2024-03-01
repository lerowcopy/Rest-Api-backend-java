package RestfulApi.Controlers.Interface;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.sql.SQLException;

public interface ControllersInterface {
    public void GETController (HttpExchange httpExchange) throws IOException, NumberFormatException, SQLException;
    public void POSTController (HttpExchange httpExchange) throws IOException, NumberFormatException, SQLException;
}
