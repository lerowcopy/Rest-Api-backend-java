package Application.Auth.Listeners;

import Application.Auth.AuthWindow;
import Application.Main.MainWindow;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static RestfulApi.Database.Methods.DescriptionMethods.hex;

public class userLoginAction implements ActionListener {
    AuthWindow authWindow = AuthWindow.instance;
    @Override
    public void actionPerformed(ActionEvent e) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = authWindow.getHttpPostForLogin();
        authWindow.responseL.setVisible(true);
        try {
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    String password = new String(authWindow.passwordField1.getPassword());
                    String entity = EntityUtils.toString(responseEntity);
                    String hexP = hex(password, 10);

                    if (entity.contains(String.format("\"password\":\"%s\"", hexP))) {
                        MainWindow wnd = new MainWindow(authWindow.loginField.getText());
                        wnd.setVisible(true);
                        authWindow.dispose();
                        authWindow.responseL.setForeground(Color.GREEN);
                        authWindow.responseL.setText("Login successful");
                        authWindow.repaint();

                    }
                    else{
                        authWindow.responseL.setForeground(Color.RED);
                        authWindow.responseL.setText("Wrong login or password");
                        authWindow.repaint();
                    }
                }
            } finally {
                response.close();
            }
        } catch (IOException ex) {
            authWindow.responseL.setForeground(Color.RED);
            authWindow.responseL.setText("Wrong login or password");
            authWindow.repaint();
        }
    }
}
