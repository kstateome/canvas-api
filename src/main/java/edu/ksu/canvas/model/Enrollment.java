package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Class to represent Canvas enrollments.
 * See <a href="https://canvas.instructure.com/doc/api/enrollments.html#Enrollment">Canvas Enrollment</a> documentation.
 */
@CanvasObject(postKey = "enrollment")
public class Enrollment extends BaseCanvasModel implements Serializable {
    private static final long serialVersionUID = 3L;

    private long id;
    private Integer courseId;
    private String sisCourseId;
    private String courseIntegrationId;
    private String courseSectionId;
    private String sectionIntegrationId;
    private String sisSectionId;
    private String enrollmentState;
    private Boolean limitPrivilegesToCourseSection;
    private String sisImportId;
    private Integer rootAccountId;
    private String type;
    private String userId;
    private Integer associatedUserId;
    private String role;
    private Date updatedAt;
    private Date startAt;
    private Date endAt;
    private Date lastActivityAt;
    private long totalActivityTime;
    private String htmlUrl;
    private Grade grades;
    private User user;
    private Long roleId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @CanvasField(postKey = "role_id")
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getSisCourseId() {
        return sisCourseId;
    }

    public void setSisCourseId(String sisCourseId) {
        this.sisCourseId = sisCourseId;
    }

    public String getCourseIntegrationId() {
        return courseIntegrationId;
    }

    public void setCourseIntegrationId(String courseIntegrationId) {
        this.courseIntegrationId = courseIntegrationId;
    }

    public String getCourseSectionId() {
        return courseSectionId;
    }

    public void setCourseSectionId(String courseSectionId) {
        this.courseSectionId = courseSectionId;
    }

    public String getSectionIntegrationId() {
        return sectionIntegrationId;
    }

    public void setSectionIntegrationId(String sectionIntegrationId) {
        this.sectionIntegrationId = sectionIntegrationId;
    }

    public String getSisSectionId() {
        return sisSectionId;
    }

    public void setSisSectionId(String sisSectionId) {
        this.sisSectionId = sisSectionId;
    }

    @CanvasField(postKey = "enrollment_state")
    public String getEnrollmentState() {
        return enrollmentState;
    }

    public void setEnrollmentState(String enrollmentState) {
        this.enrollmentState = enrollmentState;
    }

    @CanvasField(postKey = "limit_privileges_to_course_section")
    public Boolean getLimitPrivilegesToCourseSection() {
        return limitPrivilegesToCourseSection;
    }

    public void setLimitPrivilegesToCourseSection(Boolean limitPrivilegesToCourseSection) {
        this.limitPrivilegesToCourseSection = limitPrivilegesToCourseSection;
    }

    public String getSisImportId() {
        return sisImportId;
    }

    public void setSisImportId(String sisImportId) {
        this.sisImportId = sisImportId;
    }

    public Integer getRootAccountId() {
        return rootAccountId;
    }

    public void setRootAccountId(Integer rootAccountId) {
        this.rootAccountId = rootAccountId;
    }

    @CanvasField(postKey = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @CanvasField(postKey = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getAssociatedUserId() {
        return associatedUserId;
    }

    public void setAssociatedUserId(Integer associatedUserId) {
        this.associatedUserId = associatedUserId;
    }

    @Deprecated
    @CanvasField(postKey = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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

    public Date getLastActivityAt() {
        return lastActivityAt;
    }

    public void setLastActivityAt(Date lastActivityAt) {
        this.lastActivityAt = lastActivityAt;
    }

    public long getTotalActivityTime() {
        return totalActivityTime;
    }

    public void setTotalActivityTime(long totalActivityTime) {
        this.totalActivityTime = totalActivityTime;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public Grade getGrades() {
        return grades;
    }

    public void setGrades(Grade grades) {
        this.grades = grades;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enrollment)) return false;
        Enrollment that = (Enrollment) o;
        // Use only attributes that can always be seen
        return Objects.equals(courseId, that.courseId) &&
                Objects.equals(courseSectionId, that.courseSectionId) &&
                Objects.equals(enrollmentState, that.enrollmentState) &&
                Objects.equals(limitPrivilegesToCourseSection, that.limitPrivilegesToCourseSection) &&
                Objects.equals(rootAccountId, that.rootAccountId) &&
                Objects.equals(type, that.type) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(associatedUserId, that.associatedUserId) &&
                Objects.equals(role, that.role) &&
                Objects.equals(user, that.user) &&
                Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseSectionId, enrollmentState, limitPrivilegesToCourseSection, rootAccountId, type, userId, associatedUserId, role, user, roleId);
    }
}
