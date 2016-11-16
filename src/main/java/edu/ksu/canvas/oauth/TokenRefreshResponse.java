package edu.ksu.canvas.oauth;

/**
 * Object to represent the JSON received back from the call to get a new access token.
 */
public class TokenRefreshResponse {

    private String accessToken;
    private String tokenType;
    private Long expiresIn;
    private TokenUser user;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Long getExpiresIn() {
        if (expiresIn == null || expiresIn == 0) {
            return null;
        }
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public TokenUser getUser() {
        return user;
    }

    public void setUser(TokenUser user) {
        this.user = user;
    }

    public class TokenUser {
        private Integer id;
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
