package edu.ksu.canvas.model.assignment;

import edu.ksu.canvas.model.BaseCanvasModel;

import java.io.Serializable;

public class RubricRating extends BaseCanvasModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String criterionId;
    private Double points;
    private String description;
    private String longDescription;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCriterionId() {
        return criterionId;
    }

    public void setCriterionId(String criterionId) {
        this.criterionId = criterionId;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
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
}
