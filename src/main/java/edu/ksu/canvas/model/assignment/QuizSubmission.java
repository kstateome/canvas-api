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

    private Integer id;
    private Integer quizId;
    private Integer userId;
    private Integer submissionId;
    private String startedAt;
    private String finishedAt;
    private String endAt;
    private Integer attempt;
    private Integer extraAttempts;
    private Integer extraTime;
    private Boolean manuallyUnlocked;
    private Integer timeSpent;
    private Double score;
    private Double scoreBeforeRegrade;
    private Double keptScore;
    private Double fudgePoints;
    private Boolean hasSeenResults;
    private String workflowState;
    private Boolean overdueAndNeedsSubmission;
    private String validationToken;

    public Integer getId() {
        return id;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Integer submissionId) {
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
    public Integer getAttempt() {
        return attempt;
    }

    public void setAttempt(Integer attempt) {
        this.attempt = attempt;
    }

    public Integer getExtraAttempts() {
        return extraAttempts;
    }

    public void setExtraAttempts(Integer extraAttempts) {
        this.extraAttempts = extraAttempts;
    }

    public Integer getExtraTime() {
        return extraTime;
    }

    public void setExtraTime(Integer extraTime) {
        this.extraTime = extraTime;
    }

    public Boolean getManuallyUnlocked() {
        return manuallyUnlocked;
    }

    public void setManuallyUnlocked(Boolean manuallyUnlocked) {
        this.manuallyUnlocked = manuallyUnlocked;
    }

    public Integer getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Integer timeSpent) {
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
