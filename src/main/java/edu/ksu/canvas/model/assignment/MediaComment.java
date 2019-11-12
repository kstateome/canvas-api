package edu.ksu.canvas.model.assignment;

import com.google.gson.annotations.SerializedName;
import edu.ksu.canvas.model.BaseCanvasModel;

import java.io.Serializable;

/**
 * Object to represent a Media Comment which can optionally be embedded inside of a submission comment object
 */
public class MediaComment extends BaseCanvasModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("content-type") //Needed because there is a dash instead of an underscore coming from Canvas
    private String contentType;
    private String displayName;
    private String mediaId;
    private String mediaType;
    private String url;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
