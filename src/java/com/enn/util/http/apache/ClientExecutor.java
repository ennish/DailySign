package com.enn.util.http.apache;


import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author apache
 * How to send a request via proxy.
 *
 * @since 4.0
 */
public class ClientExecutor {

    public static String execute(String uri, String queryParam) throws IOException {
        if (queryParam != null) {
            if (uri.indexOf('?') == -1) {
                uri += '?';
            }
            uri += uri.endsWith("?") ? queryParam : '&' + queryParam;
        }
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpClientContext localClientContext = HttpClientContext.create();
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = httpClient.execute(httpGet, localClientContext);
        try {
            StatusLine statusLine = response.getStatusLine();
            HttpEntity entity = response.getEntity();
            if (statusLine.getStatusCode() >= 300) {
                throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
            }
            return entity == null ? null : EntityUtils.toString(entity, Consts.UTF_8);
        } finally {
            httpGet.releaseConnection();
        }
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        String baseUrl = "http://localhost:8888/country/test/rest";
        String baseUrl2 = "http://192.9.200.98:8083/ChainWayInsurance/rest/userservice/queryIfExists";
        ClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(baseUrl2,"",String.class);
        System.out.println(result);
    }
}

