package edu.ksu.canvas.model.sisimport;
import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;
import edu.ksu.canvas.model.BaseCanvasModel;

import java.io.Serializable;
import java.time.Instant;

/**
 * Class to represent Canvas Sis Imports Errors Attachment
 * See <a href="https://canvas.instructure.com/doc/api/sis_imports.html#SisImport">SisImport</a> documentation.
 */
 @CanvasObject(postKey = "errors_attachment")
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

    @CanvasField(postKey = "folder_id")
    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    @CanvasField(postKey = "display_name")
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

    @CanvasField(postKey = "upload_status")
    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    @CanvasField(postKey = "content-type")
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

    @CanvasField(postKey = "created_at")
    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @CanvasField(postKey = "updated_at")
    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @CanvasField(postKey = "unlock_at")
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

    @CanvasField(postKey = "lock_at")
    public Instant getLockAt() {
        return lockAt;
    }

    public void setLockAt(Instant lockAt) {
        this.lockAt = lockAt;
    }

    @CanvasField(postKey = "hidden_for_user")
    public boolean getHiddenForUser() {
        return hiddenForUser;
    }

    public void setHiddenForUser(boolean hiddenForUser) {
        this.hiddenForUser = hiddenForUser;
    }

    @CanvasField(postKey = "thumbnail_url")
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @CanvasField(postKey = "modified_at")
    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @CanvasField(postKey = "mime_class")
    public String getMimeClass() {
        return mimeClass;
    }

    public void setMimeClass(String mimeClass) {
        this.mimeClass = mimeClass;
    }

    @CanvasField(postKey = "media_entry_id")
    public String getMediaEntryId() {
        return mediaEntryId;
    }

    public void setMediaEntryId(String mediaEntryId) {
        this.mediaEntryId = mediaEntryId;
    }

    @CanvasField(postKey = "locked_for_user")
    public boolean getLockedForUser() {
        return lockedForUser;
    }

    public void setLockedForUser(boolean lockedForUser) {
        this.lockedForUser = lockedForUser;
    }

}

