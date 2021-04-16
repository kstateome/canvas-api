package edu.ksu.canvas.model;

import java.util.Map;

/**
 * This is part of the file upload workflow: https://canvas.instructure.com/doc/api/file.file_uploads.html
 * It's the data that is returned after asking to upload a file.
 */
public class Deposit {
    // This doesn't extend basemodel as it's never sent back to Canvas in the normal way.

    private String uploadUrl;
    private Map<String, String> uploadParams;

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public Map<String, String> getUploadParams() {
        return uploadParams;
    }

    public void setUploadParams(Map<String, String> uploadParams) {
        this.uploadParams = uploadParams;
    }
}
