package edu.ksu.canvas.model.assignment;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import edu.ksu.canvas.annotation.CanvasObject;
import edu.ksu.canvas.model.BaseCanvasModel;

@CanvasObject(postKey="assignment_override")
public class AssignmentOverride extends BaseCanvasModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer assignmentId;
    private List<Integer> studentIds;
    private Integer groupId;
    private Integer courseSectionId;
    private String title;
    private Date dueAt;
    private Boolean allDay;
    private Date allDayDate;
    private Date unlockAt;
    private Date lockAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public List<Integer> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<Integer> studentIds) {
        this.studentIds = studentIds;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getCourseSectionId() {
        return courseSectionId;
    }

    public void setCourseSectionId(Integer courseSectionId) {
        this.courseSectionId = courseSectionId;
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

    public Boolean getAllDay() {
        return allDay;
    }

    public void setAllDay(Boolean allDay) {
        this.allDay = allDay;
    }

    public Date getAllDayDate() {
        return allDayDate;
    }

    public void setAllDayDate(Date allDayDate) {
        this.allDayDate = allDayDate;
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
}
