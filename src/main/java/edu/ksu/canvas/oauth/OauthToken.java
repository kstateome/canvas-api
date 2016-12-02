package edu.ksu.canvas.oauth;

import java.io.Serializable;

public interface OauthToken extends Serializable {

    String getAccessToken();
   
    void refresh();
}
