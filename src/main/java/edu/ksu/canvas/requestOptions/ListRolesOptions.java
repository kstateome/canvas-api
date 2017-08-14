package edu.ksu.canvas.requestOptions;

/**
 * A class to represent parameters to be passed to the "List the roles available to an account" API call.
 * See <a href="https://canvas.instructure.com/doc/api/roles.html#method.role_overrides.api_index">
 *              https://canvas.instructure.com/doc/api/roles.html#method.role_overrides.api_index</a>
 */
public class ListRolesOptions extends BaseOptions {

    private String accountId;

    public ListRolesOptions(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public ListRolesOptions showInherited(Boolean show) {
        if( show != null ) {
            addSingleItem("show_inherited", show.toString());
        }
        return this;
    }
}
