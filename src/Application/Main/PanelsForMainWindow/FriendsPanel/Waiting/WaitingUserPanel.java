/*
 * Created by JFormDesigner on Sun Mar 24 12:08:54 MSK 2024
 */

package Application.Main.PanelsForMainWindow.FriendsPanel.Waiting;

import Application.Main.ApplicationWindow;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.*;

/**
 * @author 79531
 */
public class WaitingUserPanel extends JPanel {

    public WaitingUserPanel(String login) {
        AtomicReference<CloseableHttpClient> client = new AtomicReference<>(HttpClients.createDefault());

        initComponents();
        loginL.setText(login);

        declineBtn.addActionListener(e -> {
            HttpDelete deleteRequest = new HttpDelete(String.format("http://localhost:8000/friendsRequest?loginU=%s&friendLogin=%s", loginL.getText(), ApplicationWindow.name));
            try {
                client.get().execute(deleteRequest);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        acceptBtn.addActionListener(e -> {
            HttpPost postRequest = new HttpPost("http://localhost:8000/friends");
            String entity = String.format("{\"loginU\": \"%s\", \"friendLogin\": \"%s\"}", loginL.getText(), ApplicationWindow.name);
            try {
                StringEntity ent = new StringEntity(entity);
                postRequest.setEntity(ent);

                client.get().execute(postRequest);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            client.set(HttpClients.createDefault());
            entity = String.format("{\"loginU\": \"%s\", \"friendLogin\": \"%s\"}", ApplicationWindow.name, loginL.getText());
            try {
                StringEntity ent = new StringEntity(entity);
                postRequest.setEntity(ent);

                client.get().execute(postRequest);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            client.set(HttpClients.createDefault());

            HttpDelete deleteRequest = new HttpDelete(String.format("http://localhost:8000/friendsRequest?loginU=%s&friendLogin=%s", loginL.getText(), ApplicationWindow.name));
            try {
                client.get().execute(deleteRequest);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            client.set(HttpClients.createDefault());

            deleteRequest = new HttpDelete(String.format("http://localhost:8000/friendsRequest?loginU=%s&friendLogin=%s", ApplicationWindow.name, loginL.getText()));
            try {
                client.get().execute(deleteRequest);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Misha Belyakov
        iconL = new JLabel();
        loginL = new JLabel();
        declineBtn = new JButton();
        acceptBtn = new JButton();

        //======== this ========
        setBorder(null);
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.
        border.EmptyBorder(0,0,0,0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion",javax.swing.border.TitledBorder.CENTER
        ,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog",java.awt.Font
        .BOLD,12),java.awt.Color.red), getBorder())); addPropertyChangeListener(
        new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("bord\u0065r"
        .equals(e.getPropertyName()))throw new RuntimeException();}});
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {73, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {1.0, 1.0E-4};

        //---- iconL ----
        iconL.setText("icon");
        iconL.setHorizontalTextPosition(SwingConstants.CENTER);
        iconL.setHorizontalAlignment(SwingConstants.CENTER);
        add(iconL, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));

        //---- loginL ----
        loginL.setText("login");
        loginL.setFont(new Font("Manrope ExtraBold", Font.PLAIN, 16));
        add(loginL, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 25, 0), 0, 0));

        //---- declineBtn ----
        declineBtn.setText("Decline");
        declineBtn.setBorder(null);
        add(declineBtn, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(15, 0, 15, 10), 0, 0));

        //---- acceptBtn ----
        acceptBtn.setText("Accept");
        acceptBtn.setBorder(null);
        add(acceptBtn, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(15, 0, 15, 0), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Misha Belyakov
    private JLabel iconL;
    private JLabel loginL;
    private JButton declineBtn;
    private JButton acceptBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
