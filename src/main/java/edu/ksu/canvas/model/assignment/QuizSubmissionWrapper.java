package edu.ksu.canvas.model.assignment;

import java.util.List;

/**
 * Wrapper class made necessary because canvas returns an object that
 * contains a list of QuizSubmission Objects, with only one item in the list.
 * Rather than either just a list, or just the object....
 */
public class QuizSubmissionWrapper {
    private List<QuizSubmission> quiz_submissions;

    public List<QuizSubmission> getQuiz_submissions() {
        return quiz_submissions;
    }

    public void setQuiz_submissions(List<QuizSubmission> quiz_submissions) {
        this.quiz_submissions = quiz_submissions;
    }
}
