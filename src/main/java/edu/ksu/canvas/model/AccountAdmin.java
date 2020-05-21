package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasField;

/**
 * Class to represent Canvas Account Admins.
 * See <a href="https://canvas.instructure.com/doc/api/admins.html#Admin">Admins</a> documentation.
 */
public class AccountAdmin {
    public static final long serialVersionUID = 1L;

    private Long id;
    private String role;
    private Long roleId;
    private User user;
    private String workflowState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @CanvasField(postKey = "workflow_state")
    public String getWorkflowState() {
        return workflowState;
    }

    public void setWorkflowState(String workflowState) {
        this.workflowState = workflowState;
    }
}
