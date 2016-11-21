package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Class to represent Canvas enrollment termss.
 * See <a href="https://canvas.instructure.com/doc/api/enrollment_terms.html">Canvas Enrollment Terms</a> documentation.
 */
@CanvasObject(postKey = "enrollment_term")
public class EnrollmentTerm extends BaseCanvasModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String sisTermId;
    private String name;
    private Date startAt;
    private Date endAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSisTermId() {
        return sisTermId;
    }

    public void setSisTermId(String sisTermId) {
        this.sisTermId = sisTermId;
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

    @Override
    public String toString() {
        return "EnrollmentTerm{" +
                "id=" + id +
                ", sisTermId='" + sisTermId + '\'' +
                ", name='" + name + '\'' +
                ", startAt=" + startAt +
                ", endAt=" + endAt +
                '}';
    }

}
