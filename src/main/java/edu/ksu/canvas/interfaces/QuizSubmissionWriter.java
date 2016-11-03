package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.exception.MessageUndeliverableException;
import edu.ksu.canvas.exception.OauthTokenRequiredException;
import edu.ksu.canvas.model.assignment.QuizSubmission;

import java.io.IOException;
import java.util.Optional;

public interface QuizSubmissionWriter extends CanvasWriter<QuizSubmission, QuizSubmissionWriter> {
    Optional<QuizSubmission> startQuizSubmission(String wid, String courseId, String quizId, String accessCode)
            throws MessageUndeliverableException, IOException, OauthTokenRequiredException;
    Optional<QuizSubmission> completeQuizSubmission(QuizSubmission submission, String wid, String courseId, String accessCode)
            throws MessageUndeliverableException, IOException, OauthTokenRequiredException;
}
