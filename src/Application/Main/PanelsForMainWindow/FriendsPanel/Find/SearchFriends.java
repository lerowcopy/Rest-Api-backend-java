/*
 * Created by JFormDesigner on Fri Mar 22 22:15:39 MSK 2024
 */

package Application.Main.PanelsForMainWindow.FriendsPanel.Find;

import Application.Main.ApplicationWindow;
import API.Database.Database;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * @author 79531
 */
public class SearchFriends extends JPanel {
    public SearchFriends() throws SQLException {
        initComponents();
        searchBtn.addActionListener(e -> {
            Search();
        });
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchUsers(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchUsers(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchUsers(e);
            }

            private void searchUsers(DocumentEvent e) {
                Search();
            }
        });
    }

    private void Search() {
        usersPanel.removeAll();
        usersPanel.repaint();

        String query = searchField.getText();
        if (query.isEmpty()) {
            usersPanel.removeAll();
            usersPanel.repaint();
        } else {
            try {
                int index = 0;
                List<String> users = db.GETUsers(Database.con, query);
                List<String> friends = db.GETFriends(Database.con, ApplicationWindow.name);
                for (String user : users) {
                    if (!user.equals(ApplicationWindow.name) && !friends.contains(user)) {
                        CloseableHttpClient client = HttpClients.createDefault();
                        HttpGet request = new HttpGet(String.format("http://localhost:8000/friendsRequest?loginU=%s&friendLogin=%s", ApplicationWindow.name, user));
                        String response = client.execute(request).toString();
                        addFriendPanel userPanel = new addFriendPanel(user, index++);
                        if (response.contains("OK")) {
                            userPanel.addBtn.setText("cancel");
                        }
                        userPanel.setMaximumSize(new Dimension((int) usersPanel.getSize().getWidth(), 80));
                        userPanel.setMinimumSize(new Dimension((int) usersPanel.getSize().getWidth(), 80));
                        usersPanel.add(userPanel);
                        usersPanel.repaint();
                        usersPanel.revalidate();
                    }
                }
            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Misha Belyakov
        searchField = new JTextField();
        searchBtn = new JButton();
        scrollPane = new JScrollPane();
        usersPanel = new JPanel();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder
        (0,0,0,0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion",javax.swing.border.TitledBorder.CENTER,javax.swing.border
        .TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt
        .Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void
        propertyChange(java.beans.PropertyChangeEvent e){if("bord\u0065r".equals(e.getPropertyName()))throw new RuntimeException()
        ;}});
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {41, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {1.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 1.0, 1.0E-4};

        //---- searchField ----
        searchField.setPreferredSize(new Dimension(350, 30));
        searchField.setMinimumSize(new Dimension(370, 35));
        searchField.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
        add(searchField, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 10, 5, 10), 0, 0));

        //---- searchBtn ----
        searchBtn.setBorder(null);
        searchBtn.setHorizontalAlignment(SwingConstants.LEFT);
        searchBtn.setForeground(Color.white);
        searchBtn.setIcon(new ImageIcon(getClass().getResource("/Application/Auth/Icons/search.png")));
        searchBtn.setMaximumSize(new Dimension(24, 24));
        searchBtn.setMinimumSize(new Dimension(24, 24));
        searchBtn.setPreferredSize(new Dimension(35, 35));
        searchBtn.setContentAreaFilled(false);
        searchBtn.setFocusPainted(false);
        add(searchBtn, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
            new Insets(5, 5, 5, 40), 0, 0));

        //======== scrollPane ========
        {
            scrollPane.setMinimumSize(new Dimension(333, 2222));
            scrollPane.setBorder(null);

            //======== usersPanel ========
            {
                usersPanel.setLayout(new BoxLayout(usersPanel, BoxLayout.Y_AXIS));
            }
            scrollPane.setViewportView(usersPanel);
        }
        add(scrollPane, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 5, 5), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Misha Belyakov
    public static JTextField searchField;
    private JButton searchBtn;
    private JScrollPane scrollPane;
    private JPanel usersPanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    Database db = new Database();
}
