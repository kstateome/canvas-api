package edu.ksu.canvas;

public class OauthToken {
    protected String refreshToken;

    public OauthToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    String getToken() {
        return refreshToken;
    }

    public void refresh() {

    }
}
