package edu.ksu.canvas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RefreshableTokenUTest {
    private final String refreshToken = "arbitraryToken";
    private final String oldToken = "oldToken";
    private final String refreshedToken = "newToken";

    @Mock
    private OauthTokenRefresher tokenRefresher;
    private RefreshableOauthToken token;

    private void setupRefreshTest() {
        token = new RefreshableOauthToken(tokenRefresher, refreshToken);
        when(tokenRefresher.getNewToken(refreshToken)).thenReturn(refreshedToken);
    }

    @Test
    public void newTokenIsAssignedWhenRefreshed() {
        setupRefreshTest();
        token.refresh();

        assertEquals("Expected new token to be token returned from refresh service", refreshedToken, token.getToken());
    }

}
