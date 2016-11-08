package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.List;

import javax.validation.constraints.NotNull;

import edu.ksu.canvas.model.assignment.QuizSubmissionQuestion;
import edu.ksu.canvas.requestOptions.AnswerQuizQuestionOptions;

public interface QuizSubmissionQuestionWriter extends CanvasWriter<QuizSubmissionQuestion, QuizSubmissionQuestionWriter> {
    /**
     * Answers questions on behalf of a user.
     * @param options API options object for this call
     * @param answerArrayJson JsonArray of question objects. Answers may be in several formats: https://canvas.instructure.com/doc/api/quiz_submission_questions.html#Question+Answer+Formats-appendix
     * @return List of Quiz Submission Questions that Canvas returns from the request
     * @throws IOException When there is an error communicating with Canvas
     */
    List<QuizSubmissionQuestion> answerQuestions(@NotNull AnswerQuizQuestionOptions options, @NotNull String answerArrayJson)
            throws IOException;
}
