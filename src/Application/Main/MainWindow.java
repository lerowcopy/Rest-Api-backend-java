/*
 * Created by JFormDesigner on Tue Mar 12 00:33:48 MSK 2024
 */

package Application.Main;

import com.sun.tools.javac.Main;

import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
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
        panel1 = new JPanel();
        LeftArea = new JTextArea();
        RightArea = new JTextArea();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

        //======== scrollPane1 ========
        {

            //======== panel1 ========
            {
                panel1.setBackground(new Color(0x333333));
                panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border
                .EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing.border.TitledBorder.CENTER,javax
                .swing.border.TitledBorder.BOTTOM,new java.awt.Font("D\u0069alog",java.awt.Font.BOLD,
                12),java.awt.Color.red),panel1. getBorder()));panel1. addPropertyChangeListener(new java.beans
                .PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062order".equals(e.
                getPropertyName()))throw new RuntimeException();}});
                panel1.setLayout(new GridBagLayout());
                ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 0};
                ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0};
                ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.01, 0.01, 1.0E-4};
                ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.01, 1.0E-4};

                //---- LeftArea ----
                LeftArea.setBackground(new Color(0x333333));
                LeftArea.setLineWrap(true);
                LeftArea.setForeground(Color.white);
                panel1.add(LeftArea, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                    new Insets(0, 0, 0, 0), 0, 0));

                //---- RightArea ----
                RightArea.setBackground(new Color(0x333333));
                RightArea.setLineWrap(true);
                RightArea.setForeground(Color.white);
                panel1.add(RightArea, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.NORTHEAST, GridBagConstraints.NONE,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            scrollPane1.setViewportView(panel1);
        }
        contentPane.add(scrollPane1);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Misha
    private JScrollPane scrollPane1;
    private JPanel panel1;
    public static JTextArea LeftArea;
    public static JTextArea RightArea;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

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
                    if (message.contains("joined")){
                        continue;
                    }
                    String substring = message.substring(message.indexOf(username) + username.length());
                    if (message.contains(username)) {
                        if (RightArea != null) {
                            RightArea.setText(RightArea.getText() + "\n" + username + ": " + substring);
                        }
                    }
                    else{
                        if (LeftArea != null){
                            LeftArea.setText(LeftArea.getText() + "\n" + username + ": " + substring);
                        }
                    }
                    wnd.repaint();
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
        }
    }
}
