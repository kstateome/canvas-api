package edu.ksu.canvas.model;

import java.util.Date;
import java.util.List;

public class AssignmentOverride {
    private Integer id;
    private Integer assignment_id;
    private List<Integer> student_ids;
    private Integer group_id;
    private Integer course_section_id;
    private String title;
    private Date due_at;
    private Boolean all_day;
    private Date all_day_date;
    private Date unlock_at;
    private Date lock_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAssignment_id() {
        return assignment_id;
    }

    public void setAssignment_id(Integer assignment_id) {
        this.assignment_id = assignment_id;
    }

    public List<Integer> getStudent_ids() {
        return student_ids;
    }

    public void setStudent_ids(List<Integer> student_ids) {
        this.student_ids = student_ids;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public Integer getCourse_section_id() {
        return course_section_id;
    }

    public void setCourse_section_id(Integer course_section_id) {
        this.course_section_id = course_section_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDue_at() {
        return due_at;
    }

    public void setDue_at(Date due_at) {
        this.due_at = due_at;
    }

    public Boolean getAll_day() {
        return all_day;
    }

    public void setAll_day(Boolean all_day) {
        this.all_day = all_day;
    }

    public Date getAll_day_date() {
        return all_day_date;
    }

    public void setAll_day_date(Date all_day_date) {
        this.all_day_date = all_day_date;
    }

    public Date getUnlock_at() {
        return unlock_at;
    }

    public void setUnlock_at(Date unlock_at) {
        this.unlock_at = unlock_at;
    }

    public Date getLock_at() {
        return lock_at;
    }

    public void setLock_at(Date lock_at) {
        this.lock_at = lock_at;
    }
}
