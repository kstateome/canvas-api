package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Class to represent Canvas course sections.
 * See <a href="https://canvas.instructure.com/doc/api/sections.html#Section">Canvas Section</a> documentation.
 */
@CanvasObject(postKey = "course_section")
public class Section extends BaseCanvasModel implements Serializable {
    public static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String sisSectionId;
    private String integrationId;
    private String sisImportId;
    private String enrollmentRole;
    private Long courseId;
    private String sisCourseId;
    private Date startAt;
    private Date endAt;
    private Long nonxlistCourseId;
    private Long totalStudents;
    private Boolean restrictEnrollmentsToSectionDates;
    private List<User> students;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @CanvasField(postKey = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @CanvasField(postKey = "sis_section_id")
    public String getSisSectionId() {
        return sisSectionId;
    }

    public void setSisSectionId(String sisSectionId) {
        this.sisSectionId = sisSectionId;
    }

    public String getIntegrationId() {
        return integrationId;
    }

    public void setIntegrationId(String integrationId) {
        this.integrationId = integrationId;
    }

    public String getSisImportId() {
        return sisImportId;
    }

    public void setSisImportId(String sisImportId) {
        this.sisImportId = sisImportId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getSisCourseId() {
        return sisCourseId;
    }

    public void setSisCourseId(String sisCourseId) {
        this.sisCourseId = sisCourseId;
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

    public Long getNonxlistCourseId() {
        return nonxlistCourseId;
    }

    public void setNonxlistCourseId(Long nonxlistCourseId) {
        this.nonxlistCourseId = nonxlistCourseId;
    }

    public Long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Boolean getRestrictEnrollmentsToSectionDates() {
        return restrictEnrollmentsToSectionDates;
    }

    public void setRestrictEnrollmentsToSectionDates(Boolean restrictEnrollmentsToSectionDates) {
        this.restrictEnrollmentsToSectionDates = restrictEnrollmentsToSectionDates;
    }

    @CanvasField(postKey = "enrollment_role")
    public String getEnrollmentRole() {
        return enrollmentRole;
    }

    public void setEnrollmentRole(String enrollmentRole) {
        this.enrollmentRole = enrollmentRole;
    }

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }
}
