package Application.Listeners;

import Application.AuthWindow;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static RestfulApi.Database.Methods.DescriptionMethods.hex;

public class userSignUpAction implements ActionListener {
    AuthWindow authWindow = AuthWindow.instance;

    @Override
    public void actionPerformed(ActionEvent e) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = authWindow.getHttpPostForRegister();
        try {
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                HttpEntity responseEntity = response.getEntity();
                if (!authWindow.wrongLogin.isVisible() && !authWindow.wrongEmail.isVisible()) {
                    authWindow.LoginBtn.setBorder(new MatteBorder(0,0, 2,0, Color.WHITE));
                    authWindow.signUpBtn.setBorder(null);
                    authWindow.loginPanel.setVisible(true);
                    authWindow.signUpPanel.setVisible(false);
                    authWindow.repaint();
                }
            } finally {
                response.close();
            }
        } catch (IOException ex) {
        }
    }
}
