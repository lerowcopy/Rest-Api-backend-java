/*
 * Created by JFormDesigner on Fri Mar 22 19:33:43 MSK 2024
 */

package Application.Main.PanelsForMainWindow;

import java.awt.*;
import javax.swing.*;

/**
 * @author 79531
 */
public class addUserPanel extends JPanel {
    public addUserPanel(String login) {
        initComponents();
        userLogin.setText(login);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Misha
        userIcon = new JLabel();
        userLogin = new JLabel();
        addBtn = new JButton();

        //======== this ========
        setPreferredSize(new Dimension(105, 80));
        setMinimumSize(new Dimension(105, 80));
        setMaximumSize(new Dimension(2147483647, 2147483647));
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax .
        swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing .border
        . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069alog"
        , java .awt . Font. BOLD ,12 ) ,java . awt. Color .red ) , getBorder
        () ) );  addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java
        . beans. PropertyChangeEvent e) { if( "\u0062order" .equals ( e. getPropertyName () ) )throw new RuntimeException
        ( ) ;} } );
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {80, 0, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {1.0, 1.0E-4};

        //---- userIcon ----
        userIcon.setText("icon");
        userIcon.setMaximumSize(new Dimension(70, 80));
        userIcon.setMinimumSize(new Dimension(70, 80));
        userIcon.setPreferredSize(new Dimension(70, 80));
        userIcon.setOpaque(true);
        userIcon.setHorizontalAlignment(SwingConstants.CENTER);
        add(userIcon, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.WEST, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        //---- userLogin ----
        userLogin.setText("login");
        userLogin.setFont(new Font("Manrope ExtraBold", Font.PLAIN, 16));
        add(userLogin, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 25, 0), 0, 0));

        //---- addBtn ----
        addBtn.setText("add");
        addBtn.setBorder(null);
        addBtn.setMaximumSize(new Dimension(72, 35));
        addBtn.setMinimumSize(new Dimension(72, 35));
        add(addBtn, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 0, 0), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Misha
    private JLabel userIcon;
    private JLabel userLogin;
    private JButton addBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
