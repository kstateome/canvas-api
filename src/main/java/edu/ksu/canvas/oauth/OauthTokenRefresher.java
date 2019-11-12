package edu.ksu.canvas.oauth;

import com.google.gson.Gson;
import edu.ksu.canvas.impl.GsonResponseParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;

public class OauthTokenRefresher implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(OauthTokenRefresher.class);

    private static final int TIMEOUT_SECONDS = 10;
    private final String clientId;
    private final String clientSecret;
    private final String canvasUrl;

    public OauthTokenRefresher(String clientId, String clientSecret, String canvasUrl) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.canvasUrl = canvasUrl;
    }

    public TokenRefreshResponse getNewToken(String refreshToken) throws IOException {
        LOG.debug("Getting a fresh OAuth access token");
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(TIMEOUT_SECONDS*1000)
                .setSocketTimeout(TIMEOUT_SECONDS*1000)
                .build();
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(config)
                .build();

        String url = canvasUrl + "/login/oauth2/token?grant_type=refresh_token&client_id=" + clientId + "&client_secret=" + clientSecret + "&refresh_token=" + refreshToken;
        HttpPost postRequest = new HttpPost(url);

        try {
            HttpResponse httpResponse = httpClient.execute(postRequest);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == 401) {
                LOG.error("Unauthorized refresh token request. Wrong client_id or secret?");
                return null;
            }
            if (statusCode != 200) {
                LOG.error("Non-200 status code ( " + statusCode + " )returned while requesting an access token at URL " + url);
                HttpEntity errorEntity = httpResponse.getEntity();
                if (errorEntity != null) {
                    String errorBody = EntityUtils.toString(errorEntity);
                    LOG.error("Response from Canvas: " + errorBody);
                }
                return null;
            }
            HttpEntity entity = httpResponse.getEntity();
            String responseBody = EntityUtils.toString(entity);
            Gson gson = GsonResponseParser.getDefaultGsonParser(false);
            return gson.fromJson(responseBody, TokenRefreshResponse.class);
        } finally {
            postRequest.releaseConnection();
            httpClient.close();
        }
    }
}
