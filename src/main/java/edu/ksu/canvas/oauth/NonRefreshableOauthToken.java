package edu.ksu.canvas.oauth;

public class NonRefreshableOauthToken implements OauthToken {

    private String token;

    public NonRefreshableOauthToken(String token) {
        this.token = token;
    }
    @Override
    public String getAccessToken() {
        return token;
    }

    @Override
    public void refresh() { }

}
