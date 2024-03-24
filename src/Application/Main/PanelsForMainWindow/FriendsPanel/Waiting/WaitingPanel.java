/*
 * Created by JFormDesigner on Sun Mar 24 12:06:07 MSK 2024
 */

package Application.Main.PanelsForMainWindow.FriendsPanel.Waiting;

import API.Database.Response.ResponseClass.FriendRequest;
import API.Database.Response.TypeResponse.FriendRequestResponse;
import Application.Main.ApplicationWindow;
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
public class WaitingPanel extends JPanel implements Runnable {
    CloseableHttpClient client = HttpClients.createDefault();
    int length;
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();

    public WaitingPanel() throws IOException {
        initComponents();
        new Thread(this).start();

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Misha
        scrollPane1 = new JScrollPane();
        waitingPanel = new JPanel();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing
        . border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder
        . CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .
        awt .Font .BOLD ,12 ), java. awt. Color. red) , getBorder( )) )
        ;  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
        ) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} )
        ;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //======== scrollPane1 ========
        {

            //======== waitingPanel ========
            {
                waitingPanel.setBackground(Color.white);
                waitingPanel.setLayout(new BoxLayout(waitingPanel, BoxLayout.Y_AXIS));
            }
            scrollPane1.setViewportView(waitingPanel);
        }
        add(scrollPane1);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Misha
    private JScrollPane scrollPane1;
    private JPanel waitingPanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on


    @Override
    public void run() {
        while (true) {
            client = HttpClients.createDefault();
            if (ApplicationWindow.name != null) {
                HttpGet request = new HttpGet(String.format("http://localhost:8000/friendsRequest?friendLogin=%s", ApplicationWindow.name));
                try {
                    HttpEntity entity = client.execute(request).getEntity();
                    String strEntity = EntityUtils.toString(entity);
                    if (strEntity.length() != length && !strEntity.equals("Bad Request")) {
                        System.out.println(strEntity);
                        FriendRequestResponse response = gson.fromJson(strEntity, FriendRequestResponse.class);
                        waitingPanel.removeAll();
                        for (FriendRequest friend : response.data) {
                            WaitingUserPanel panel = new WaitingUserPanel(friend.getLoginU());
                            panel.setMaximumSize(new Dimension(2222, 80));
                            panel.setMinimumSize(new Dimension(2222, 80));
                            waitingPanel.add(panel);
                            waitingPanel.repaint();
                            waitingPanel.revalidate();
                        }
                        length = strEntity.length();
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
}
