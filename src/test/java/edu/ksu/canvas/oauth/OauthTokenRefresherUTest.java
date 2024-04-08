package edu.ksu.canvas.oauth;

import edu.ksu.canvas.LocalServerTestBase;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class OauthTokenRefresherUTest extends LocalServerTestBase {

    @Test
    void successfulRefresh() throws Exception {
        registerUrlResponse("/login/oauth2/*", "/SampleJson/oauth/SuccessfulAccessTokenResponse.json", 200, Collections.emptyMap());
        OauthTokenRefresher refresher = new OauthTokenRefresher("", "", baseUrl);

        TokenRefreshResponse response = refresher.getNewToken("abc");

        assertEquals("1726~ACCESS_TOKEN_STRING", response.getAccessToken());
    }

    @Test
    void invalidTokenReturnsNull() throws Exception {
        registerUrlResponse("/login/oauth2/*", "/SampleJson/oauth/InvalidRefreshTokenResponse.json", 400, Collections.emptyMap());
        OauthTokenRefresher refresher = new OauthTokenRefresher("", "", baseUrl);

        TokenRefreshResponse response = refresher.getNewToken("");

        assertNull(response, "An invalid refresh token should return null");
    }

    @Test
    void invalidClientIdReturnsNull() throws Exception {
        registerUrlResponse("/login/oauth2/*", "/SampleJson/oauth/InvalidClientIdResponse.json", 401, Collections.emptyMap());
        OauthTokenRefresher refresher = new OauthTokenRefresher("", "", baseUrl);

        TokenRefreshResponse response = refresher.getNewToken("");

        assertNull(response, "An invalid refresh token should return null");
    }
}
