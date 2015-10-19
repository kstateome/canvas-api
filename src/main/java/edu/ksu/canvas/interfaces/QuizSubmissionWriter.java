package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.quizzes.QuizSubmission;
import edu.ksu.lti.error.MessageUndeliverableException;
import edu.ksu.lti.error.OauthTokenRequiredException;

import java.io.IOException;
import java.util.Optional;

public interface QuizSubmissionWriter {
    Optional<QuizSubmission> startQuizSubmission(String wid, String courseId, String quizId, String accessCode)
            throws MessageUndeliverableException, IOException, OauthTokenRequiredException;
    Optional<QuizSubmission> completeQuizSubmission(QuizSubmission submission, String wid, String courseId, String accessCode)
            throws MessageUndeliverableException, IOException, OauthTokenRequiredException;
}
