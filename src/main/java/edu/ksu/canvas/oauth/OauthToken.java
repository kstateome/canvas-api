package edu.ksu.canvas.oauth;

import org.apache.hc.core5.http.ParseException;

import java.io.Serializable;

public interface OauthToken extends Serializable {

    String getAccessToken() throws ParseException;
   
    void refresh() throws ParseException;
}
