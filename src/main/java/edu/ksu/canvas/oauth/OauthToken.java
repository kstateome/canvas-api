package edu.ksu.canvas.oauth;

public interface OauthToken {

    String getAccessToken();
   
    void refresh();
}
