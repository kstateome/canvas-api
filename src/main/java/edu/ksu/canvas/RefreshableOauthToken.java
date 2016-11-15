package edu.ksu.canvas;

public class RefreshableOauthToken extends OauthToken {

    private OauthTokenRefresher tokenRefresher;
    private String apiToken;

    public RefreshableOauthToken(OauthTokenRefresher tokenRefresher, String refreshToken) {
        super(refreshToken);
        this.tokenRefresher = tokenRefresher;
    }

    @Override
    public void refresh() {
        apiToken = tokenRefresher.getNewToken(refreshToken);
    }

    @Override
    public String getToken() {
        return apiToken;
    }
}
