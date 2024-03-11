package Application.Auth.Listeners;

import Application.Auth.AuthWindow;

import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpAction implements ActionListener {
    AuthWindow authWindow = AuthWindow.instance;
    @Override
    public void actionPerformed(ActionEvent e) {
        authWindow.signUpBtn.setBorder(new MatteBorder(0,0, 2,0, Color.WHITE));
        authWindow.LoginBtn.setBorder(null);
        authWindow.signUpPanel.setVisible(true);
        authWindow.loginPanel.setVisible(false);
        authWindow.repaint();
    }
}
