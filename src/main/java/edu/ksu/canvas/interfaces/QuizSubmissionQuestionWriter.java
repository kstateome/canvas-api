package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.exception.OauthTokenRequiredException;
import edu.ksu.canvas.model.quizzes.QuizSubmission;
import edu.ksu.canvas.model.quizzes.QuizSubmissionQuestion;
import edu.ksu.canvas.exception.MessageUndeliverableException;

import java.io.IOException;
import java.util.List;

public interface QuizSubmissionQuestionWriter {
    /**
     * Answers questions on behalf of a user.
     * Uses Masquerading, thus requires Admin access
     *
     * @param submission      QuizSubmission we are answering questions for
     * @param wid             WID of the user to masquerade as
     * @param answerArrayJson JsonArray of question objects. Answers may be in several formats: https://canvas.instructure.com/doc/api/quiz_submission_questions.html#Question+Answer+Formats-appendix
     * @return List of Quiz Submission Questions that Canvas returns from the request
     * @throws MessageUndeliverableException
     * @throws IOException
     * @throws OauthTokenRequiredException
     */
    List<QuizSubmissionQuestion> answerQuestions(QuizSubmission submission, String wid, String answerArrayJson, String accessCode)
            throws MessageUndeliverableException, IOException, OauthTokenRequiredException;
}
