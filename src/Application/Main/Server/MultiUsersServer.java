package Application.Main.Server;

import javax.management.remote.JMXServerErrorException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MultiUsersServer {
    private static final int port = 8021;
    private static final Set<ClientHandler> clients = new HashSet<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("New client connected " + clientSocket.getInetAddress().getHostAddress());

            ClientHandler clientHandler = new ClientHandler(clientSocket);
            clients.add(clientHandler);
            new Thread(clientHandler).start();
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket clientSocket;
        private Scanner in;
        private PrintWriter out;
        private String username;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                in = new Scanner(clientSocket.getInputStream());
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                username = in.nextLine();

                System.out.println(username + " joined the chat");
                broadcastMessage(username + " joined the chat");

                String message;
                while ((message = in.nextLine()) != null) {
                    broadcastMessage(username + ": " + message);
                }
            } catch (IOException e) {
                System.err.println("Error handling client: " + e.getMessage());
            } finally {
                clients.remove(this);
                try {
                    clientSocket.close();
                    in.close();
                    out.close();
                } catch (IOException e) {
                    System.err.println("Error closing client socket: " + e.getMessage());
                }
            }
        }

        private void broadcastMessage(String message) {
            for (ClientHandler client : clients) {
                String f = null;
                if (message.contains(":")){
                    f = message.substring(0, message.indexOf(":"));
                }
                if (!client.username.equals(f)) {
                    client.sendMessage(message);
                }
            }
        }

        private void sendMessage(String message) {
            out.println(message);
        }
    }
}


