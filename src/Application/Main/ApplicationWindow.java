/*
 * Created by JFormDesigner on Fri Mar 22 15:07:50 MSK 2024
 */

package Application.Main;

import javax.swing.*;
import Application.Main.PanelsForMainWindow.*;

import java.io.IOException;

/**
 * @author 79531
 */
public class ApplicationWindow extends JFrame {
    private final String name;
    public ApplicationWindow(String username) throws IOException {
        name = username;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() throws IOException {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Misha
        chatPanel1 = new ChatPanel(name);

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
        contentPane.add(chatPanel1);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Misha
    private ChatPanel chatPanel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
