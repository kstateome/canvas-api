package edu.ksu.canvas.oauth;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.localserver.LocalTestServer;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.ksu.canvas.util.JsonTestUtil;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

public class OauthTokenRefresherUTest {

    private LocalTestServer server = new LocalTestServer(null, null);
    private String baseUrl;

    // Take a JSON file and an HTTP status code and construct a mock response
    private HttpRequestHandler makeRequestHandlerFromJson(String jsonFileName, int statusCode) {
        String jsonContent = JsonTestUtil.loadJson(jsonFileName, OauthTokenRefresherUTest.class);
        HttpRequestHandler myHttpRequestHandler = new HttpRequestHandler() {
            @Override
            public void handle(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
                response.setStatusCode(statusCode);
                response.setEntity(new StringEntity(jsonContent));
            }
        };
        return myHttpRequestHandler;
    }

    private void registerOauthHandler(HttpRequestHandler handler) {
        server.register("/login/oauth2/*", handler);
    }

    @Before
    public void setUp() throws Exception {
        server.start();
        baseUrl = "http:/" + server.getServiceAddress();
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void successfulRefresh() throws Exception {
        registerOauthHandler(makeRequestHandlerFromJson("/SampleJson/oauth/SuccessfulAccessTokenResponse.json", 200));
        OauthTokenRefresher refresher = new OauthTokenRefresher("", "", baseUrl);

        TokenRefreshResponse response = refresher.getNewToken("abc");

        assertEquals("1726~ACCESS_TOKEN_STRING", response.getAccessToken());
    }

    @Test
    public void invalidTokenReturnsNull() throws Exception {
        registerOauthHandler(makeRequestHandlerFromJson("/SampleJson/oauth/InvalidRefreshTokenResponse.json", 400));
        OauthTokenRefresher refresher = new OauthTokenRefresher("", "", baseUrl);

        TokenRefreshResponse response = refresher.getNewToken("");

        assertNull("An invalid refresh token should return null", response);
    }

    @Test
    public void invalidClientIdReturnsNull() throws Exception {
        registerOauthHandler(makeRequestHandlerFromJson("/SampleJson/oauth/InvalidClientIdResponse.json", 401));
        OauthTokenRefresher refresher = new OauthTokenRefresher("", "", baseUrl);

        TokenRefreshResponse response = refresher.getNewToken("");

        assertNull("An invalid refresh token should return null", response);
    }
}
