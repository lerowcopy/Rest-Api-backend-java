package RestfulApi.Server;

import RestfulApi.Controlers.GETController;
import RestfulApi.Database.Database;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class RestHttpServer {
    protected static final int port = 8000;

    public static void sendResponse (HttpExchange exchange, int statusCode, String responseBody) throws IOException {
        byte[] responseBytes = responseBody.getBytes(StandardCharsets.UTF_8);

        exchange.sendResponseHeaders(statusCode, responseBody.length());

        OutputStream os = exchange.getResponseBody();
        os.write(responseBytes);
        os.close();
    }

    public static void main(String[] args) throws Exception{
        Database database = new Database();

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        System.out.println("Server started on port " + port);

        server.createContext("/", new GETController());

        server.setExecutor(null);
        server.start();
    }
}
