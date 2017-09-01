package edu.ksu.canvas.model.assignment;

import java.io.Serializable;
import java.util.Date;

/**
 * Class representing due dates for assignments and quizzes.
 * See <a href="https://canvas.instructure.com/doc/api/assignments.html#AssignmentDate">API docs</a>
 *
 */
public class AssignmentDate implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private Date dueAt;
    private Date unlockAt;
    private Date lockAt;
    private Boolean base;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDueAt() {
        return dueAt;
    }

    public void setDueAt(Date dueAt) {
        this.dueAt = dueAt;
    }

    public Date getUnlockAt() {
        return unlockAt;
    }

    public void setUnlockAt(Date unlockAt) {
        this.unlockAt = unlockAt;
    }

    public Date getLockAt() {
        return lockAt;
    }

    public void setLockAt(Date lockAt) {
        this.lockAt = lockAt;
    }

    public Boolean getBase() {
        return base;
    }

    public void setBase(Boolean base) {
        this.base = base;
    }
}