package Application.Main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MainWindow {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8021;
    public MainWindow(String username) throws IOException {
        Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        System.out.println("Connected to server at " + SERVER_ADDRESS + ":" + SERVER_PORT);

        Scanner in = new Scanner(socket.getInputStream());
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        Scanner console = new Scanner(System.in);

        Thread inputThread = new Thread(() -> {
            while (true) {
                String message = in.nextLine();
                System.out.println(message);
            }
        });

        out.println(username);
        Thread outputThread = new Thread(() -> {
            while (true) {
                String message = console.nextLine();
                out.println(message);
            }
        });

        inputThread.start();
        outputThread.start();

        //console.close();
        //in.close();
        //out.close();
        //socket.close();
    }
}
