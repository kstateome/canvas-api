package edu.ksu.canvas.model.assignment;

import com.google.common.collect.ImmutableList;
import edu.ksu.canvas.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class QuizSubmissionResponse {
    private final List<QuizSubmission> quizSubmissions;
    private final Map<Integer, User> users;

    public QuizSubmissionResponse(final List<QuizSubmission> quizSubmissions, final List<User> users) {
        this.quizSubmissions = ImmutableList.copyOf(quizSubmissions);
        this.users = users.stream().distinct().collect(Collectors.toMap(User::getId, Function.identity()));
    }

    public List<QuizSubmission> getQuizSubmissions() {
        return quizSubmissions;
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    public Optional<User> getUser(final int id) {
        return Optional.ofNullable(users.get(id));
    }

}
