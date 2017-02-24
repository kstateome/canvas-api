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
    private String currentGrade;
    private String finalGrade;

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
}