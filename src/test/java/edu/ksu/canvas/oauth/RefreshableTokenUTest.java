package edu.ksu.canvas.oauth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RefreshableTokenUTest {
    private static final long EXPIRE_TIME_SECONDS = 3600;
    private final String refreshToken = "arbitraryToken";
    private final TokenRefreshResponse oldToken = new TokenRefreshResponse();
    private final TokenRefreshResponse refreshedToken = new TokenRefreshResponse();

    @Mock
    private OauthTokenRefresher tokenRefresher;
    private RefreshableOauthToken token;

    @Before
    public void setup() {
        oldToken.setAccessToken("oldToken");
        oldToken.setExpiresIn(EXPIRE_TIME_SECONDS);
        refreshedToken.setAccessToken("newToken");
        refreshedToken.setExpiresIn(EXPIRE_TIME_SECONDS);
    }

    @Test
    public void tokenIsRefreshedUponConstruction() {
        when(tokenRefresher.getNewToken(refreshToken)).thenReturn(refreshedToken);

        token = new RefreshableOauthToken(tokenRefresher, refreshToken);

        assertEquals("Expected token to be refreshed upon construction", refreshedToken.getAccessToken(), token.getAccessToken());
    }

    @Test
    public void tokenIsChangedWhenTokenExists() {
        when(tokenRefresher.getNewToken(refreshToken))
                .thenReturn(oldToken)
                .thenReturn(refreshedToken);
        token = new RefreshableOauthToken(tokenRefresher, refreshToken);

        token.refresh();

        assertEquals("Expected new token to be token returned from refresh service", refreshedToken.getAccessToken(), token.getAccessToken());
    }

    @Test
    public void tokenIsRefreshedWhenExpireTimeReached() {
        oldToken.setExpiresIn(0l);
        when(tokenRefresher.getNewToken(refreshToken))
                .thenReturn(oldToken)
                .thenReturn(refreshedToken);
        token = new RefreshableOauthToken(tokenRefresher, refreshToken);

        String accessToken = token.getAccessToken();

        assertEquals("Expected token to be refreshed when expire time is reached", refreshedToken.getAccessToken(), accessToken);
    }

    @Test
    public void tokenIsNotRefreshedWhenNotExpired() {
        when(tokenRefresher.getNewToken(refreshToken))
                .thenReturn(oldToken)
                .thenReturn(refreshedToken);
        token = new RefreshableOauthToken(tokenRefresher, refreshToken);

        String accessToken = token.getAccessToken();

        assertEquals("Expected token to not be refreshed when not expired", refreshedToken.getAccessToken(), accessToken);
    }

    @Test
    public void tokenIsNotExpiredWhenNullTimeToLive() {
        oldToken.setExpiresIn(null);
        when(tokenRefresher.getNewToken(refreshToken))
                .thenReturn(oldToken)
                .thenReturn(refreshedToken);
        token = new RefreshableOauthToken(tokenRefresher, refreshToken);

        String accessToken = token.getAccessToken();

        assertEquals("Expected token to not be refreshed when expire time is null", oldToken.getAccessToken(), accessToken);
    }

}
