package edu.ksu.canvas.model.sisimport;
import edu.ksu.canvas.model.BaseCanvasModel;

import java.io.Serializable;
import java.time.Instant;

/**
 * Class to represent Canvas Sis Imports Errors Attachment
 * See <a href="https://canvas.instructure.com/doc/api/sis_imports.html#SisImport">SisImport</a> documentation.
 */
public class SisImportErrorsAttachment extends BaseCanvasModel implements Serializable {
    public static final long serialVersionUID = 1L;

    private Integer id;
    private String uuid;
    private String folderId;
    private String displayName;
    private String filename;
    private String uploadStatus;
    private String contentType;
    private String url;
    private Integer size;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant unlockAt;
    private boolean locked;
    private boolean hidden;
    private Instant lockAt;
    private boolean hiddenForUser;
    private String thumbnailUrl;
    private Instant modifiedAt;
    private String mimeClass;
    private String mediaEntryId;
    private boolean lockedForUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFileName() {
        return filename;
    }

    public void setFileName(String filename) {
        this.filename = filename;
    }

    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
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

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean getHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public Instant getLockAt() {
        return lockAt;
    }

    public void setLockAt(Instant lockAt) {
        this.lockAt = lockAt;
    }

    public boolean getHiddenForUser() {
        return hiddenForUser;
    }

    public void setHiddenForUser(boolean hiddenForUser) {
        this.hiddenForUser = hiddenForUser;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
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

    public boolean getLockedForUser() {
        return lockedForUser;
    }

    public void setLockedForUser(boolean lockedForUser) {
        this.lockedForUser = lockedForUser;
    }

}

