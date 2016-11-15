package edu.ksu.canvas.oauth;

import java.io.IOException;
import java.util.Date;

import org.apache.log4j.Logger;

import edu.ksu.canvas.exception.InvalidOauthTokenException;

public class RefreshableOauthToken implements OauthToken {
    private static final Logger LOG = Logger.getLogger(RefreshableOauthToken.class);

    private OauthTokenRefresher tokenRefresher;
    private String refreshToken;
    private String apiToken;
    private TokenExpiration tokenExpiration;

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

    private class TokenExpiration {
        private final int expireWindowMS = 60000;
        Date lastRefreshed;
        Long timeToLive;

        TokenExpiration(Long timeToLive) {
            this.lastRefreshed = new Date();
            if (timeToLive != null) {
                this.timeToLive = timeToLive - expireWindowMS;
            }
        }

        boolean isExpired() {
            // Not trusting time to live to be returned so default to not being expired.
            if (timeToLive == null) {
                return false;
            }
            return (new Date().getTime() - lastRefreshed.getTime()) >= timeToLive;
        }
    }

}
