package edu.ksu.canvas.model;

import java.io.Serializable;
import java.util.Date;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;

@CanvasObject(postKey = "wiki_page")
public class Page extends BaseCanvasModel implements Serializable {
    public static final long serialVersionUID = 1L;

    private String url;
    private String title;
    private Date createdAt;
    private Date updatedAt;
    private String editingRoles;
    private PageUser lastEditedBy;
    private String body;
    private Boolean published;
    private Boolean frontPage;
    private Boolean lockedForUser;
    private String lockInfo;
    private String lockExplanation;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @CanvasField(postKey = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @CanvasField(postKey = "editing_roles")
    public String getEditingRoles() {
        return editingRoles;
    }

    public void setEditingRoles(String editingRoles) {
        this.editingRoles = editingRoles;
    }

    public PageUser getLastEditedBy() {
        return lastEditedBy;
    }

    public void setLastEditedBy(PageUser lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
    }

    @CanvasField(postKey = "body")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @CanvasField(postKey = "published")
    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    @CanvasField(postKey = "front_page")
    public Boolean getFrontPage() {
        return frontPage;
    }

    public void setFrontPage(Boolean frontPage) {
        this.frontPage = frontPage;
    }

    public Boolean getLockedForUser() {
        return lockedForUser;
    }

    public void setLockedForUser(Boolean lockedForUser) {
        this.lockedForUser = lockedForUser;
    }

    public String getLockInfo() {
        return lockInfo;
    }

    public void setLockInfo(String lockInfo) {
        this.lockInfo = lockInfo;
    }

    public String getLockExplanation() {
        return lockExplanation;
    }

    public void setLockExplanation(String lockExplanation) {
        this.lockExplanation = lockExplanation;
    }

    /**
     * Pages include a "last edited by user" object. Unfortunately it doesn't
     * seem to be any kind of existing Canvas User or DisplayUser object type.
     */
    public class PageUser implements Serializable {
        private static final long serialVersionUID = 1L;

        private Long id;
        private String displayName;
        private String avatarImageUrl;
        private String htmlUrl;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
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
}
