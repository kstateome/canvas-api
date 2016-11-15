package edu.ksu.canvas.oauth;

import java.util.Date;

public class RefreshableOauthToken implements OauthToken {

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
        TokenRefreshResponse refreshResponse = tokenRefresher.getNewToken(refreshToken);
        apiToken = refreshResponse.getAccessToken();
        tokenExpiration = new TokenExpiration(refreshResponse.getExpiresIn());
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
