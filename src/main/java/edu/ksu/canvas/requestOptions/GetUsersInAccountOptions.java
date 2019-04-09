package edu.ksu.canvas.requestOptions;

import java.util.List;

/**
 * Options class which supports queries used to retrieve all users for a given account.
 * <p>
 * Standard information which can be queried upon include:
 * <ul>
 *     <li>A search term - which behaves analogously to the browser search in what it accepts and searches on</li>
 *     <li>The column with which to sort by - username, email, sis_id and login are acceptable options</li>
 *     <li>The overall ordering cardinality of the data brought back</li>
 * </ul>
 * <p>
 * Two commonplace query parameters are also added for querying convenience:
 * <ul>
 *     <li><code>per_page</code> - useful to indicate how many records you want to return back.  Globally defaults to 10,
 *     but may be overridden here.
 *     </li>
 *     <li><code>include[]</code> - useful to include extra values that you are not seeing in the data objects.
 *      Acceptable values are avatar_url, email, last_login and time_zone.
 *     </li>
 * </ul>
 * <p>
 * The values which are supported with this are found
 * <a href="https://canvas.instructure.com/doc/api/users.html#method.users.index">on the "List users in account"</a> portion of the Canvas API.
 */
public class GetUsersInAccountOptions extends BaseOptions {

    public enum Include {
        avatar_url, email, last_login, time_zone
    }

    private int accountId;

    public GetUsersInAccountOptions(final int accountId) {
        this.accountId = accountId;
    }

    public int getAccountId() {
        return accountId;
    }


    public GetUsersInAccountOptions searchTerm(final String searchTerm) {
        addSingleItem("search_term", searchTerm);
        return this;
    }

    public GetUsersInAccountOptions sort(final String sort) {
        addSingleItem("sort", sort);
        return this;
    }

    public GetUsersInAccountOptions order(final String order) {
        addSingleItem("order", order);
        return this;
    }

    public GetUsersInAccountOptions include(List<Include> includes) {
        addEnumList("include[]", includes);
        return this;
    }
}
