package edu.ksu.canvas.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Class to represent Canvas course sections.
 * See <a href="https://canvas.instructure.com/doc/api/sections.html#Section">Canvas Section</a> documentation.
 */
public class Section implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private String sisSectionId;
    private String integrationId;
    private String sisImportId;
    private Integer courseId;
    private String sisCourseId;
    private Date startAt;
    private Date endAt;
    private Integer nonxlistCourseId;
    private Integer totalStudents;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Integer getNonxlistCourseId() {
        return nonxlistCourseId;
    }

    public void setNonxlistCourseId(Integer nonxlistCourseId) {
        this.nonxlistCourseId = nonxlistCourseId;
    }

    public Integer getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Integer totalStudents) {
        this.totalStudents = totalStudents;
    }
}
