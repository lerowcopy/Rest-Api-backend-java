package RestfulApi.Server;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class test {
    public static void main(String[] args) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8000/api?login=rua");
        CloseableHttpResponse response  = httpClient.execute(httpGet);

        try {
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String entityS = EntityUtils.toString(entity);
                if (entityS.contains("\"status\":\"success\"")){
                    System.out.println(entityS);
                }
                else if (entityS.contains("failed")){
                    System.out.println("failed");;
                }
            }
        } finally {
            response.close();
        }
    }
}
