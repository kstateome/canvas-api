package edu.ksu.canvas.model.wrapper;

import java.util.ArrayList;
import java.util.List;

import edu.ksu.canvas.model.User;
import edu.ksu.canvas.model.assignment.QuizQuestion;
import edu.ksu.canvas.model.assignment.QuizSubmission;

/**
 * Wrapper class made necessary because canvas returns an object that
 * contains a list of QuizSubmission objects, with supplemental objects
 */
public class QuizSubmissionWrapper {
    private List<QuizSubmission> quizSubmissions = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public List<QuizSubmission> getQuizSubmissions() {
        return quizSubmissions;
    }

    public void setQuizSubmissions(final List<QuizSubmission> quizSubmissions) {
        this.quizSubmissions = quizSubmissions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(final List<User> users) {
        this.users = users;
    }
}
