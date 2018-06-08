package edu.ksu.canvas.requestOptions;

/**
 * Options class which supports queries used to update login information.
 * <p>
 * Standard information which one can update with this endpoint include:
 * <ul>
 *     <li>The internal unique ID the user is assigned in Canvas</li>
 *     <li>The user's password</li>
 *     <li>The user's SIS User ID</li>
 *     <li>The user's integration ID</li>
 * </ul>
 * <p>
 * The values which are supported with this are found
 * <a href="https://canvas.instructure.com/doc/api/logins.html#method.pseudonyms.update">on the "Edit a user login" portion of the Canvas API.</a>
 *
 */
public class UpdateLoginOptions extends BaseOptions {
    private String accountId;
    private String loginId;

    public UpdateLoginOptions(String accountId, String loginId) {
        this.accountId = accountId;
        this.loginId = loginId;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getLoginId() {
        return loginId;
    }

    public UpdateLoginOptions uniqueId(final String uniqueId) {
        addSingleItem("login[unique_id]", uniqueId);
        return this;
    }

    public UpdateLoginOptions password(final String password) {
        addSingleItem("login[password]", password);
        return this;
    }

    public UpdateLoginOptions sisUserId(final String sisUserId) {
        addSingleItem("login[sis_user_id]", sisUserId);
        return this;
    }

    public UpdateLoginOptions integrationId(final String integrationId) {
        addSingleItem("login[integration_id]", integrationId);
        return this;
    }
}
