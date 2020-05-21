package edu.ksu.canvas.model.assignment;

import java.io.Serializable;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;
import edu.ksu.canvas.model.BaseCanvasModel;

/**
 * Class to represent Canvas quiz submission.
 * * See <a href="https://canvas.instructure.com/doc/api/quiz_submissions.html#QuizSubmission">Canvas Quiz Submission</a> documentation.
 */

@CanvasObject(postKey = "quizSubmission")
public class QuizSubmission extends BaseCanvasModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long quizId;
    private Long userId;
    private Long submissionId;
    private String startedAt;
    private String finishedAt;
    private String endAt;
    private Long attempt;
    private Long extraAttempts;
    private Long extraTime;
    private Boolean manuallyUnlocked;
    private Long timeSpent;
    private Double score;
    private Double scoreBeforeRegrade;
    private Double keptScore;
    private Double fudgePoints;
    private Boolean hasSeenResults;
    private String workflowState;
    private Boolean overdueAndNeedsSubmission;
    private String validationToken;

    public Long getId() {
        return id;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Long submissionId) {
        this.submissionId = submissionId;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    public String getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(String finishedAt) {
        this.finishedAt = finishedAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    @CanvasField(postKey = "attempt", array = false)
    public Long getAttempt() {
        return attempt;
    }

    public void setAttempt(Long attempt) {
        this.attempt = attempt;
    }

    public Long getExtraAttempts() {
        return extraAttempts;
    }

    public void setExtraAttempts(Long extraAttempts) {
        this.extraAttempts = extraAttempts;
    }

    public Long getExtraTime() {
        return extraTime;
    }

    public void setExtraTime(Long extraTime) {
        this.extraTime = extraTime;
    }

    public Boolean getManuallyUnlocked() {
        return manuallyUnlocked;
    }

    public void setManuallyUnlocked(Boolean manuallyUnlocked) {
        this.manuallyUnlocked = manuallyUnlocked;
    }

    public Long getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Long timeSpent) {
        this.timeSpent = timeSpent;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getScoreBeforeRegrade() {
        return scoreBeforeRegrade;
    }

    public void setScoreBeforeRegrade(Double scoreBeforeRegrade) {
        this.scoreBeforeRegrade = scoreBeforeRegrade;
    }

    public Double getKeptScore() {
        return keptScore;
    }

    public void setKeptScore(Double keptScore) {
        this.keptScore = keptScore;
    }

    @CanvasField(postKey = "fudge_points", array = false)
    public Double getFudgePoints() {
        return fudgePoints;
    }

    public void setFudgePoints(Double fudgePoints) {
        this.fudgePoints = fudgePoints;
    }

    public Boolean getHasSeenResults() {
        return hasSeenResults;
    }

    public void setHasSeenResults(Boolean hasSeenResults) {
        this.hasSeenResults = hasSeenResults;
    }

    public String getWorkflowState() {
        return workflowState;
    }

    public void setWorkflowState(String workflowState) {
        this.workflowState = workflowState;
    }

    public Boolean getOverdueAndNeedsSubmission() {
        return overdueAndNeedsSubmission;
    }

    public void setOverdueAndNeedsSubmission(Boolean overdueAndNeedsSubmission) {
        this.overdueAndNeedsSubmission = overdueAndNeedsSubmission;
    }

    public String getValidationToken() {
        return validationToken;
    }

    public void setValidationToken(String validationToken) {
        this.validationToken = validationToken;
    }

}
