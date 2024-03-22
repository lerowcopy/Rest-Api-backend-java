/*
 * Created by JFormDesigner on Fri Mar 22 15:07:50 MSK 2024
 */

package Application.Main;

import java.awt.*;
import javax.swing.*;
import Application.Main.PanelsForMainWindow.*;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author 79531
 */
public class ApplicationWindow extends JFrame {
    public static ApplicationWindow instance;
    public static String name;
    public ApplicationWindow() throws IOException {}
    public ApplicationWindow(String username) throws IOException {
        instance = this;
        name = username;

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
    }

    private void initComponents() throws IOException {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Misha
        forBtnPanel = new JPanel();
        friendsBtn = new JButton();

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
            forBtnPanel.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
            EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax. swing
            . border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ),
            java. awt. Color. red) ,forBtnPanel. getBorder( )) ); forBtnPanel. addPropertyChangeListener (new java. beans. PropertyChangeListener( )
            { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () ))
            throw new RuntimeException( ); }} );
            forBtnPanel.setLayout(new BoxLayout(forBtnPanel, BoxLayout.Y_AXIS));

            //---- friendsBtn ----
            friendsBtn.setText("text");
            friendsBtn.setMaximumSize(new Dimension(60, 50));
            forBtnPanel.add(friendsBtn);
        }
        contentPane.add(forBtnPanel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 0, 0), 0, 0));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Misha
    public static JPanel forBtnPanel;
    public static JButton friendsBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public static final FriendsWindow friendsWindow;

    static {
        try {
            friendsWindow = new FriendsWindow();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
