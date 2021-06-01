package edu.ksu.canvas.model.assignment;

import edu.ksu.canvas.model.BaseCanvasModel;

import java.io.Serializable;

public class RubricAssessment extends BaseCanvasModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long rubricId;
    private Long rubricAssociationId;
    private Double score;
    private Long artifactId;
    private String artifactType;
    private Integer artifactAttempt;
    private String assessmentType;
    private Long assessorId;
    private String comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRubricId() {
        return rubricId;
    }

    public void setRubricId(Long rubricId) {
        this.rubricId = rubricId;
    }

    public Long getRubricAssociationId() {
        return rubricAssociationId;
    }

    public void setRubricAssociationId(Long rubricAssociationId) {
        this.rubricAssociationId = rubricAssociationId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Long getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(Long artifactId) {
        this.artifactId = artifactId;
    }

    public String getArtifactType() {
        return artifactType;
    }

    public void setArtifactType(String artifactType) {
        this.artifactType = artifactType;
    }

    public Integer getArtifactAttempt() {
        return artifactAttempt;
    }

    public void setArtifactAttempt(Integer artifactAttempt) {
        this.artifactAttempt = artifactAttempt;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public Long getAssessorId() {
        return assessorId;
    }

    public void setAssessorId(Long assessorId) {
        this.assessorId = assessorId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
