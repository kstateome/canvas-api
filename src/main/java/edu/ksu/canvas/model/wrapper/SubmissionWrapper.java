package edu.ksu.canvas.model.wrapper;

import edu.ksu.canvas.model.User;
import edu.ksu.canvas.model.assignment.Assignment;
import edu.ksu.canvas.model.assignment.Submission;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper class made necessary because canvas returns an object that
 * contains a list of QuizSubmission objects, with supplemental objects
 */
public class SubmissionWrapper {
    private List<Submission> submissions = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Assignment> assignments = new ArrayList<>();

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(final List<Submission> submissions) {
        this.submissions = submissions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(final List<User> users) {
        this.users = users;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }
}
