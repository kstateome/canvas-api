package edu.ksu.canvas.oauth;

public class RefreshableOauthToken implements OauthToken {

    private String refreshToken;
    private OauthTokenRefresher tokenRefresher;
    private String apiToken;

    public RefreshableOauthToken(OauthTokenRefresher tokenRefresher, String refreshToken) {
        this.refreshToken = refreshToken;
        this.tokenRefresher = tokenRefresher;
        refresh();
    }

    @Override
    public void refresh() {
        apiToken = tokenRefresher.getNewToken(refreshToken).getAccessToken();
    }

    @Override
    public String getAccessToken() {
        return apiToken;
    }

}
