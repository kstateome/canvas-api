package edu.ksu.canvas.model;

import java.io.Serializable;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;

/**
 * Class to represent UI tabs in Canvas.
 * See https://canvas.colorado.edu/doc/api/tabs.html
 */
@CanvasObject(postKey = "tab")
public class Tab extends BaseCanvasModel implements Serializable {

    public static final long serialVersionUID = 1L;

    private String htmlUrl;
    private String id;
    private String label;
    private String type;
    private boolean hidden;
    private String visibility;
    private int postion;

    @CanvasField(postKey = "html_url")
    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    // TODO: consider creating an enum for the visibility property.  Currently,
    // visibility cannot be changed through the API, so it seems premature to
    // do this.

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }
}
