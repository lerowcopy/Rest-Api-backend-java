/*
 * Created by JFormDesigner on Mon Mar 25 22:55:28 MSK 2024
 */

package Application.Main.PanelsForMainWindow.FriendsPanel.All;

import Application.Main.ApplicationWindow;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;

/**
 * @author 79531
 */
public class FriendPanel extends JPanel {
    public FriendPanel(String name) {
        initComponents();
        loginL.setText(name);

        deleteBtn.addActionListener(e -> {
            CloseableHttpClient client = HttpClients.createDefault();

            HttpDelete httpDelete = new HttpDelete(String.format("http://localhost:8000/friends?loginU=%s&friendLogin=%s", ApplicationWindow.name, loginL.getText()));

            try {
                client.execute(httpDelete);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            httpDelete = new HttpDelete(String.format("http://localhost:8000/friends?loginU=%s&friendLogin=%s", loginL.getText(), ApplicationWindow.name));

            try {
                client.execute(httpDelete);
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
        deleteBtn = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(192, 54));
        setPreferredSize(new Dimension(192, 54));
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .EmptyBorder (
        0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax. swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder
        . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,java . awt. Color .
        red ) , getBorder () ) );  addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java .
        beans. PropertyChangeEvent e) { if( "\u0062ord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {82, 0, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {1.0, 1.0E-4};

        //---- iconL ----
        iconL.setText("icon");
        iconL.setMaximumSize(new Dimension(28, 2222));
        iconL.setMinimumSize(new Dimension(28, 2222));
        iconL.setPreferredSize(new Dimension(28, 2222));
        iconL.setHorizontalTextPosition(SwingConstants.CENTER);
        iconL.setHorizontalAlignment(SwingConstants.CENTER);
        add(iconL, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 0, 0), 0, 0));

        //---- loginL ----
        loginL.setText("text");
        loginL.setFont(new Font("Manrope ExtraBold", Font.PLAIN, 16));
        add(loginL, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 25, 0), 0, 0));

        //---- deleteBtn ----
        deleteBtn.setText("delete");
        deleteBtn.setBorder(null);
        deleteBtn.setPreferredSize(new Dimension(72, 30));
        deleteBtn.setMaximumSize(new Dimension(72, 40));
        deleteBtn.setMinimumSize(new Dimension(72, 40));
        add(deleteBtn, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 0, 5), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Misha Belyakov
    private JLabel iconL;
    private JLabel loginL;
    private JButton deleteBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
