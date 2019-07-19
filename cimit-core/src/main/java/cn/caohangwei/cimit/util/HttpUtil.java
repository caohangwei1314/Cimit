package cn.caohangwei.cimit.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


/**
 * Encapsulates the apache http components class and sends a post request.
 *
 * @author PinuoC
 * @since 0.3.0
 */
public class HttpUtil {

    private static volatile CloseableHttpClient httpClient = HttpClients.createDefault();

    public static String sendPost(String url, String jsonStr) throws IOException {
        String result;
        StringEntity stringEntity = new StringEntity(jsonStr);
        stringEntity.setContentType("application/json");
        HttpPost httpPost = new HttpPost("http://" + url);
        httpPost.setEntity(stringEntity);
        try (CloseableHttpResponse httpResponse = httpClient.execute(httpPost);) {
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        }
        return result;
    }

}
