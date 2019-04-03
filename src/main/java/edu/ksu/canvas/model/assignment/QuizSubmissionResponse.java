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
public class QuizSubmissionResponse {
    private final List<QuizSubmission> quizSubmissions;
    private final Map<Integer, User> users;
    private final Map<Integer, Quiz> quizzes;

    public QuizSubmissionResponse(final List<QuizSubmission> quizSubmissions, final List<User> users, final List<Quiz> quizzes) {
        this.quizSubmissions = ImmutableList.copyOf(quizSubmissions);
        this.users = users.stream().distinct().collect(Collectors.toMap(User::getId, Function.identity()));
        this.quizzes = quizzes.stream().distinct().collect(Collectors.toMap(Quiz::getId, Function.identity()));
    }

    /**
     * Get the requested list of queried QuizSubmission objects
     * @return The requested quiz submissions
     */
    public List<QuizSubmission> getQuizSubmissions() {
        return quizSubmissions;
    }

    /**
     * Get a userID to User map of all users associated with the queried quiz submissions
     * if users were requested via GetQuizSubmissionsOptions
     * @return User map
     */
    public Map<Integer, User> getUsers() {
        return users;
    }

    /**
     * Find a user associated with the queried quiz submissions (if users were requested via GetQuizSubmissionsOptions)
     * @param userId Canvas user ID
     * @return Canvas user record if found
     */
    public Optional<User> getUser(final int userId) {
        return Optional.ofNullable(users.get(userId));
    }

    /**
     * Get a quiz ID to Quiz map of quizzes returned by the quiz submission query.
     *
     * Only populated if quizzes were requested in the original query via GetQuizSubmissionsOptions.
     * While this comes from Canvas as a list, I THINK there should only ever be one element given that quiz submissions
     * are queried by specific quiz ID.
     * @return Quiz associated with these submissions
     */
    public Map<Integer, Quiz> getQuizzes() {
        return quizzes;
    }

    /**
     * Find a quiz associated with the queried quiz submissions (if quizzes were requested via GetQuizSubmissionsOptions)
     * @param quizId Canvas ID of the quiz
     * @return The requested Quiz object if found
     */
    public Optional<Quiz> getQuiz(final int quizId) {
        return Optional.ofNullable(quizzes.get(quizId));
    }
}
