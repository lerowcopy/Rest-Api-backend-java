package Application.Listeners;

import Application.AuthWindow;

import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginAction implements ActionListener {
    AuthWindow authWindow = AuthWindow.instance;
    @Override
    public void actionPerformed(ActionEvent e) {
        authWindow.LoginBtn.setBorder(new MatteBorder(0,0, 2,0, Color.WHITE));
        authWindow.signUpBtn.setBorder(null);
        authWindow.loginPanel.setVisible(true);
        authWindow.signUpPanel.setVisible(false);
        authWindow.repaint();
    }
}
