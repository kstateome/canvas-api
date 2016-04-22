package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.quizzes.QuizSubmission;
import edu.ksu.canvas.exception.MessageUndeliverableException;
import edu.ksu.canvas.exception.OauthTokenRequiredException;

import java.io.IOException;
import java.util.Optional;

public interface QuizSubmissionWriter extends CanvasWriter {
    Optional<QuizSubmission> startQuizSubmission(String wid, String courseId, String quizId, String accessCode)
            throws MessageUndeliverableException, IOException, OauthTokenRequiredException;
    Optional<QuizSubmission> completeQuizSubmission(QuizSubmission submission, String wid, String courseId, String accessCode)
            throws MessageUndeliverableException, IOException, OauthTokenRequiredException;
}
