package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.exception.OauthTokenRequiredException;
import edu.ksu.canvas.model.quizzes.QuizSubmission;
import edu.ksu.canvas.model.quizzes.QuizSubmissionQuestion;
import edu.ksu.lti.error.MessageUndeliverableException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface QuizSubmissionQuestionWriter {
    List<QuizSubmissionQuestion> answerQuestions(QuizSubmission submission, String wid, String answerArrayJson, String accessCode)
            throws MessageUndeliverableException, IOException, OauthTokenRequiredException;
}
