package edu.ksu.canvas.model;

import java.io.Serializable;

/**
 * Class to represent Canvas grades associated with enrollments.
 * See <a href="https://canvas.instructure.com/doc/api/enrollments.html#Grade">Canvas Enrollments</a> documentation.
 */
public class Grade implements Serializable {
    public static final long serialVersionUID = 1L;

    private String htmlUrl;
    private String currentScore;
    private String finalScore;
    private String unpostedCurrentScore;
    private String unpostedFinalScore;
    private String currentGrade;
    private String finalGrade;
    private String unpostedCurrentGrade;
    private String unpostedFinalGrade;

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(String currentScore) {
        this.currentScore = currentScore;
    }

    public String getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(String finalScore) {
        this.finalScore = finalScore;
    }

    public String getUnpostedCurrentScore() {
        return unpostedCurrentScore;
    }

    public void setUnpostedCurrentScore(String unpostedCurrentScore) {
        this.unpostedCurrentScore = unpostedCurrentScore;
    }

    public String getUnpostedFinalScore() {
        return unpostedFinalScore;
    }

    public void setUnpostedFinalScore(String unpostedFinalScore) {
        this.unpostedFinalScore = unpostedFinalScore;
    }

    public String getCurrentGrade() {
        return currentGrade;
    }

    public void setCurrentGrade(String currentGrade) {
        this.currentGrade = currentGrade;
    }

    public String getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(String finalGrade) {
        this.finalGrade = finalGrade;
    }

    public String getUnpostedCurrentGrade() {
        return unpostedCurrentGrade;
    }

    public void setUnpostedCurrentGrade(String unpostedCurrentGrade) {
        this.unpostedCurrentGrade = unpostedCurrentGrade;
    }

    public String getUnpostedFinalGrade() {
        return unpostedFinalGrade;
    }

    public void setUnpostedFinalGrade(String unpostedFinalGrade) {
        this.unpostedFinalGrade = unpostedFinalGrade;
    }
}