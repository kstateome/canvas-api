package edu.ksu.canvas;

public class OauthToken {
    protected String refreshToken;

    public OauthToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return refreshToken;
    }

    public void refresh() {

    }
}
