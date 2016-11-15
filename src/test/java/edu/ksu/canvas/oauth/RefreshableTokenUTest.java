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
    private final TokenRefreshResponse firstToken = new TokenRefreshResponse();
    private final TokenRefreshResponse secondToken = new TokenRefreshResponse();

    @Mock
    private OauthTokenRefresher tokenRefresher;
    private RefreshableOauthToken token;

    @Before
    public void setup() {
        firstToken.setAccessToken("firstToken");
        firstToken.setExpiresIn(EXPIRE_TIME_SECONDS);
        secondToken.setAccessToken("secondToken");
        secondToken.setExpiresIn(EXPIRE_TIME_SECONDS);
    }

    @Test
    public void tokenIsRefreshedUponConstruction() throws Exception {
        when(tokenRefresher.getNewToken(refreshToken)).thenReturn(secondToken);

        token = new RefreshableOauthToken(tokenRefresher, refreshToken);

        assertEquals("Expected token to be refreshed upon construction", secondToken.getAccessToken(), token.getAccessToken());
    }

    @Test
    public void tokenIsChangedWhenTokenExists() throws Exception {
        when(tokenRefresher.getNewToken(refreshToken))
                .thenReturn(firstToken)
                .thenReturn(secondToken);
        token = new RefreshableOauthToken(tokenRefresher, refreshToken);

        token.refresh();

        assertEquals("Expected new token to be token returned from refresh service", secondToken.getAccessToken(), token.getAccessToken());
    }

    @Test
    public void tokenIsRefreshedWhenExpireTimeReached() throws Exception {
        firstToken.setExpiresIn(0l);
        when(tokenRefresher.getNewToken(refreshToken))
                .thenReturn(firstToken)
                .thenReturn(secondToken);
        token = new RefreshableOauthToken(tokenRefresher, refreshToken);

        String accessToken = token.getAccessToken();

        assertEquals("Expected token to be refreshed when expire time is reached", secondToken.getAccessToken(), accessToken);
    }

    @Test
    public void tokenIsNotRefreshedWhenNotExpired() throws Exception {
        when(tokenRefresher.getNewToken(refreshToken))
                .thenReturn(firstToken)
                .thenReturn(secondToken);
        token = new RefreshableOauthToken(tokenRefresher, refreshToken);

        String accessToken = token.getAccessToken();

        assertEquals("Expected token to not be refreshed when not expired", secondToken.getAccessToken(), accessToken);
    }

    @Test
    public void tokenIsNotExpiredWhenNullTimeToLive() throws Exception {
        firstToken.setExpiresIn(null);
        when(tokenRefresher.getNewToken(refreshToken))
                .thenReturn(firstToken)
                .thenReturn(secondToken);
        token = new RefreshableOauthToken(tokenRefresher, refreshToken);

        String accessToken = token.getAccessToken();

        assertEquals("Expected token to not be refreshed when expire time is null", firstToken.getAccessToken(), accessToken);
    }

}
