package edu.ksu.canvas.model.assignment;

import edu.ksu.canvas.model.BaseCanvasModel;

import java.io.Serializable;

public class RubricAssociation extends BaseCanvasModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long rubricId;
    private Long associationId;
    private String associationType;
    private Boolean useForGrading;
    private String purpose;
    private Boolean hideScoreTotal;
    private Boolean hidePoints;
    private Boolean hideOutcomeResults;
    // No clue what summary data is. The only place it is mentioned in docs seems to indicate it is a String
    // although I suspect this is not correct so I am leaving it out for now.
    //private String summaryData;

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

    public Long getAssociationId() {
        return associationId;
    }

    public void setAssociationId(Long associationId) {
        this.associationId = associationId;
    }

    public String getAssociationType() {
        return associationType;
    }

    public void setAssociationType(String associationType) {
        this.associationType = associationType;
    }

    public Boolean getUseForGrading() {
        return useForGrading;
    }

    public void setUseForGrading(Boolean useForGrading) {
        this.useForGrading = useForGrading;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
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

    public Boolean getHideOutcomeResults() {
        return hideOutcomeResults;
    }

    public void setHideOutcomeResults(Boolean hideOutcomeResults) {
        this.hideOutcomeResults = hideOutcomeResults;
    }
}
