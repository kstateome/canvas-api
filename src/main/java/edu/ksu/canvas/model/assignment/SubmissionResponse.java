package edu.ksu.canvas.model.assignment;

import com.google.common.collect.ImmutableList;
import edu.ksu.canvas.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Object to capture the response of Quiz Submission API calls.
 *
 * When you ask for users and/or quiz information to be included in the response (via GetQuizSubmissionsOptions), Canvas
 * returns them as top level lists along with the QuizSubmission objects. This is unlike other API calls with optional
 * includes which typically return the requested includes as nested objects instead of the top level list.
 *
 * The users are stored in a map by user ID to make them easy to look up if you need the user object while doing
 * something with the submissions.
 */
public class SubmissionResponse {
    private final List<Submission> submissions;
    private final Map<Integer, User> users;
    private final Map<Integer, Assignment> assignments;

    public SubmissionResponse(final List<Submission> submissions, final List<User> users, final List<Assignment> assignments) {
        this.submissions = ImmutableList.copyOf(submissions);
        this.users = users.stream().distinct().collect(Collectors.toMap(User::getId, Function.identity()));
        this.assignments = assignments.stream().distinct().collect(Collectors.toMap(Assignment::getId, Function.identity()));
    }

    /**
     * Get the requested list of queried Submission objects
     * @return The requested submissions
     */
    public List<Submission> getSubmissions() {
        return submissions;
    }

    /**
     * Get a userID to User map of all users associated with the queried submissions
     * if users were requested via GetSubmissionsOptions
     * @return User map
     */
    public Map<Integer, User> getUsers() {
        return users;
    }

    /**
     * Find a user associated with the queried submissions (if users were requested via GetSubmissionsOptions)
     * @param userId Canvas user ID
     * @return Canvas user record if found
     */
    public Optional<User> getUser(final int userId) {
        return Optional.ofNullable(users.get(userId));
    }

    /**
     * Get an assignment ID to map of assignments returned by the submission query.
     *
     * Only populated if assignments were requested in the original query via GetSubmissionsOptions.
     * While this comes from Canvas as a list, I THINK there should only ever be one element given that submissions
     * are queried by specific assignment ID.
     * @return Quiz associated with these submissions
     */
    public Map<Integer, Assignment> getAssignments() {
        return assignments;
    }

    /**
     * Find a quiz associated with the queried quiz submissions (if assignments were requested via GetQuizSubmissionsOptions)
     * @param assignmentId Canvas ID of the quiz
     * @return The requested Quiz object if found
     */
    public Optional<Assignment> getAssignment(final int assignmentId) {
        return Optional.ofNullable(assignments.get(assignmentId));
    }
}
