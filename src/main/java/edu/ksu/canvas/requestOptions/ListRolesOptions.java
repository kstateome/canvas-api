package edu.ksu.canvas.requestOptions;

import java.util.List;

/**
 * A class to represent parameters to be passed to the "List the roles available to an account" API call.
 * See <a href="https://canvas.instructure.com/doc/api/roles.html#method.role_overrides.api_index">
 *              https://canvas.instructure.com/doc/api/roles.html#method.role_overrides.api_index</a>
 */
public class ListRolesOptions extends BaseOptions {

    private String accountId;

    public enum State {
        ACTIVE, INACTIVE;
        @Override
        public String toString() { return name().toLowerCase(); }
    }

    public ListRolesOptions(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    /**
     * If set to true, inherited roles from parent accounts will be included
     * @param show Whether or not to include inherited roles
     * @return This object to allow adding more options
     */
    public ListRolesOptions showInherited(Boolean show) {
        if( show != null ) {
            addSingleItem("show_inherited", show.toString());
        }
        return this;
    }

    /**
     * Filter returned roles by activity state. Default is only "active" roles
     * @param states List of states to filter by (active or inactive)
     * @return This object to allow adding more options
     */
    public ListRolesOptions state(List<State> states) {
        addEnumList("state[]", states);
        return this;
    }
}
