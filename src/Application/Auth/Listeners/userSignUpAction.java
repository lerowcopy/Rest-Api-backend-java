package Application.Auth.Listeners;

import Application.Auth.AuthWindow;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class userSignUpAction implements ActionListener {
    AuthWindow authWindow = AuthWindow.instance;

    @Override
    public void actionPerformed(ActionEvent e) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = authWindow.getHttpPostForRegister();
        try {
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                if (!authWindow.wrongLogin.isVisible() && !authWindow.wrongEmail.isVisible()) {
                    authWindow.loginField2.setText("");
                    authWindow.emailField.setText("");
                    authWindow.passwordField2.setText("");
                    authWindow.firstNameField.setText("");
                    authWindow.secondNameField.setText("");

                    authWindow.LoginBtn.setBorder(new MatteBorder(0, 0, 2, 0, Color.WHITE));
                    authWindow.signUpBtn.setBorder(null);
                    authWindow.loginPanel.setVisible(true);
                    authWindow.signUpPanel.setVisible(false);
                    authWindow.repaint();
                }
            }
        } catch (IOException ignored) {
        }
    }
}
