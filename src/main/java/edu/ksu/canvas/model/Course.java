package edu.ksu.canvas.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Class to represent Canvas courses.
 * See <a href="https://canvas.instructure.com/doc/api/courses.html#Course">Canvas courses</a> documentation.
 */
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer accountId;
    private String courseCode;
    private String defaultView;
    private Integer id;
    private String name;
    private Date startAt;
    private Date endAt;
    private Boolean publicSyllabus;
    private Integer sotrageQuotaMb;
    private Boolean hideFinalGrades;
    private Boolean applyAssignmentGroupWeights;
    private String sisCourseId;
    private Integer integrationId;
    private String workflowState;
    private Integer totalStudents;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getDefaultView() {
        return defaultView;
    }

    public void setDefaultView(String defaultView) {
        this.defaultView = defaultView;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public Boolean getPublicSyllabus() {
        return publicSyllabus;
    }

    public void setPublicSyllabus(Boolean publicSyllabus) {
        this.publicSyllabus = publicSyllabus;
    }

    public Integer getSotrageQuotaMb() {
        return sotrageQuotaMb;
    }

    public void setSotrageQuotaMb(Integer sotrageQuotaMb) {
        this.sotrageQuotaMb = sotrageQuotaMb;
    }

    public Boolean getHideFinalGrades() {
        return hideFinalGrades;
    }

    public void setHideFinalGrades(Boolean hideFinalGrades) {
        this.hideFinalGrades = hideFinalGrades;
    }

    public Boolean getApplyAssignmentGroupWeights() {
        return applyAssignmentGroupWeights;
    }

    public void setApplyAssignmentGroupWeights(Boolean applyAssignmentGroupWeights) {
        this.applyAssignmentGroupWeights = applyAssignmentGroupWeights;
    }

    public String getSisCourseId() {
        return sisCourseId;
    }

    public void setSisCourseId(String sisCourseId) {
        this.sisCourseId = sisCourseId;
    }

    public Integer getIntegrationId() {
        return integrationId;
    }

    public void setIntegrationId(Integer integrationId) {
        this.integrationId = integrationId;
    }

    public String getWorkflowState() {
        return workflowState;
    }

    public void setWorkflowState(String workflowState) {
        this.workflowState = workflowState;
    }

    public Integer getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Integer totalStudents) {
        this.totalStudents = totalStudents;
    }
}
