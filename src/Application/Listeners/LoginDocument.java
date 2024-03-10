package Application.Listeners;

import Application.AuthWindow;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.IOException;

public class LoginDocument implements DocumentListener {
    AuthWindow authWindow = AuthWindow.instance;
    @Override
    public void insertUpdate(DocumentEvent e) {
        changedUpdate(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        changedUpdate(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(String.format("http://localhost:8000/api?login=%s", authWindow.loginField2.getText()));

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String entityS = EntityUtils.toString(entity);
                if (entityS.contains("\"status\":\"success\"")) {
                    authWindow.wrongLogin.setVisible(true);
                } else if (entityS.contains("failed")) {
                    authWindow.wrongLogin.setVisible(false);
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
