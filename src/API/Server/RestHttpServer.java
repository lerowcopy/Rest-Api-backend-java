package API.Server;

import API.Controlers.AuthController.RegisterController;
import API.Controlers.ControllerRequests;
import API.Controlers.AuthController.LoginController;
import API.Controlers.FriendsController.FriendsController;
import com.sun.net.httpserver.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class RestHttpServer {
    protected static final int port = 8000;
    public static void main(String[] args) throws Exception{
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/api", new ControllerRequests());
        server.createContext("/auth/login", new LoginController());
        server.createContext("/auth/register", new RegisterController());
        server.createContext("/friendsRequest", new FriendsController());

        server.setExecutor(null);
        server.start();

        System.out.println("Server started on port " + port);
    }

    public static void sendResponse (HttpExchange exchange, int statusCode, String responseBody) throws IOException {
        byte[] responseBytes = responseBody.getBytes(StandardCharsets.UTF_8);

        exchange.sendResponseHeaders(statusCode, responseBody.length());

        OutputStream os = exchange.getResponseBody();
        os.write(responseBytes);
        os.close();
    }
}
