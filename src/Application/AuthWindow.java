/*
 * Created by JFormDesigner on Sat Mar 09 22:09:50 MSK 2024
 */

package Application;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author 79531
 */
public class AuthWindow extends JFrame {
    public AuthWindow() {
        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(419, 607);
        loginBtn.setBorder(new RoundedBorder(25));
        cancelBtn.setBorder(new RoundedBorder(25));
        registerBtn.setBorder(new RoundedBorder(25));
        
        
        loginPanel.setVisible(true);
        signUpPanel.setVisible(false);

        signUpBtn.addActionListener(e -> {
            signUpPanel.setVisible(true);
            loginPanel.setVisible(false);
            repaint();
        });

        LoginBtn.addActionListener(e -> {
            loginPanel.setVisible(true);
            signUpPanel.setVisible(false);
            repaint();
        });

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Misha
        LoginBtn = new JButton();
        signUpBtn = new JButton();
        signUpPanel = new JPanel();
        loginField2 = new JTextField();
        loginL2 = new JLabel();
        emailFIeld = new JTextField();
        emailL = new JLabel();
        firstNameField = new JTextField();
        firstNameL = new JLabel();
        secondNameField = new JTextField();
        secondNameL = new JLabel();
        passwordField2 = new JPasswordField();
        passwordL2 = new JLabel();
        registerBtn = new JButton();
        cancelBtn = new JButton();
        loginPanel = new JPanel();
        loginField = new JTextField();
        loginL = new JLabel();
        passwordL = new JLabel();
        passwordField1 = new JPasswordField();
        loginBtn = new JButton();

        //======== this ========
        setBackground(new Color(0x333333));
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- LoginBtn ----
        LoginBtn.setText("Sign In");
        LoginBtn.setBorder(new MatteBorder(0, 0, 3, 0, Color.white));
        LoginBtn.setFont(new Font("Manrope ExtraBold", Font.BOLD, 17));
        LoginBtn.setBackground(new Color(0x333333));
        LoginBtn.setForeground(Color.white);
        LoginBtn.setFocusPainted(false);
        LoginBtn.setContentAreaFilled(false);
        contentPane.add(LoginBtn);
        LoginBtn.setBounds(80, 65, 88, 40);

        //---- signUpBtn ----
        signUpBtn.setText("Sign Up");
        signUpBtn.setBorder(new MatteBorder(0, 0, 3, 0, Color.white));
        signUpBtn.setFont(new Font("Manrope ExtraBold", Font.BOLD, 17));
        signUpBtn.setBackground(new Color(0x333333));
        signUpBtn.setForeground(Color.white);
        signUpBtn.setFocusPainted(false);
        signUpBtn.setContentAreaFilled(false);
        contentPane.add(signUpBtn);
        signUpBtn.setBounds(220, 65, 88, 40);

        //======== signUpPanel ========
        {
            signUpPanel.setBackground(new Color(0x333333));
            signUpPanel.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .EmptyBorder
            ( 0, 0 ,0 , 0) ,  "" , javax. swing .border . TitledBorder. CENTER ,javax . swing. border
            .TitledBorder . BOTTOM, new java. awt .Font ( "Dialo\u0067", java .awt . Font. BOLD ,12 ) ,java . awt
            . Color .red ) ,signUpPanel. getBorder () ) ); signUpPanel. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void
            propertyChange (java . beans. PropertyChangeEvent e) { if( "borde\u0072" .equals ( e. getPropertyName () ) )throw new RuntimeException( )
            ;} } );
            signUpPanel.setLayout(null);

            //---- loginField2 ----
            loginField2.setBorder(new MatteBorder(0, 0, 2, 0, Color.white));
            loginField2.setFont(new Font("Manrope ExtraBold", Font.PLAIN, 20));
            loginField2.setBackground(new Color(0x333333));
            signUpPanel.add(loginField2);
            loginField2.setBounds(80, 215, 230, 34);

            //---- loginL2 ----
            loginL2.setText("login");
            loginL2.setFont(new Font("Fira Code", Font.PLAIN, 12));
            loginL2.setForeground(Color.white);
            signUpPanel.add(loginL2);
            loginL2.setBounds(80, 195, 36, 16);

            //---- emailFIeld ----
            emailFIeld.setBorder(new MatteBorder(0, 0, 2, 0, Color.white));
            emailFIeld.setFont(new Font("Manrope ExtraBold", Font.PLAIN, 20));
            emailFIeld.setBackground(new Color(0x333333));
            signUpPanel.add(emailFIeld);
            emailFIeld.setBounds(80, 280, 230, 34);

            //---- emailL ----
            emailL.setText("email");
            emailL.setFont(new Font("Fira Code", Font.PLAIN, 12));
            emailL.setForeground(Color.white);
            signUpPanel.add(emailL);
            emailL.setBounds(80, 260, 36, 16);

            //---- firstNameField ----
            firstNameField.setBorder(new MatteBorder(0, 0, 2, 0, Color.white));
            firstNameField.setFont(new Font("Manrope ExtraBold", Font.PLAIN, 20));
            firstNameField.setBackground(new Color(0x333333));
            signUpPanel.add(firstNameField);
            firstNameField.setBounds(80, 415, 230, 34);

            //---- firstNameL ----
            firstNameL.setText("first name");
            firstNameL.setFont(new Font("Fira Code", Font.PLAIN, 12));
            firstNameL.setForeground(Color.white);
            signUpPanel.add(firstNameL);
            firstNameL.setBounds(80, 395, 90, 16);

            //---- secondNameField ----
            secondNameField.setBorder(new MatteBorder(0, 0, 2, 0, Color.white));
            secondNameField.setFont(new Font("Manrope ExtraBold", Font.PLAIN, 20));
            secondNameField.setBackground(new Color(0x333333));
            signUpPanel.add(secondNameField);
            secondNameField.setBounds(80, 480, 230, 34);

            //---- secondNameL ----
            secondNameL.setText("second name");
            secondNameL.setFont(new Font("Fira Code", Font.PLAIN, 12));
            secondNameL.setForeground(Color.white);
            signUpPanel.add(secondNameL);
            secondNameL.setBounds(80, 460, 85, 16);

            //---- passwordField2 ----
            passwordField2.setFont(new Font("Manrope ExtraBold", Font.PLAIN, 20));
            passwordField2.setBorder(new MatteBorder(0, 0, 2, 0, Color.white));
            passwordField2.setBackground(new Color(0x333333));
            signUpPanel.add(passwordField2);
            passwordField2.setBounds(80, 345, 230, 34);

            //---- passwordL2 ----
            passwordL2.setText("Password");
            passwordL2.setFont(new Font("Fira Code", Font.PLAIN, 12));
            passwordL2.setForeground(Color.white);
            signUpPanel.add(passwordL2);
            passwordL2.setBounds(80, 325, 58, 16);

            //---- registerBtn ----
            registerBtn.setText("Register");
            registerBtn.setBorder(new LineBorder(Color.darkGray, 1, true));
            registerBtn.setForeground(Color.white);
            registerBtn.setBackground(new Color(0x333333));
            registerBtn.setContentAreaFilled(false);
            registerBtn.setFocusPainted(false);
            signUpPanel.add(registerBtn);
            registerBtn.setBounds(220, 540, 100, 35);

            //---- cancelBtn ----
            cancelBtn.setText("Cancel");
            cancelBtn.setBorder(new LineBorder(Color.darkGray, 1, true));
            cancelBtn.setForeground(Color.white);
            cancelBtn.setBackground(new Color(0x333333));
            cancelBtn.setContentAreaFilled(false);
            cancelBtn.setFocusPainted(false);
            signUpPanel.add(cancelBtn);
            cancelBtn.setBounds(85, 540, 100, 35);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < signUpPanel.getComponentCount(); i++) {
                    Rectangle bounds = signUpPanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = signUpPanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                signUpPanel.setMinimumSize(preferredSize);
                signUpPanel.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(signUpPanel);
        signUpPanel.setBounds(0, -40, 405, 610);

        //======== loginPanel ========
        {
            loginPanel.setBackground(new Color(0x333333));
            loginPanel.setVisible(false);
            loginPanel.setLayout(null);

            //---- loginField ----
            loginField.setBorder(new MatteBorder(0, 0, 2, 0, Color.white));
            loginField.setFont(new Font("Manrope ExtraBold", Font.PLAIN, 20));
            loginField.setBackground(new Color(0x333333));
            loginPanel.add(loginField);
            loginField.setBounds(75, 225, 230, loginField.getPreferredSize().height);

            //---- loginL ----
            loginL.setText("login");
            loginL.setFont(new Font("Fira Code", Font.PLAIN, 12));
            loginL.setForeground(Color.white);
            loginPanel.add(loginL);
            loginL.setBounds(new Rectangle(new Point(80, 205), loginL.getPreferredSize()));

            //---- passwordL ----
            passwordL.setText("Password");
            passwordL.setFont(new Font("Fira Code", Font.PLAIN, 12));
            passwordL.setForeground(Color.white);
            loginPanel.add(passwordL);
            passwordL.setBounds(new Rectangle(new Point(80, 275), passwordL.getPreferredSize()));

            //---- passwordField1 ----
            passwordField1.setFont(new Font("Manrope ExtraBold", Font.PLAIN, 20));
            passwordField1.setBorder(new MatteBorder(0, 0, 2, 0, Color.white));
            passwordField1.setBackground(new Color(0x333333));
            loginPanel.add(passwordField1);
            passwordField1.setBounds(80, 305, 230, passwordField1.getPreferredSize().height);

            //---- loginBtn ----
            loginBtn.setText("Login");
            loginBtn.setBorder(new LineBorder(Color.darkGray, 1, true));
            loginBtn.setForeground(Color.white);
            loginBtn.setBackground(new Color(0x333333));
            loginBtn.setContentAreaFilled(false);
            loginBtn.setFocusPainted(false);
            loginPanel.add(loginBtn);
            loginBtn.setBounds(145, 375, 100, 35);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < loginPanel.getComponentCount(); i++) {
                    Rectangle bounds = loginPanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = loginPanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                loginPanel.setMinimumSize(preferredSize);
                loginPanel.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(loginPanel);
        loginPanel.setBounds(0, -40, 405, 610);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Misha
    private JButton LoginBtn;
    private JButton signUpBtn;
    private JPanel signUpPanel;
    private JTextField loginField2;
    private JLabel loginL2;
    private JTextField emailFIeld;
    private JLabel emailL;
    private JTextField firstNameField;
    private JLabel firstNameL;
    private JTextField secondNameField;
    private JLabel secondNameL;
    private JPasswordField passwordField2;
    private JLabel passwordL2;
    private JButton registerBtn;
    private JButton cancelBtn;
    private JPanel loginPanel;
    private JTextField loginField;
    private JLabel loginL;
    private JLabel passwordL;
    private JPasswordField passwordField1;
    private JButton loginBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    


    public static void main(String[] args){
        AuthWindow wnd = new AuthWindow();
        wnd.setVisible(true);
    }    
}
