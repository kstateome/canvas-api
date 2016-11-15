package edu.ksu.canvas.oauth;

public class OauthTokenRefresher {
    private final String clientId;
    private final String clientSecret;
    private final String canvasUrl;

    public OauthTokenRefresher(String clientId, String clientSecret, String canvasUrl) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.canvasUrl = canvasUrl;
    }

    public String getNewToken(String refreshToken) {
        return "";
    }
}
