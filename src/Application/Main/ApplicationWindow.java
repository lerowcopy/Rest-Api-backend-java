/*
 * Created by JFormDesigner on Fri Mar 22 15:07:50 MSK 2024
 */

package Application.Main;

import java.awt.*;
import javax.swing.*;

import API.Database.Response.ResponseClass.FriendRequest;
import API.Database.Response.TypeResponse.FriendRequestResponse;
import Application.Main.AdditionClass.FriendData;
import Application.Main.PanelsForMainWindow.FriendsPanel.FriendsWindow;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 79531
 */
public class ApplicationWindow extends JFrame implements Runnable{
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8021;
    public static PrintWriter out;
    public static Socket socket;
    private int length;
    private List<FriendData> friends;
    private final Gson gson;
    public static String name;
    public static final FriendsWindow friendsWindow;
    public static ApplicationWindow instance;
    public ApplicationWindow(Gson gson) throws IOException {
        this.gson = gson;
    }
    public ApplicationWindow(String username) throws IOException {
        name = username;
        instance = this;
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
        friends = new ArrayList<>();
        socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        System.out.println("Connected to server at " + socket.getInetAddress() + ":" + socket.getPort());

        out = new PrintWriter(socket.getOutputStream(), true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(770, 500);
        setPreferredSize(new Dimension(770, 500));
        setMinimumSize(new Dimension(770, 500));

        setLocationRelativeTo(null);
        initComponents();
        GridBagConstraints gbc = new GridBagConstraints(
                0, 0, 5, 5, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0
        );
        add(friendsWindow, gbc);
        friendsWindow.setVisible(false);

        friendsBtn.addActionListener(e -> {
            friendsBtn.setVisible(false);
            forBtnPanel.setVisible(false);
            friendsWindow.setVisible(true);
        });
        new Thread(this).start();
    }

    private void initComponents() throws IOException {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Misha Belyakov
        forBtnPanel = new JPanel();
        friendsBtn = new JButton();
        scrollPane1 = new JScrollPane();
        friendsPanel = new JPanel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {47, 132, 320, 0};
        ((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {0, 0};
        ((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {1.0, 1.0E-4};

        //======== forBtnPanel ========
        {
            forBtnPanel.setPreferredSize(new Dimension(0, 2222));
            forBtnPanel.setMinimumSize(new Dimension(0, 2222));
            forBtnPanel.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing
            . border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder
            . CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .
            awt .Font .BOLD ,12 ), java. awt. Color. red) ,forBtnPanel. getBorder( )) )
            ; forBtnPanel. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
            ) {if ("\u0062order" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} )
            ;
            forBtnPanel.setLayout(new BoxLayout(forBtnPanel, BoxLayout.Y_AXIS));

            //---- friendsBtn ----
            friendsBtn.setMaximumSize(new Dimension(60, 50));
            friendsBtn.setIcon(new ImageIcon(getClass().getResource("/Application/Auth/Icons/friends.png")));
            friendsBtn.setBorder(null);
            friendsBtn.setContentAreaFilled(false);
            friendsBtn.setFocusPainted(false);
            forBtnPanel.add(friendsBtn);
        }
        contentPane.add(forBtnPanel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 0, 0), 0, 0));

        //======== scrollPane1 ========
        {
            scrollPane1.setMinimumSize(new Dimension(16, 2222));
            scrollPane1.setBorder(null);

            //======== friendsPanel ========
            {
                friendsPanel.setLayout(new BoxLayout(friendsPanel, BoxLayout.Y_AXIS));
            }
            scrollPane1.setViewportView(friendsPanel);
        }
        contentPane.add(scrollPane1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 0, 0), 0, 0));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Misha Belyakov
    public static JPanel forBtnPanel;
    public static JButton friendsBtn;
    private JScrollPane scrollPane1;
    private JPanel friendsPanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    static {
        try {
            friendsWindow = new FriendsWindow();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (true){
            CloseableHttpClient client = HttpClients.createDefault();

            HttpGet getRequest = new HttpGet(String.format("http://localhost:8000/friends?loginU=%s", name));

            try {
                HttpEntity entity = client.execute(getRequest).getEntity();
                String strEntity = EntityUtils.toString(entity);

                if (strEntity.length() != length && !strEntity.contains("Bad Request")){
                    FriendRequestResponse response = gson.fromJson(strEntity, FriendRequestResponse.class);

                    length = strEntity.length();

                    for (FriendRequest data : response.data){
                        System.out.println(data.getFriendLogin());
                        friends.add(new FriendData("null",data.getFriendLogin()));
                    }
                    for (FriendData data : friends){
                        System.out.println(data.getLogin());
                        JButton btn = new JButton(data.getLogin());
                        btn.setBackground(Color.BLACK);
                        friendsPanel.add(btn);
                        friendsPanel.repaint();
                        friendsPanel.revalidate();
                    }
                    //добавить измененние length
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
