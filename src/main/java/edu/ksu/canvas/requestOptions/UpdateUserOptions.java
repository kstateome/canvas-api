package edu.ksu.canvas.requestOptions;

/**
 * Options class which supports queries used to update a user.
 * <p>
 * Standard information which one can update with this endpoint include:
 * <ul>
 *     <li>The user's name</li>
 *     <li>The user's short name</li>
 *     <li>The user's sortable name (typically in the form of "Last, First")</li>
 *     <li>The user's time zone (IANA formatted)</li>
 *     <li>The user's email</li>
 *     <li>The user's locale (formatted by RFC-5646)</li>
 *     <li>The user's avatar token</li>
 *     <li>The user's avatar URL</li>
 * </ul>
 * The values which are supported with this are found
 * <a href="https://canvas.instructure.com/doc/api/users.html#method.users.update">on the "Edit a User" portion of the Canvas API.</a>
 * You are expected to supply the internal Canvas user ID, but this is easily substituted for the <code>sis_user_id:123</code> form as well.
 */
public class UpdateUserOptions extends BaseOptions {

    private String internalUserId;

    public UpdateUserOptions(String internalUserId) {
        this. internalUserId = internalUserId;
    }

    public String getInternalUserId() {
        return internalUserId;
    }

    public UpdateUserOptions name(final String name) {
        addSingleItem("user[name]", name);
        return this;
    }

    public UpdateUserOptions shortName(final String shortName) {
        addSingleItem("user[short_name]", shortName);
        return this;
    }

    public UpdateUserOptions sortableName(final String sortableName) {
        addSingleItem("user[sortable_name]", sortableName);
        return this;
    }

    public UpdateUserOptions timeZone(final String timeZone) {
        addSingleItem("user[time_zone]", timeZone);
        return this;
    }

    public UpdateUserOptions email(final String email) {
        addSingleItem("user[email]", email);
        return this;
    }

    public UpdateUserOptions locale(final String locale) {
        addSingleItem("user[locale]", locale);
        return this;
    }

    public UpdateUserOptions avatarToken(final String avatarToken) {
        addSingleItem("user[avatar][token]", avatarToken);
        return this;
    }

    public UpdateUserOptions avatarUrl(final String avatarUrl) {
        addSingleItem("user[avatar][url]", avatarUrl);
        return this;
    }

}
