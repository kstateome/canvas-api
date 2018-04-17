package edu.ksu.canvas.model;

import java.io.Serializable;

/**
 * Class to represent Canvas user mini-object.
 * See the <a href="https://canvas.instructure.com/doc/api/users.html#UserDisplay">Canvas User</a> documentation.
 */
public class UserDisplay implements Serializable {
    public static final long serialVersionUID = 1L;

    private int id;
    private String displayName;
    private String avatarImageUrl;
    private String htmlUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAvatarImageUrl() {
        return avatarImageUrl;
    }

    public void setAvatarImageUrl(String avatarImageUrl) {
        this.avatarImageUrl = avatarImageUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }
}
