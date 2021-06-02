package edu.ksu.canvas.model.assignment;

import edu.ksu.canvas.model.BaseCanvasModel;

import java.io.Serializable;

/**
 * Class to represent the rubric settings values that are returned as part of assignments that have
 * rubrics associated with them. The class isn't really documented and seems to be mostly a stripped
 * down version of the Rubric class, plus the "hide points" boolean.
 */
public class RubricSettings extends BaseCanvasModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private Double pointsPossible;
    private Boolean freeFormCriterionComments;
    private Boolean hideScoreTotal;
    private Boolean hidePoints;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPointsPossible() {
        return pointsPossible;
    }

    public void setPointsPossible(Double pointsPossible) {
        this.pointsPossible = pointsPossible;
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

    public Boolean getHidePoints() {
        return hidePoints;
    }

    public void setHidePoints(Boolean hidePoints) {
        this.hidePoints = hidePoints;
    }
}
