package edu.ksu.canvas.model.assignment;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;
import edu.ksu.canvas.model.BaseCanvasModel;

/**
 * Class to represent Canvas quiz submission.
 * * See <a href="https://canvas.instructure.com/doc/api/quiz_submissions.html#QuizSubmission">Canvas Quiz Submission</a> documentation.
 */

@CanvasObject(postKey = "quizSubmission")
public class QuizSubmission extends BaseCanvasModel {
    private Integer id;
    private Integer quiz_id;
    private Integer user_id;
    private Integer submission_id;
    private String started_at;
    private String finished_at;
    private String end_at;
    private Integer attempt;
    private Integer extra_attempts;
    private Integer extra_time;
    private Boolean manually_unlocked;
    private Integer time_spent;
    private Double score;
    private Double score_before_regrade;
    private Double kept_score;
    private Double fudge_points;
    private Boolean has_seen_results;
    private String workflow_state;
    private Boolean overdue_and_needs_submission;
    private String validation_token;

    public Integer getId() {
        return id;
    }

    public Integer getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(Integer quiz_id) {
        this.quiz_id = quiz_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getSubmission_id() {
        return submission_id;
    }

    public void setSubmission_id(Integer submission_id) {
        this.submission_id = submission_id;
    }

    public String getStarted_at() {
        return started_at;
    }

    public void setStarted_at(String started_at) {
        this.started_at = started_at;
    }

    public String getFinished_at() {
        return finished_at;
    }

    public void setFinished_at(String finished_at) {
        this.finished_at = finished_at;
    }

    public String getEnd_at() {
        return end_at;
    }

    public void setEnd_at(String end_at) {
        this.end_at = end_at;
    }

    @CanvasField(postKey = "attempt", array = false)
    public Integer getAttempt() {
        return attempt;
    }

    public void setAttempt(Integer attempt) {
        this.attempt = attempt;
    }

    public Integer getExtra_attempts() {
        return extra_attempts;
    }

    public void setExtra_attempts(Integer extra_attempts) {
        this.extra_attempts = extra_attempts;
    }

    public Integer getExtra_time() {
        return extra_time;
    }

    public void setExtra_time(Integer extra_time) {
        this.extra_time = extra_time;
    }

    public Boolean getManually_unlocked() {
        return manually_unlocked;
    }

    public void setManually_unlocked(Boolean manually_unlocked) {
        this.manually_unlocked = manually_unlocked;
    }

    public Integer getTime_spent() {
        return time_spent;
    }

    public void setTime_spent(Integer time_spent) {
        this.time_spent = time_spent;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getScore_before_regrade() {
        return score_before_regrade;
    }

    public void setScore_before_regrade(Double score_before_regrade) {
        this.score_before_regrade = score_before_regrade;
    }

    public Double getKept_score() {
        return kept_score;
    }

    public void setKept_score(Double kept_score) {
        this.kept_score = kept_score;
    }

    @CanvasField(postKey = "fudge_points", array = false)
    public Double getFudge_points() {
        return fudge_points;
    }

    public void setFudge_points(Double fudge_points) {
        this.fudge_points = fudge_points;
    }

    public Boolean getHas_seen_results() {
        return has_seen_results;
    }

    public void setHas_seen_results(Boolean has_seen_results) {
        this.has_seen_results = has_seen_results;
    }

    public String getWorkflow_state() {
        return workflow_state;
    }

    public void setWorkflow_state(String workflow_state) {
        this.workflow_state = workflow_state;
    }

    public Boolean getOverdue_and_needs_submission() {
        return overdue_and_needs_submission;
    }

    public void setOverdue_and_needs_submission(Boolean overdue_and_needs_submission) {
        this.overdue_and_needs_submission = overdue_and_needs_submission;
    }

    public String getValidation_token() {
        return validation_token;
    }

    public void setValidation_token(String validation_token) {
        this.validation_token = validation_token;
    }

}
