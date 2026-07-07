package edu.ksu.canvas.oauth;

import com.google.gson.Gson;
import edu.ksu.canvas.impl.GsonResponseParser;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.util.Timeout;
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
                .setConnectTimeout(Timeout.ofSeconds(TIMEOUT_SECONDS))
                .setResponseTimeout(Timeout.ofSeconds(TIMEOUT_SECONDS))
                .build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(config)
                .build();

        String url = canvasUrl + "/login/oauth2/token?grant_type=refresh_token&client_id=" + clientId + "&client_secret=" + clientSecret + "&refresh_token=" + refreshToken;
        HttpPost postRequest = new HttpPost(url);

        try {
            ClassicHttpResponse httpResponse = httpClient.execute(postRequest);
            int statusCode = httpResponse.getCode();
            if (statusCode == 401) {
                LOG.error("Unauthorized refresh token request. Wrong client_id or secret?");
                return null;
            }
            if (statusCode != 200) {
                LOG.error("Non-200 status code ( " + statusCode + " )returned while requesting an access token at URL " + url);
                HttpEntity errorEntity = httpResponse.getEntity();
                if (errorEntity != null) {
                    try {
                        String errorBody = EntityUtils.toString(errorEntity);
                        LOG.error("Response from Canvas: " + errorBody);
                    } catch (org.apache.hc.core5.http.ParseException e) {
                        LOG.error("Failed to parse error response body", e);
                    }
                }
                return null;
            }
            HttpEntity entity = httpResponse.getEntity();
            try {
                String responseBody = EntityUtils.toString(entity);
                Gson gson = GsonResponseParser.getDefaultGsonParser(false);
                return gson.fromJson(responseBody, TokenRefreshResponse.class);
            } catch (org.apache.hc.core5.http.ParseException e) {
                throw new IOException("Failed to parse token response", e);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            postRequest.reset();
            httpClient.close();
        }
    }
}
