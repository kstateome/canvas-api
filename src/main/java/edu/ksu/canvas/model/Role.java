package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasObject;

/**
 * Class to represent Canvas roles.
 * See <a href="https://canvas.instructure.com/doc/api/roles.html#Role">Roles</a> documentation.
 */
@CanvasObject(postKey = "role")
public class Role {
    private long id;
    private String label;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
}
