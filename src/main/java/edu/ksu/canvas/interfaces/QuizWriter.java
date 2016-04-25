package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.quizzes.Quiz;
import edu.ksu.canvas.exception.MessageUndeliverableException;
import edu.ksu.canvas.exception.OauthTokenRequiredException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

public interface QuizWriter extends CanvasBase {
    /**
     *
     * @param quiz
     * @param courseId
     * @return UpdatedQuiz
     * @throws MessageUndeliverableException
     * @throws UnsupportedEncodingException
     * @throws OauthTokenRequiredException
     */
    Optional<Quiz> updateQuiz(Quiz quiz, String courseId)
            throws MessageUndeliverableException, IOException, OauthTokenRequiredException;
}
