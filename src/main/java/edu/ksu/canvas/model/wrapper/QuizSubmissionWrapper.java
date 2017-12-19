package edu.ksu.canvas.model.wrapper;

import java.util.List;

import edu.ksu.canvas.model.assignment.QuizSubmission;

/**
 * Wrapper class made necessary because canvas returns an object that
 * contains a list of QuizSubmission Objects, with only one item in the list.
 * Rather than either just a list, or just the object....
 */
public class QuizSubmissionWrapper {
    private List<QuizSubmission> quizSubmissions;

    public List<QuizSubmission> getQuizSubmissions() {
        return quizSubmissions;
    }

    public void setQuizSubmissions(List<QuizSubmission> quizSubmissions) {
        this.quizSubmissions = quizSubmissions;
    }
}
