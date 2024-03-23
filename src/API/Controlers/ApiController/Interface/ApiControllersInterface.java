package API.Controlers.ApiController.Interface;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.sql.SQLException;

public interface ApiControllersInterface {
    void GETController (HttpExchange httpExchange) throws IOException, NumberFormatException, SQLException;
    void POSTController (HttpExchange httpExchange) throws IOException, NumberFormatException, SQLException;
    void PUTController (HttpExchange httpExchange) throws IOException, NumberFormatException, SQLException;
    void PATCHController (HttpExchange httpExchange) throws IOException, NumberFormatException, SQLException;
    void DELETEController (HttpExchange httpExchange) throws IOException, NumberFormatException, SQLException;
}
