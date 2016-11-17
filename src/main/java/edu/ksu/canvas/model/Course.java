package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Class to represent Canvas courses.
 * See <a href="https://canvas.instructure.com/doc/api/courses.html#Course">Canvas courses</a> documentation.
 */
@CanvasObject(postKey = "course")
public class Course extends BaseCanvasModel implements Serializable {
    public static final long serialVersionUID = 1L;

    private Integer accountId;
    private String courseCode;
    private String defaultView;
    private Integer id;
    private String name;
    private Date startAt;
    private Date endAt;
    private Boolean publicSyllabus;
    private Integer storageQuotaMb;
    private Boolean hideFinalGrades;
    private Boolean applyAssignmentGroupWeights;
    private String sisCourseId;
    private Integer integrationId;
    private String workflowState;
    private Integer totalStudents;

    @CanvasField(postKey = "account_id")
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    @CanvasField(postKey = "course_code")
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

    @CanvasField(postKey = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @CanvasField(postKey = "start_at")
    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    @CanvasField(postKey = "end_at")
    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    @CanvasField(postKey = "public_syllabus")
    public Boolean getPublicSyllabus() {
        return publicSyllabus;
    }

    public void setPublicSyllabus(Boolean publicSyllabus) {
        this.publicSyllabus = publicSyllabus;
    }

    public Integer getStorageQuotaMb() {
        return storageQuotaMb;
    }

    public void setStorageQuotaMb(Integer storageQuotaMb) {
        this.storageQuotaMb = storageQuotaMb;
    }

    @CanvasField(postKey = "hide_final_grades")
    public Boolean getHideFinalGrades() {
        return hideFinalGrades;
    }

    public void setHideFinalGrades(Boolean hideFinalGrades) {
        this.hideFinalGrades = hideFinalGrades;
    }

    @CanvasField(postKey = "apply_assignment_group_weights")
    public Boolean getApplyAssignmentGroupWeights() {
        return applyAssignmentGroupWeights;
    }

    public void setApplyAssignmentGroupWeights(Boolean applyAssignmentGroupWeights) {
        this.applyAssignmentGroupWeights = applyAssignmentGroupWeights;
    }

    @CanvasField(postKey = "sis_course_id")
    public String getSisCourseId() {
        return sisCourseId;
    }

    public void setSisCourseId(String sisCourseId) {
        this.sisCourseId = sisCourseId;
    }

    @CanvasField(postKey = "integration_id")
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
