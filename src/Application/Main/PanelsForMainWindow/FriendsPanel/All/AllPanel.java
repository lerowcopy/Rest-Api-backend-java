/*
 * Created by JFormDesigner on Mon Mar 25 22:55:03 MSK 2024
 */

package Application.Main.PanelsForMainWindow.FriendsPanel.All;

import API.Database.Response.ResponseClass.FriendRequest;
import API.Database.Response.TypeResponse.FriendRequestResponse;
import Application.Main.ApplicationWindow;
import Application.Main.PanelsForMainWindow.FriendsPanel.Waiting.WaitingUserPanel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * @author 79531
 */
public class AllPanel extends JPanel implements Runnable {
    private int length;
    private final GsonBuilder builder = new GsonBuilder();
    private final Gson gson = builder.create();
    public AllPanel() {
        initComponents();
        new Thread(this).start();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Misha Belyakov
        scrollPane1 = new JScrollPane();
        friendsPanel = new JPanel();

        //======== this ========
        setPreferredSize(new Dimension(2, 2));
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax .
        swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing .border
        . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog"
        , java .awt . Font. BOLD ,12 ) ,java . awt. Color .red ) , getBorder
        () ) );  addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java
        . beans. PropertyChangeEvent e) { if( "bord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException
        ( ) ;} } );
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //======== scrollPane1 ========
        {
            scrollPane1.setPreferredSize(new Dimension(12, 12));

            //======== friendsPanel ========
            {
                friendsPanel.setLayout(new BoxLayout(friendsPanel, BoxLayout.Y_AXIS));
            }
            scrollPane1.setViewportView(friendsPanel);
        }
        add(scrollPane1);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Misha Belyakov
    private JScrollPane scrollPane1;
    private JPanel friendsPanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    @Override
    public void run() {
        while (true) {
            CloseableHttpClient client = HttpClients.createDefault();

            HttpGet get = new HttpGet(String.format("http://localhost:8000/friends?loginU=%s", ApplicationWindow.name));

            try {
                HttpEntity entity = client.execute(get).getEntity();
                String strEntity = EntityUtils.toString(entity);
                if (strEntity.length() != length && !strEntity.equals("Bad Request")) {
                    FriendRequestResponse response = gson.fromJson(strEntity, FriendRequestResponse.class);
                    friendsPanel.removeAll();
                    for (FriendRequest friend : response.data) {
                        FriendPanel panel = new FriendPanel(friend.getFriendLogin());
                        panel.setMaximumSize(new Dimension(2222, 80));
                        panel.setMinimumSize(new Dimension(2222, 80));
                        friendsPanel.add(panel);
                        friendsPanel.repaint();
                        friendsPanel.revalidate();
                    }
                    length = strEntity.length();
                }
                else if (strEntity.equals("Bad Request")) {
                    friendsPanel.removeAll();
                    friendsPanel.repaint();
                    friendsPanel.revalidate();
                    length = 0;
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
