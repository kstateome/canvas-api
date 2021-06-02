package edu.ksu.canvas.model.assignment;

import com.google.gson.annotations.SerializedName;
import edu.ksu.canvas.annotation.CanvasObject;
import edu.ksu.canvas.model.BaseCanvasModel;

import java.io.Serializable;
import java.util.List;

@CanvasObject(postKey = "rubric")
public class Rubric extends BaseCanvasModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long contextId;
    private String contextType;
    private Double pointsPossible;
    private String title;
    private Boolean reusable;
    @SerializedName("public") // public is a reserved word in Java
    private Boolean isPublic;
    private Boolean readOnly;
    private Boolean freeFormCriterionComments;
    private Boolean hideScoreTotal;
    @SerializedName("data")
    private List<RubricCriterion> criteria;
    private List<RubricAssessment> assessments;
    private List<RubricAssociation> associations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContextId() {
        return contextId;
    }

    public void setContextId(Long contextId) {
        this.contextId = contextId;
    }

    public String getContextType() {
        return contextType;
    }

    public void setContextType(String contextType) {
        this.contextType = contextType;
    }

    public Double getPointsPossible() {
        return pointsPossible;
    }

    public void setPointsPossible(Double pointsPossible) {
        this.pointsPossible = pointsPossible;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getReusable() {
        return reusable;
    }

    public void setReusable(Boolean reusable) {
        this.reusable = reusable;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }

    public Boolean getFreeFormCriterionComments() {
        return freeFormCriterionComments;
    }

    public void setFreeFormCriterionComments(Boolean freeFormCriterionComments) {
        this.freeFormCriterionComments = freeFormCriterionComments;
    }

    public Boolean getHideScoreTotal() {
        return hideScoreTotal;
    }

    public void setHideScoreTotal(Boolean hideScoreTotal) {
        this.hideScoreTotal = hideScoreTotal;
    }

    public List<RubricCriterion> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<RubricCriterion> criteria) {
        this.criteria = criteria;
    }

    public List<RubricAssessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<RubricAssessment> assessments) {
        this.assessments = assessments;
    }

    public List<RubricAssociation> getAssociations() {
        return associations;
    }

    public void setAssociations(List<RubricAssociation> associations) {
        this.associations = associations;
    }
}
