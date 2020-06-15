package edu.ksu.canvas.requestOptions;

/**
 * The options needed when uploading a file: https://canvas.instructure.com/doc/api/file.file_uploads.html
 */
public class UploadOptions extends BaseOptions {

    public UploadOptions(String name) {
        addSingleItem("name", name);
    }

    public UploadOptions size(long size) {
        addSingleItem("size", Long.toString(size));
        return this;
    }

    public UploadOptions contentType(String contentType) {
        addSingleItem("content_type", contentType);
        return this;
    }

    public UploadOptions parentFolderId(long parentFolderId) {
        addSingleItem("parent_folder_id", Long.toString(parentFolderId));
        return this;
    }

    public UploadOptions parentFolderPath(String parentFolderPath) {
        addSingleItem("parent_folder_path", parentFolderPath);
        return this;
    }

    public UploadOptions onDuplicateRename() {
        addSingleItem("on_duplicate", "rename");
        return this;
    }
}
