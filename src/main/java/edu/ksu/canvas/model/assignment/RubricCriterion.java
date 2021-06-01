package edu.ksu.canvas.model.assignment;

import edu.ksu.canvas.model.BaseCanvasModel;

import java.io.Serializable;
import java.util.List;

public class RubricCriterion extends BaseCanvasModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String description;
    private String longDescription;
    private Double points;
    private Boolean criterionUseRange;
    private Boolean ignoreForScoring;
    private Double masteryPoints;
    private Long learningOutcomeId;
    private List<RubricRating> ratings;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public Boolean getCriterionUseRange() {
        return criterionUseRange;
    }

    public void setCriterionUseRange(Boolean criterionUseRange) {
        this.criterionUseRange = criterionUseRange;
    }

    public Boolean getIgnoreForScoring() {
        return ignoreForScoring;
    }

    public void setIgnoreForScoring(Boolean ignoreForScoring) {
        this.ignoreForScoring = ignoreForScoring;
    }

    public Double getMasteryPoints() {
        return masteryPoints;
    }

    public void setMasteryPoints(Double masteryPoints) {
        this.masteryPoints = masteryPoints;
    }

    public Long getLearningOutcomeId() {
        return learningOutcomeId;
    }

    public void setLearningOutcomeId(Long learningOutcomeId) {
        this.learningOutcomeId = learningOutcomeId;
    }

    public List<RubricRating> getRatings() {
        return ratings;
    }

    public void setRatings(List<RubricRating> ratings) {
        this.ratings = ratings;
    }
}
