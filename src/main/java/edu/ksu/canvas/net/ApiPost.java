package edu.ksu.canvas.net;

import java.io.Serializable;
import java.util.HashMap;

public class ApiPost implements Serializable{

    private static final long serialVersionUID = 1L;

    private String oauthToken;
    private String url;
    private String method;
    private HashMap<String, String> postParameters;

    public ApiPost(String oauthToken, String url, String method, HashMap<String, String> postParameters) {
        this.oauthToken = oauthToken;
        this.url = url;
        this.method = method;
        this.postParameters = postParameters;
    }

    public String getOauthToken() {
        return oauthToken;
    }

    public void setOauthToken(String oauthToken) {
        this.oauthToken = oauthToken;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public HashMap<String, String> getPostParameters() {
        return postParameters;
    }

    public void setPostParameters(HashMap<String, String> postParameters) {
        this.postParameters = postParameters;
    }
}
