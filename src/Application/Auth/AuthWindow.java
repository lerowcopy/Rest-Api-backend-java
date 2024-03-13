/*
 * Created by JFormDesigner on Sat Mar 09 22:09:50 MSK 2024
 */

package Application.Auth;

import Application.Auth.Listeners.*;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.awt.*;
import java.io.UnsupportedEncodingException;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author 79531
 */
public class AuthWindow extends JFrame {
    public static AuthWindow instance;

    public AuthWindow() {
        instance = this;
        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(414, 607);
        loginBtn.setBorder(new RoundedBorder(25));
        cancelBtn.setBorder(new RoundedBorder(25));
        registerBtn.setBorder(new RoundedBorder(25));


        loginPanel.setVisible(true);
        signUpPanel.setVisible(false);

        signUpBtn.addActionListener(new SignUpAction());
        LoginBtn.addActionListener(new LoginAction());
        loginBtn.addActionListener(new userLoginAction());
        registerBtn.addActionListener(new userSignUpAction());
        loginField2.getDocument().addDocumentListener(new LoginDocument());
        emailField.getDocument().addDocumentListener(new EmailDocument());

    }


    public HttpPost getHttpPostForLogin() {
        HttpPost httpPost = new HttpPost("http://localhost:8000/auth/login");

        // Установите тело запроса (например, JSON)
        String requestBody = null;
        if (loginField.getText().contains("@")) {
            requestBody = String.format("{email:\"%s\"}", loginField.getText());
        } else {
            requestBody = String.format("{login:\"%s\"}", loginField.getText());
        }
        StringEntity entity = null;
        try {
            entity = new StringEntity(requestBody);
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
        httpPost.setEntity(entity);
        return httpPost;
    }

    public HttpPost getHttpPostForRegister() {
        HttpPost httpPost = new HttpPost("http://localhost:8000/auth/register");

        // Установите тело запроса (например, JSON)
        String requestBody = String.format("{login:\"%s\", email:\"%s\", password:\"%s\", firstName:\"%s\", secondName:\"%s\"}",
                loginField2.getText(), emailField.getText(), new String(passwordField2.getPassword()), firstNameField.getText(), secondNameField.getText());
        StringEntity entity = null;
        try {
            entity = new StringEntity(requestBody);
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
        httpPost.setEntity(entity);
        return httpPost;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Misha
        LoginBtn = new JButton();
        signUpBtn = new JButton();
        signUpPanel = new JPanel();
        loginField2 = new JTextField();
        loginL2 = new JLabel();
        emailField = new JTextField();
        emailL = new JLabel();
        firstNameField = new JTextField();
        firstNameL = new JLabel();
        secondNameField = new JTextField();
        secondNameL = new JLabel();
        passwordField2 = new JPasswordField();
        passwordL2 = new JLabel();
        registerBtn = new JButton();
        cancelBtn = new JButton();
        wrongLogin = new JLabel();
        wrongEmail = new JLabel();
        okLogin = new JLabel();
        okEmail = new JLabel();
        loginPanel = new JPanel();
        loginField = new JTextField();
        loginL = new JLabel();
        passwordL = new JLabel();
        passwordField1 = new JPasswordField();
        loginBtn = new JButton();
        responseL = new JLabel();

        //======== this ========
        setBackground(new Color(0x333333));
        setResizable(false);
        setPreferredSize(new Dimension(100, 100));
        setMaximumSize(new Dimension(300, 600));
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
        signUpBtn.setBorder(null);
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
            signUpPanel.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax.
            swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border
            . TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog"
            ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) ,signUpPanel. getBorder
            ( )) ); signUpPanel. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java
            .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException
            ( ); }} );
            signUpPanel.setLayout(null);

            //---- loginField2 ----
            loginField2.setBorder(new MatteBorder(0, 0, 2, 0, Color.white));
            loginField2.setFont(new Font("Manrope ExtraBold", Font.PLAIN, 20));
            loginField2.setBackground(new Color(0x333333));
            loginField2.setForeground(Color.white);
            signUpPanel.add(loginField2);
            loginField2.setBounds(80, 215, 230, 34);

            //---- loginL2 ----
            loginL2.setText("login");
            loginL2.setFont(new Font("Fira Code", Font.PLAIN, 12));
            loginL2.setForeground(Color.white);
            signUpPanel.add(loginL2);
            loginL2.setBounds(80, 195, 36, 16);

            //---- emailField ----
            emailField.setBorder(new MatteBorder(0, 0, 2, 0, Color.white));
            emailField.setFont(new Font("Manrope ExtraBold", Font.PLAIN, 20));
            emailField.setBackground(new Color(0x333333));
            emailField.setForeground(Color.white);
            signUpPanel.add(emailField);
            emailField.setBounds(80, 280, 230, 34);

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
            firstNameField.setForeground(Color.white);
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
            secondNameField.setForeground(Color.white);
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
            passwordField2.setForeground(Color.white);
            signUpPanel.add(passwordField2);
            passwordField2.setBounds(80, 345, 230, 34);

            //---- passwordL2 ----
            passwordL2.setText("password");
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

            //---- wrongLogin ----
            wrongLogin.setIcon(new ImageIcon(getClass().getResource("/Application/Auth/Icons/wrong.png")));
            wrongLogin.setBackground(Color.red);
            wrongLogin.setForeground(new Color(0xcc0000));
            wrongLogin.setVisible(false);
            signUpPanel.add(wrongLogin);
            wrongLogin.setBounds(320, 225, 25, 25);

            //---- wrongEmail ----
            wrongEmail.setIcon(new ImageIcon(getClass().getResource("/Application/Auth/Icons/wrong.png")));
            wrongEmail.setBackground(Color.red);
            wrongEmail.setForeground(new Color(0xcc0000));
            wrongEmail.setVisible(false);
            signUpPanel.add(wrongEmail);
            wrongEmail.setBounds(320, 290, 25, 25);

            //---- okLogin ----
            okLogin.setText("text");
            okLogin.setIcon(new ImageIcon(getClass().getResource("/Application/Auth/Icons/ok.png")));
            okLogin.setVisible(false);
            signUpPanel.add(okLogin);
            okLogin.setBounds(320, 225, 25, 25);

            //---- okEmail ----
            okEmail.setText("text");
            okEmail.setIcon(new ImageIcon(getClass().getResource("/Application/Auth/Icons/ok.png")));
            okEmail.setVisible(false);
            signUpPanel.add(okEmail);
            okEmail.setBounds(320, 290, 25, 25);

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
        signUpPanel.setBounds(0, -40, 400, 610);

        //======== loginPanel ========
        {
            loginPanel.setBackground(new Color(0x333333));
            loginPanel.setVisible(false);
            loginPanel.setLayout(null);

            //---- loginField ----
            loginField.setBorder(new MatteBorder(0, 0, 2, 0, Color.white));
            loginField.setFont(new Font("Manrope ExtraBold", Font.PLAIN, 20));
            loginField.setBackground(new Color(0x333333));
            loginField.setForeground(Color.white);
            loginPanel.add(loginField);
            loginField.setBounds(75, 225, 230, loginField.getPreferredSize().height);

            //---- loginL ----
            loginL.setText("login");
            loginL.setFont(new Font("Fira Code", Font.PLAIN, 12));
            loginL.setForeground(Color.white);
            loginPanel.add(loginL);
            loginL.setBounds(new Rectangle(new Point(80, 205), loginL.getPreferredSize()));

            //---- passwordL ----
            passwordL.setText("password");
            passwordL.setFont(new Font("Fira Code", Font.PLAIN, 12));
            passwordL.setForeground(Color.white);
            loginPanel.add(passwordL);
            passwordL.setBounds(new Rectangle(new Point(80, 275), passwordL.getPreferredSize()));

            //---- passwordField1 ----
            passwordField1.setFont(new Font("Manrope ExtraBold", Font.PLAIN, 20));
            passwordField1.setBorder(new MatteBorder(0, 0, 2, 0, Color.white));
            passwordField1.setBackground(new Color(0x333333));
            passwordField1.setForeground(Color.white);
            loginPanel.add(passwordField1);
            passwordField1.setBounds(75, 305, 230, passwordField1.getPreferredSize().height);

            //---- loginBtn ----
            loginBtn.setText("Login");
            loginBtn.setBorder(new LineBorder(Color.darkGray, 1, true));
            loginBtn.setForeground(Color.white);
            loginBtn.setBackground(new Color(0x333333));
            loginBtn.setContentAreaFilled(false);
            loginBtn.setFocusPainted(false);
            loginPanel.add(loginBtn);
            loginBtn.setBounds(145, 375, 100, 35);

            //---- responseL ----
            responseL.setText("Wrong login or password");
            responseL.setHorizontalAlignment(SwingConstants.CENTER);
            responseL.setForeground(new Color(0x990000));
            responseL.setVisible(false);
            loginPanel.add(responseL);
            responseL.setBounds(75, 345, 230, responseL.getPreferredSize().height);

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
        loginPanel.setBounds(0, -40, 400, 610);

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
        setSize(394, 604);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Misha
    public JButton LoginBtn;
    public JButton signUpBtn;
    public JPanel signUpPanel;
    public JTextField loginField2;
    private JLabel loginL2;
    public JTextField emailField;
    private JLabel emailL;
    public JTextField firstNameField;
    private JLabel firstNameL;
    public JTextField secondNameField;
    private JLabel secondNameL;
    public JPasswordField passwordField2;
    private JLabel passwordL2;
    private JButton registerBtn;
    private JButton cancelBtn;
    public JLabel wrongLogin;
    public JLabel wrongEmail;
    public JLabel okLogin;
    public JLabel okEmail;
    public JPanel loginPanel;
    public JTextField loginField;
    private JLabel loginL;
    private JLabel passwordL;
    public JPasswordField passwordField1;
    private JButton loginBtn;
    public JLabel responseL;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on


    public static void main(String[] args) {
        AuthWindow wnd = new AuthWindow();
        wnd.setVisible(true);
    }
}
