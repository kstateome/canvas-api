package edu.ksu.canvas;

import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.exception.UnauthorizedException;
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

    @Test
    public void newTokenIsAssignedWhenRefreshed() {
        token = new RefreshableOauthToken(tokenRefresher, refreshToken);
        when(tokenRefresher.getNewToken(refreshToken)).thenReturn(refreshedToken);

        token.refresh();

        assertEquals("Expected new token to be token returned from refresh service", refreshedToken, token.getToken());
    }

    @Test
    public void tokenIsRefreshedUponConstruction() {
        when(tokenRefresher.getNewToken(refreshToken)).thenReturn(refreshedToken);
        token = new RefreshableOauthToken(tokenRefresher, refreshToken);

        assertEquals("Expected token to be refreshed upon construction", refreshedToken, token.getToken());
    }

    @Test
    public void tokenIsChangedWhenTokenExists() {
        when(tokenRefresher.getNewToken(refreshToken))
                .thenReturn(oldToken)
                .thenReturn(refreshedToken);
        token = new RefreshableOauthToken(tokenRefresher, refreshToken);

        token.refresh();

        assertEquals("Expected new token to be token returned from refresh service", refreshedToken, token.getToken());
    }

    @Test(expected = UnauthorizedException.class)
    public void unauthorizedExceptionThrownWhenUnauthorized() {
        token = new RefreshableOauthToken(tokenRefresher, refreshToken);
        when(tokenRefresher.getNewToken(refreshToken)).thenThrow(UnauthorizedException.class);
        token.refresh();
    }

    @Test(expected = InvalidOauthTokenException.class)
    public void invalidOauthTokenExceptionThrownWhenInvalid() {
        token = new RefreshableOauthToken(tokenRefresher, refreshToken);
        when(tokenRefresher.getNewToken(refreshToken)).thenThrow(InvalidOauthTokenException.class);
        token.refresh();
    }

}
