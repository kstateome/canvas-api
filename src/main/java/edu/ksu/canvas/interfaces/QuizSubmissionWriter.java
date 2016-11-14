package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.assignment.QuizSubmission;
import edu.ksu.canvas.requestOptions.CompleteQuizSubmissionOptions;
import edu.ksu.canvas.requestOptions.StartQuizSubmissionOptions;

import java.io.IOException;
import java.util.Optional;

public interface QuizSubmissionWriter extends CanvasWriter<QuizSubmission, QuizSubmissionWriter> {

    /**
     * Opens a quiz submission which can be used to answer questions and submit answers
     * @param options API options to be used to open a submission
     * @return The quiz submission object created by Canvas
     * @throws IOException If there is an error talking to Canvas
     */
    Optional<QuizSubmission> startQuizSubmission(StartQuizSubmissionOptions options)throws IOException;

    /**
     * Finish a quiz submission. This turns it in and grades it. After this call, no further modifications are allowed.
     * @param options API options to be used in closing the submission
     * @return The quiz submission object returned by Canvas
     * @throws IOException If there is an error talking to Canvas
     */
    Optional<QuizSubmission> completeQuizSubmission(CompleteQuizSubmissionOptions options) throws IOException;
}
