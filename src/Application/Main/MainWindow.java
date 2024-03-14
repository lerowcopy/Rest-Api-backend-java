/*
 * Created by JFormDesigner on Tue Mar 12 00:33:48 MSK 2024
 */

package Application.Main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

        messageField.addActionListener(e -> {
            if (!messageField.equals("")){
                ClientPart.sendMessage(messageField.getText());
                messageField.setText("");
            }
        });
        sendMessageBtn.addActionListener(e ->{
            if (!messageField.equals("")){
                ClientPart.sendMessage(messageField.getText());
                messageField.setText("");
            }
        });
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Misha
        scrollPane1 = new JScrollPane();
        chatPanel = new JPanel();
        sendMessagePanel = new JPanel();
        messageField = new JTextField();
        sendMessageBtn = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== scrollPane1 ========
        {
            scrollPane1.setMaximumSize(new Dimension(43423423, 50023423));

            //======== chatPanel ========
            {
                chatPanel.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax.
                swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border
                . TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069al\u006fg"
                ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) ,chatPanel. getBorder
                ( )) ); chatPanel. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java
                .beans .PropertyChangeEvent e) {if ("\u0062or\u0064er" .equals (e .getPropertyName () )) throw new RuntimeException
                ( ); }} );
                chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
            }
            scrollPane1.setViewportView(chatPanel);
        }
        contentPane.add(scrollPane1, BorderLayout.CENTER);

        //======== sendMessagePanel ========
        {
            sendMessagePanel.setPreferredSize(new Dimension(0, 50));
            sendMessagePanel.setLayout(new BoxLayout(sendMessagePanel, BoxLayout.X_AXIS));
            sendMessagePanel.add(messageField);

            //---- sendMessageBtn ----
            sendMessageBtn.setPreferredSize(new Dimension(50, 50));
            sendMessageBtn.setMaximumSize(new Dimension(50, 50));
            sendMessageBtn.setIcon(new ImageIcon(getClass().getResource("/Application/Auth/Icons/send.png")));
            sendMessagePanel.add(sendMessageBtn);
        }
        contentPane.add(sendMessagePanel, BorderLayout.PAGE_END);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Misha
    private JScrollPane scrollPane1;
    public static JPanel chatPanel;
    private JPanel sendMessagePanel;
    private JTextField messageField;
    private JButton sendMessageBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public static List<JLabel> messages = new ArrayList<>();


    public static class ClientPart {
        private static final String SERVER_ADDRESS = "localhost";
        private static final int SERVER_PORT = 8021;
        public final String username;
        public static int multiplyHeight = 0;
        public static JLabel messageL;
        static PrintWriter out;
        MainWindow wnd = MainWindow.instance;

        public ClientPart(String username) throws IOException {
            this.username = username;
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Connected to server at " + SERVER_ADDRESS + ":" + SERVER_PORT);

            Scanner in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);

            Scanner console = new Scanner(System.in);

            Thread inputThread = new Thread(() -> {
                while (true) {
                    /*String message = in.nextLine();
                    System.out.println(message);*/
                    String message = in.nextLine();
                    if (message.contains("joined")) {
                        continue;
                    }
                    messageL = new JLabel();
                    if (message.contains(":")) {
                        String userMessage = message.substring((message.indexOf(":") + 2));
                        String response = null;

                        if (message.substring(0, message.indexOf(":")).equals(username)) {
                            messageL.setHorizontalAlignment(JLabel.RIGHT);
                            response = wrapLine(userMessage, 25);
                        } else {
                            response = wrapLine(message, 25);
                        }
                        System.out.println(response);

                        setMessageL(response);

                        chatPanel.add(messageL);
                        messages.add(messageL);

                        chatPanel.revalidate();
                        chatPanel.repaint();
                    }
                    wnd.repaint();
                }
            });

            out.println(username);
            /*Thread outputThread = new Thread(() -> {
                while (true) {

                    String message = console.nextLine();
                    out.println(message);
                }
            });*/

            inputThread.start();
            //outputThread.start();
        }

        public static void sendMessage (String message){
            out.println(message);
        }

        private void setMessageL(String response) {
            messageL.setFont(new Font("Manrope ExtraBold", Font.PLAIN, 14));
            messageL.setMaximumSize(new Dimension(3000, 22 * (multiplyHeight + 1)));
            response = "<html>" + response + "</html>";
            messageL.setText(response);
            messageL.setForeground(Color.BLACK);
        }

        public String wrapLine(String line, int Length) {
            int count = 0;
            for (int i = 0; i < line.length(); ++i) {
                if (count == Length) {
                    String first = line.substring(0, i);
                    String second = line.substring(i);
                    line = first + "<br/>" + second;
                    multiplyHeight += 1;
                    count = -1;
                    i += 4;
                    //heeeeeeeeeeeeeeeeeeeeeeee<br/>eeeeeeeeeeeeeeeeeeeeyyyyy<br/>yyyyyyyyyyyyyyyyyyyyyyyyy<br/>yyyyyyyyyyyyyyyyyyyyyyyyy<br/>yyyyyyyyyyyy
                }
                count += 1;
            }
            return line;
        }

    }
}
