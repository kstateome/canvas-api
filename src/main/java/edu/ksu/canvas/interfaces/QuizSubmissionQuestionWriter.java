package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.quizzes.QuizSubmission;
import edu.ksu.canvas.model.quizzes.QuizSubmissionQuestion;

import java.io.IOException;
import java.util.List;

public interface QuizSubmissionQuestionWriter extends CanvasWriter<QuizSubmissionQuestion, QuizSubmissionQuestionWriter> {
    /**
     * Answers questions on behalf of a user.
     * Uses Masquerading, thus requires Admin access
     *
     * @param submission      QuizSubmission we are answering questions for
     * @param wid             WID of the user to masquerade as
     * @param answerArrayJson JsonArray of question objects. Answers may be in several formats: https://canvas.instructure.com/doc/api/quiz_submission_questions.html#Question+Answer+Formats-appendix
     * @param accessCode Quiz access code
     * @return List of Quiz Submission Questions that Canvas returns from the request
     * @throws IOException When there is an error communicating with Canvas
     */
    List<QuizSubmissionQuestion> answerQuestions(QuizSubmission submission, String wid, String answerArrayJson, String accessCode)
            throws IOException;
}
