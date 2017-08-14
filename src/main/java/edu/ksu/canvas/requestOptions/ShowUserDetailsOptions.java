package edu.ksu.canvas.requestOptions;

/**
 * Show user details options wrapper for the "show user details" API call with required parameters
 */
public class ShowUserDetailsOptions extends BaseOptions {

    private String userId;

    private String sisIntegrationId;

    public String getUserId() {
        return userId;
    }

    /**
    * Either user id or sisIntegrationId is required
    * @param userId the id of the user
    */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSisIntegrationId() {
        return sisIntegrationId;
    }

    /**
    * sisIntegrationId of the user
    * @param sisIntegrationId system integration id of the user
    */
    public void setSisIntegrationId(String sisIntegrationId) {
        this.sisIntegrationId = sisIntegrationId;
    }

}
