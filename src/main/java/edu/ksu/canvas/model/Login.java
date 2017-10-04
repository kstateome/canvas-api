package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.*;

import java.io.Serializable;

/**
 * Class to represent Canvas user logins. See the
 * <a href="https://canvas.instructure.com/doc/api/logins.html">Logins API</a>
 * documentation.
 */
@CanvasObject(postKey = "login")
@SuppressWarnings("serial")
public class Login extends BaseCanvasModel implements Serializable {
    private String id;
    private String userId;
    private String accountId;
    private String uniqueId;
    private String sisUserId;
    private String integrationId;
    private String authenticationProviderId;
    private String authenticationProviderType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @CanvasField(overrideObjectKey = "user", postKey = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @CanvasField(postKey = "unique_id")
    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    @CanvasField(postKey = "sis_user_id")
    public String getSisUserId() {
        return sisUserId;
    }

    public void setSisUserId(String sisUserId) {
        this.sisUserId = sisUserId;
    }

    @CanvasField(postKey = "integration_id")
    public String getIntegrationId() {
        return integrationId;
    }

    public void setIntegrationId(String integrationId) {
        this.integrationId = integrationId;
    }

    @CanvasField(postKey = "authentication_provider_id")
    public String getAuthenticationProviderId() {
        return authenticationProviderId;
    }

    public void setAuthenticationProviderId(String authenticationProviderId) {
        this.authenticationProviderId = authenticationProviderId;
    }

    public String getAuthenticationProviderType() {
        return authenticationProviderType;
    }

    public void setAuthenticationProviderType(String authenticationProviderType) {
        this.authenticationProviderType = authenticationProviderType;
    }
}
