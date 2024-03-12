/*
 * Created by JFormDesigner on Tue Mar 12 00:33:48 MSK 2024
 */

package Application.Main;

import com.sun.tools.javac.Main;

import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

/**
 * @author 79531
 */
public class MainWindow extends JFrame {
    public static MainWindow instance;

    public MainWindow() throws IOException {
    }

    public MainWindow(String username) throws IOException {
        instance = this;
        ClientPart client = new ClientPart(username);
        setSize(785, 540);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Misha
        scrollPane1 = new JScrollPane();
        chatPanel = new JPanel();
        label2 = new JLabel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== scrollPane1 ========
        {

            //======== chatPanel ========
            {
                chatPanel.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing.
                border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax. swing .border . TitledBorder. CENTER
                ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .awt . Font
                . BOLD ,12 ) ,java . awt. Color .red ) ,chatPanel. getBorder () ) ); chatPanel. addPropertyChangeListener(
                new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062ord\u0065r"
                .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );
                chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));

                //---- label2 ----
                label2.setText("text");
                chatPanel.add(label2);
            }
            scrollPane1.setViewportView(chatPanel);
        }
        contentPane.add(scrollPane1, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Misha
    private JScrollPane scrollPane1;
    public static JPanel chatPanel;
    private JLabel label2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    

    public static List<JLabel> messages = new ArrayList<>();
    public static class ClientPart {
        private static final String SERVER_ADDRESS = "localhost";
        private static final int SERVER_PORT = 8021;
        public final String username;
        MainWindow wnd = MainWindow.instance;

        public ClientPart(String username) throws IOException {
            this.username = username;
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Connected to server at " + SERVER_ADDRESS + ":" + SERVER_PORT);

            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Scanner console = new Scanner(System.in);

            Thread inputThread = new Thread(() -> {
                while (true) {
                    /*String message = in.nextLine();
                    System.out.println(message);*/
                    String message = in.nextLine();
                    if (message.contains("joined")) {
                        continue;
                    }
                    int f = 0;
                    if (message.contains(":")) {
                        String userMessage = message.substring((message.indexOf(":") + 2));
                        JLabel messag = new JLabel();

                        int count = 0;
                        for (int i = 0; i < message.length(); ++i){
                            if (count == 10){
                                String first = message.substring(0, i);
                                String second = message.substring(i);
                                message = first + "<br/>" + second;
                                f+= 1;
                                count = -1;
                            }
                            count += 1;
                        }

                        if (f != 0){
                            messag.setMaximumSize(new Dimension(3000, 18 * (f + 1)));
                        }
                        else{
                            messag.setMaximumSize(new Dimension(3000, 18));
                        }
                        if (message.substring(0, message.indexOf(":")).equals(username)){
                            messag.setHorizontalAlignment(JLabel.RIGHT);
                        }
                        message = "<html>" + message + "</html>";
                        messag.setText(message);
                        messag.setForeground(Color.BLACK);
                        chatPanel.add(messag);

                        chatPanel.revalidate();
                        chatPanel.repaint();
                        /*if (message.substring(0, message.indexOf(":")).equals(username)) {
                            if (RightArea != null) {
                                RightArea.setText(RightArea.getText() + userMessage);
                            } else {
                                RightArea.setText(userMessage + "\n");
                            }
                        } else {
                            if (LeftArea != null) {
                                LeftArea.setText(LeftArea.getText() + message + "\n");
                            } else {
                                LeftArea.setText(userMessage + "\n");
                            }
                        }*/
                    }
                    wnd.repaint();
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
        }
    }
}
