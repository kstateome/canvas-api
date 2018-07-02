package edu.ksu.canvas.net;

import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.Collections;

import org.apache.http.HttpHeaders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import edu.ksu.canvas.LocalServerTestBase;
import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.exception.UnauthorizedException;
import edu.ksu.canvas.oauth.NonRefreshableOauthToken;
import edu.ksu.canvas.oauth.OauthToken;



@RunWith(JUnit4.class)
public class SimpleRestClientUTest extends LocalServerTestBase {

    private OauthToken emptyAdminToken = new NonRefreshableOauthToken("");
    SimpleRestClient restClient = new SimpleRestClient();

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

    @Test(expected = IOException.class)
    public void http503ServiceTemporarilyUnavailableException() throws Exception {
        String url = "/unavailableServiceUrl";
        registerUrlResponse(url, "", 503, Collections.emptyMap());

        final Response response = restClient.sendApiGet(emptyAdminToken, baseUrl + url, 100, 100);
        assertNull(response.getContent());
    }
}
