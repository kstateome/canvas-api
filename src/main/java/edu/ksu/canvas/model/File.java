package edu.ksu.canvas.model;

import java.time.Instant;

/**
 * https://canvas.instructure.com/doc/api/files.html#method.files.api_show
 */
public class File {

    private Long id;
    private String uuid;
    private Long folderId;
    private String displayName;
    private String filename;
    // private String content-type;
    private String url;
    private Long size;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant unlockAt;
    private Instant lockAt;
    private Instant modifiedAt;

    private Boolean locked;
    private Boolean hidden;

    private String thumbnailUrl;
    private String previewUrl;

    private String mimeClass;
    private String mediaEntryId;
    private Boolean lockedForUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getFolderId() {
        return folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getUnlockAt() {
        return unlockAt;
    }

    public void setUnlockAt(Instant unlockAt) {
        this.unlockAt = unlockAt;
    }

    public Instant getLockAt() {
        return lockAt;
    }

    public void setLockAt(Instant lockAt) {
        this.lockAt = lockAt;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getMimeClass() {
        return mimeClass;
    }

    public void setMimeClass(String mimeClass) {
        this.mimeClass = mimeClass;
    }

    public String getMediaEntryId() {
        return mediaEntryId;
    }

    public void setMediaEntryId(String mediaEntryId) {
        this.mediaEntryId = mediaEntryId;
    }

    public Boolean getLockedForUser() {
        return lockedForUser;
    }

    public void setLockedForUser(Boolean lockedForUser) {
        this.lockedForUser = lockedForUser;
    }
}
