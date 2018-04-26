package edu.ksu.canvas.model;

import java.io.Serializable;

import edu.ksu.canvas.annotation.CanvasObject;

/**
 * Class to represent Canvas roles.
 * See <a href="https://canvas.instructure.com/doc/api/roles.html#Role">Roles</a> documentation.
 */
@CanvasObject(postKey = "role")
public class Role extends BaseCanvasModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String label;
    private String baseRoleType;

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
    public String getBaseRoleType() {
        return baseRoleType;
    }
    public void setBaseRoleType(String baseRoleType) {
        this.baseRoleType = baseRoleType;
    }
}
