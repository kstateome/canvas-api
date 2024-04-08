package edu.ksu.canvas.oauth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RefreshableTokenUTest {
    private static final long EXPIRE_TIME_SECONDS = 3600;
    private static final long DEFAULT_TIME_DELTA_MS = 1000;
    private final String refreshToken = "arbitraryToken";
    private final TokenRefreshResponse firstToken = new TokenRefreshResponse();
    private final TokenRefreshResponse secondToken = new TokenRefreshResponse();

    @Mock
    private OauthTokenRefresher tokenRefresher;
    private RefreshableOauthToken token;

    @BeforeEach
    void setup() {
        firstToken.setAccessToken("firstToken");
        firstToken.setExpiresIn(EXPIRE_TIME_SECONDS);
        secondToken.setAccessToken("secondToken");
        secondToken.setExpiresIn(EXPIRE_TIME_SECONDS);
    }

    @Test
    void tokenIsRefreshedUponConstruction() throws Exception {
        when(tokenRefresher.getNewToken(refreshToken)).thenReturn(secondToken);

        token = new RefreshableOauthTokenForTests(tokenRefresher, refreshToken, DEFAULT_TIME_DELTA_MS);

        assertEquals(secondToken.getAccessToken(), token.getAccessToken(), "Expected token to be refreshed upon construction");
    }

    @Test
    void tokenIsChangedWhenTokenExists() throws Exception {
        when(tokenRefresher.getNewToken(refreshToken))
                .thenReturn(firstToken)
                .thenReturn(secondToken);
        token = new RefreshableOauthTokenForTests(tokenRefresher, refreshToken, DEFAULT_TIME_DELTA_MS);

        token.refresh();

        assertEquals(secondToken.getAccessToken(), token.getAccessToken(), "Expected new token to be token returned from refresh service");
    }

    @Test
    void tokenIsRefreshedWhenExpireTimeReached() throws Exception {
        firstToken.setExpiresIn(1l);
        long timeDelta = 1001l;
        when(tokenRefresher.getNewToken(refreshToken))
                .thenReturn(firstToken)
                .thenReturn(secondToken);
        token = new RefreshableOauthTokenForTests(tokenRefresher, refreshToken, timeDelta);

        String accessToken = token.getAccessToken();

        assertEquals(secondToken.getAccessToken(), accessToken, "Expected token to be refreshed when expire time is reached");
    }

    @Test
    void tokenIsNotRefreshedWhenNotExpired() throws Exception {
        when(tokenRefresher.getNewToken(refreshToken))
                .thenReturn(firstToken)
                .thenReturn(secondToken);
        token = new RefreshableOauthTokenForTests(tokenRefresher, refreshToken, DEFAULT_TIME_DELTA_MS);

        String accessToken = token.getAccessToken();

        assertEquals(firstToken.getAccessToken(), accessToken, "Expected token to not be refreshed when not expired");
    }

    @Test
    void tokenIsNotExpiredWhenNullTimeToLive() throws Exception {
        firstToken.setExpiresIn(null);
        when(tokenRefresher.getNewToken(refreshToken))
                .thenReturn(firstToken)
                .thenReturn(secondToken);
        token = new RefreshableOauthTokenForTests(tokenRefresher, refreshToken, DEFAULT_TIME_DELTA_MS);

        String accessToken = token.getAccessToken();

        assertEquals(firstToken.getAccessToken(), accessToken, "Expected token to not be refreshed when expire time is null");
    }

    @Test
    void tokenIsNotExpiredWhen0TimeToLive() throws Exception {
        firstToken.setExpiresIn(0l);
        when(tokenRefresher.getNewToken(refreshToken))
                .thenReturn(firstToken)
                .thenReturn(secondToken);
        token = new RefreshableOauthTokenForTests(tokenRefresher, refreshToken, DEFAULT_TIME_DELTA_MS);

        String accessToken = token.getAccessToken();

        assertEquals(firstToken.getAccessToken(), accessToken, "Expected token to not be refreshed when expire time is 0");
    }

    @Test
    void createTokenWithAccessTokenThenRefresh() throws Exception {
        when(tokenRefresher.getNewToken(refreshToken))
                .thenReturn(firstToken);
        token = new RefreshableOauthTokenForTests(tokenRefresher, refreshToken, "arbitraryAccessToken", DEFAULT_TIME_DELTA_MS);
        token.refresh();

        assertEquals(firstToken.getAccessToken(), token.getAccessToken());
    }

    /*
     * This class allows us to not depend on Thread.sleep() for the unit tests.
     * The now() method alternates between calls in the 'present' and calls in the 'future'.
     * This allows us to 'trick' the RefreshableOauthToken to think that time has passed.
     */
    private class RefreshableOauthTokenForTests extends RefreshableOauthToken {
        private long timeDelta;
        private boolean inFuture;

        RefreshableOauthTokenForTests (OauthTokenRefresher tokenRefresher, String refreshToken, String accessToken, long timeDelta) {
            super(tokenRefresher, refreshToken, accessToken);
            this.timeDelta = timeDelta;
        }

        RefreshableOauthTokenForTests(OauthTokenRefresher tokenRefresher, String refreshToken, long timeDelta) {
            super(tokenRefresher, refreshToken);
            this.timeDelta = timeDelta;
        }

        @Override
        protected Date now() {
            if (!inFuture) {
                inFuture = true;
                return new Date();
            } else {
                inFuture = false;
                return new Date(new Date().getTime() + timeDelta);
            }
        }
    }
}
