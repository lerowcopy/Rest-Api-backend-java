/*
 * Created by JFormDesigner on Sun Mar 24 12:08:54 MSK 2024
 */

package Application.Main.PanelsForMainWindow.FriendsPanel.Waiting;

import java.awt.*;
import javax.swing.*;

/**
 * @author 79531
 */
public class WaitingUserPanel extends JPanel {
    public WaitingUserPanel(String login) {
        initComponents();
        loginL.setText(login);
        
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Misha
        iconL = new JLabel();
        loginL = new JLabel();
        declineBtn = new JButton();
        acceptBtn = new JButton();

        //======== this ========
        setBorder(null);
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder
        ( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER, javax. swing. border
        . TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt
        . Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void
        propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( )
        ; }} );
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
    // Generated using JFormDesigner Evaluation license - Misha
    private JLabel iconL;
    private JLabel loginL;
    private JButton declineBtn;
    private JButton acceptBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
