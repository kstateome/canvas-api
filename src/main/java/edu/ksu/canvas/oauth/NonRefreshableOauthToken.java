package edu.ksu.canvas.oauth;

public class NonRefreshableOauthToken implements OauthToken {
    private static final long serialVersionUID = 1L;

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
