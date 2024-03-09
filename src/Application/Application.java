package Application;

import javax.swing.*;

public class Application extends JFrame {
    private JPanel registrationPanel;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JTextField firstNameField;
    private JTextField secondNameField;
    private JLabel registerL;
    private JLabel loginL;
    private JLabel passwordL;
    private JLabel firstNameL;
    private JLabel secondNameL;
    private JCheckBox seePasswordCheckBox;
    private JButton signUpButton;

    public Application() {
        setSize(380, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(registrationPanel);

    }

    public static void main(String[] args) {
        Application app = new Application();
        app.setVisible(true);
    }

}
