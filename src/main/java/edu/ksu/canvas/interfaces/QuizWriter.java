package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.quizzes.Quiz;
import edu.ksu.lti.error.MessageUndeliverableException;
import edu.ksu.lti.error.OauthTokenRequiredException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

public interface QuizWriter {
    /**
     *
     * @param oauthToken
     * @param quiz
     * @param courseId
     * @return UpdatedQuiz
     * @throws MessageUndeliverableException
     * @throws UnsupportedEncodingException
     * @throws OauthTokenRequiredException
     */
    Optional<Quiz> updateQuiz(String oauthToken, Quiz quiz, String courseId)
            throws MessageUndeliverableException, IOException, OauthTokenRequiredException;
}
