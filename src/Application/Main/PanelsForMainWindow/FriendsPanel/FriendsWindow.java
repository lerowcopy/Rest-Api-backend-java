/*
 * Created by JFormDesigner on Fri Mar 22 15:28:40 MSK 2024
 */

package Application.Main.PanelsForMainWindow.FriendsPanel;

import Application.Main.ApplicationWindow;
import API.Database.Database;
import Application.Main.PanelsForMainWindow.FriendsPanel.All.AllPanel;
import Application.Main.PanelsForMainWindow.FriendsPanel.Find.SearchFriends;
import Application.Main.PanelsForMainWindow.FriendsPanel.Waiting.WaitingPanel;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.*;

/**
 * @author 79531
 */
public class FriendsWindow extends JPanel {
    public FriendsWindow() throws SQLException, IOException {
        initComponents();

        JPanel searchPanel = new SearchFriends();
        JPanel waitingPanel = new WaitingPanel();
        JPanel allPanel = new AllPanel();
        friendsPanel.add(searchPanel, BorderLayout.CENTER);
        friendsPanel.add(waitingPanel, BorderLayout.CENTER);
        friendsPanel.add(allPanel, BorderLayout.CENTER);
        searchPanel.setVisible(false);
        waitingPanel.setVisible(false);
        allPanel.setVisible(true);

        closeBtn.addActionListener(e -> {
            ApplicationWindow.friendsWindow.setVisible(false);
            ApplicationWindow.forBtnPanel.setVisible(true);
            ApplicationWindow.friendsBtn.setVisible(true);
        });
        findBtn.addActionListener(e -> {
            searchPanel.setVisible(true);
            waitingPanel.setVisible(false);
            allPanel.setVisible(false);
        });
        waitingBtn.addActionListener(e -> {
            waitingPanel.setVisible(true);
            searchPanel.setVisible(false);
            allPanel.setVisible(false);
        });

        allBtn.addActionListener(e -> {
            allPanel.setVisible(true);
            waitingPanel.setVisible(false);
            searchPanel.setVisible(false);
        });


    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Misha
        closeBtn = new JButton();
        switchFriendsPanel = new JPanel();
        allBtn = new JButton();
        waitingBtn = new JButton();
        findBtn = new JButton();
        friendsPanel = new JPanel();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new
        javax.swing.border.EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax
        .swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java
        .awt.Font("D\u0069alog",java.awt.Font.BOLD,12),java.awt
        .Color.red), getBorder())); addPropertyChangeListener(new java.beans.
        PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062order".
        equals(e.getPropertyName()))throw new RuntimeException();}});
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {401, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {18, 0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {1.0, 1.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0, 1.0E-4};

        //---- closeBtn ----
        closeBtn.setIcon(new ImageIcon(getClass().getResource("/Application/Auth/Icons/wrong.png")));
        closeBtn.setPreferredSize(new Dimension(20, 20));
        closeBtn.setMinimumSize(new Dimension(20, 20));
        closeBtn.setBorder(null);
        closeBtn.setFocusPainted(false);
        closeBtn.setContentAreaFilled(false);
        add(closeBtn, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
            new Insets(3, 0, 0, 3), 0, 0));

        //======== switchFriendsPanel ========
        {
            switchFriendsPanel.setLayout(new GridBagLayout());
            ((GridBagLayout)switchFriendsPanel.getLayout()).columnWidths = new int[] {89, 88, 0, 0};
            ((GridBagLayout)switchFriendsPanel.getLayout()).rowHeights = new int[] {0, 0};
            ((GridBagLayout)switchFriendsPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)switchFriendsPanel.getLayout()).rowWeights = new double[] {1.0, 1.0E-4};

            //---- allBtn ----
            allBtn.setText("All");
            allBtn.setBorder(null);
            allBtn.setMaximumSize(new Dimension(72, 25));
            allBtn.setMinimumSize(new Dimension(72, 25));
            allBtn.setPreferredSize(new Dimension(72, 25));
            allBtn.setContentAreaFilled(false);
            allBtn.setFocusPainted(false);
            switchFriendsPanel.add(allBtn, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 0, 0));

            //---- waitingBtn ----
            waitingBtn.setText("Waiting");
            waitingBtn.setBorder(null);
            waitingBtn.setMaximumSize(new Dimension(78, 25));
            waitingBtn.setMinimumSize(new Dimension(78, 25));
            waitingBtn.setPreferredSize(new Dimension(78, 25));
            waitingBtn.setContentAreaFilled(false);
            waitingBtn.setFocusPainted(false);
            switchFriendsPanel.add(waitingBtn, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 0, 0));

            //---- findBtn ----
            findBtn.setText("Find");
            findBtn.setBorder(null);
            findBtn.setMaximumSize(new Dimension(72, 25));
            findBtn.setMinimumSize(new Dimension(72, 25));
            findBtn.setPreferredSize(new Dimension(78, 25));
            findBtn.setContentAreaFilled(false);
            findBtn.setFocusPainted(false);
            switchFriendsPanel.add(findBtn, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        add(switchFriendsPanel, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));

        //======== friendsPanel ========
        {
            friendsPanel.setLayout(new BoxLayout(friendsPanel, BoxLayout.PAGE_AXIS));
        }
        add(friendsPanel, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Misha
    private JButton closeBtn;
    private JPanel switchFriendsPanel;
    private JButton allBtn;
    private JButton waitingBtn;
    private JButton findBtn;
    private JPanel friendsPanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    Database db = new Database();

}
