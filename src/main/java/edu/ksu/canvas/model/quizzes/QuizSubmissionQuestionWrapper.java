package edu.ksu.canvas.model.quizzes;

import java.util.List;

/**
 * Wrapper class made necessary because canvas returns an object that
 * contains a list of QuizSubmissionQuestion Objects, with only one item in the list.
 * Rather than either just a list, or just the object....
 */
public class QuizSubmissionQuestionWrapper {

    private List<QuizSubmissionQuestion> quiz_submission_questions;

    public List<QuizSubmissionQuestion> getQuiz_submission_questions() {
        return quiz_submission_questions;
    }

    public void setQuiz_submission_questions(List<QuizSubmissionQuestion> quiz_submission_questions) {
        this.quiz_submission_questions = quiz_submission_questions;
    }
}