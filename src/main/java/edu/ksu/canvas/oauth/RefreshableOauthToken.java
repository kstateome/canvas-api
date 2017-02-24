package edu.ksu.canvas.oauth;

import edu.ksu.canvas.exception.InvalidOauthTokenException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Date;

public class RefreshableOauthToken implements OauthToken {
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = Logger.getLogger(RefreshableOauthToken.class);

    private OauthTokenRefresher tokenRefresher;
    private String refreshToken;
    private String apiToken;
    private TokenExpiration tokenExpiration;

    /**
     * Constructs a new OAuth token with an access token already set.
     * When a refresh token is first issued during the OAuth flow it comes with a
     * valid access token so using this constructor eliminates one call to Canvas
     * compared to the other constructor.
     * @param tokenRefresher Token refresher service
     * @param refreshToken The permanent refresh token
     * @param accessToken The temporary access token
     */
    public RefreshableOauthToken(OauthTokenRefresher tokenRefresher, String refreshToken, String accessToken) {
        this.refreshToken = refreshToken;
        this.apiToken = accessToken;
        this.tokenRefresher = tokenRefresher;
        tokenExpiration = new TokenExpiration(null);
    }

    /**
     * Constructs a new OAuth token and immediately performs a refresh to get a valid access token
     * @param tokenRefresher Token refresher service
     * @param refreshToken The permanent refresh token
     */
    public RefreshableOauthToken(OauthTokenRefresher tokenRefresher, String refreshToken) {
        this.refreshToken = refreshToken;
        this.tokenRefresher = tokenRefresher;
        refresh();
    }

    @Override
    public void refresh() {
        try {
            TokenRefreshResponse refreshResponse = tokenRefresher.getNewToken(refreshToken);
            if(refreshResponse == null) {
                throw new InvalidOauthTokenException();
            }
            apiToken = refreshResponse.getAccessToken();
            tokenExpiration = new TokenExpiration(refreshResponse.getExpiresIn());
        } catch (IOException e) {
            LOG.error("Exception while attempting to refresh access token");
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getAccessToken() {
        if (tokenExpiration.isExpired()) {
            refresh();
        }
        return apiToken;
    }

    protected Date now() {
        return new Date();
    }

    private class TokenExpiration {
        Date lastRefreshed;
        Long timeToLiveMS;

        TokenExpiration(Long timeToLiveSeconds) {
            this.lastRefreshed = now();
            if (timeToLiveSeconds != null) {
                this.timeToLiveMS = timeToLiveSeconds * 1000;
            }
        }

        boolean isExpired() {
            // Not trusting time to live to be returned so default to not being expired.
            if (timeToLiveMS == null) {
                return false;
            }
            return (now().getTime() - lastRefreshed.getTime()) >= timeToLiveMS;
        }
    }

}
