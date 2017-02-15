package edu.ksu.canvas.net;

import java.util.Collections;
import java.util.Map;

import org.apache.http.HttpHeaders;
import org.apache.http.entity.StringEntity;
import org.apache.http.localserver.LocalTestServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.exception.UnauthorizedException;
import edu.ksu.canvas.oauth.NonRefreshableOauthToken;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.util.JsonTestUtil;


@RunWith(JUnit4.class)
public class SimpleRestClientUTest {

    private LocalTestServer server = new LocalTestServer(null, null);
    private String baseUrl;
    private OauthToken emptyAdminToken = new NonRefreshableOauthToken("");
    SimpleRestClient restClient = new SimpleRestClient();

    @Before
    public void setUp() throws Exception {
        server.start();
        baseUrl = "http:/" + server.getServiceAddress();
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    private void registerUrlResponse(String url, String sampleJsonFileName, Integer statusCode, Map<String, String> headers) {
        String jsonContent = JsonTestUtil.loadJson(sampleJsonFileName, SimpleRestClientUTest.class);
        server.register(url, (request, response, context) -> {
            response.setStatusCode(statusCode);
            for(String key : headers.keySet()) {
                String value = headers.get(key);
                response.addHeader(key, value);
            }
            response.setEntity(new StringEntity(jsonContent));
        });
    }

    @Test(expected=UnauthorizedException.class)
    public void http401UnauthorizedThrowsException() throws Exception {
        String url = "/unauthorizedUrl";
        registerUrlResponse(url, "/SampleJson/oauth/UserUnauthorizedResponse.json", 401, Collections.emptyMap());

        restClient.sendApiGet(emptyAdminToken, baseUrl + url, 100, 100);
    }

    @Test(expected=InvalidOauthTokenException.class)
    public void http401InvalidTokenThrowsException() throws Exception {
        String url = "/invalidAccessToken";
        registerUrlResponse(url, "/SampleJson/oauth/InvalidAccessTokenResponse.json", 401, Collections.singletonMap(HttpHeaders.WWW_AUTHENTICATE, ""));

        restClient.sendApiGet(emptyAdminToken, baseUrl + url, 100, 100);
    }
}
