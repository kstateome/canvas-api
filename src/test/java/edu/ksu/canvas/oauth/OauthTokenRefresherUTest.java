package edu.ksu.canvas.oauth;

import edu.ksu.canvas.LocalServerTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collections;

@RunWith(JUnit4.class)
public class OauthTokenRefresherUTest extends LocalServerTestBase {

    @Test
    public void successfulRefresh() throws Exception {
        registerUrlResponse("/login/oauth2/*", "/SampleJson/oauth/SuccessfulAccessTokenResponse.json", 200, Collections.emptyMap());
        OauthTokenRefresher refresher = new OauthTokenRefresher("", "", baseUrl);

        TokenRefreshResponse response = refresher.getNewToken("abc");

        assertEquals("1726~ACCESS_TOKEN_STRING", response.getAccessToken());
    }

    @Test
    public void invalidTokenReturnsNull() throws Exception {
        registerUrlResponse("/login/oauth2/*", "/SampleJson/oauth/InvalidRefreshTokenResponse.json", 400, Collections.emptyMap());
        OauthTokenRefresher refresher = new OauthTokenRefresher("", "", baseUrl);

        TokenRefreshResponse response = refresher.getNewToken("");

        assertNull("An invalid refresh token should return null", response);
    }

    @Test
    public void invalidClientIdReturnsNull() throws Exception {
        registerUrlResponse("/login/oauth2/*", "/SampleJson/oauth/InvalidClientIdResponse.json", 401, Collections.emptyMap());
        OauthTokenRefresher refresher = new OauthTokenRefresher("", "", baseUrl);

        TokenRefreshResponse response = refresher.getNewToken("");

        assertNull("An invalid refresh token should return null", response);
    }
}
