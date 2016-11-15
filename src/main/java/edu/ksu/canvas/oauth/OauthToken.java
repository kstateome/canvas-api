package edu.ksu.canvas.oauth;

public interface OauthToken {

    public String getAccessToken();
   
    public void refresh();
}
