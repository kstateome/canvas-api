package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.List;

import edu.ksu.canvas.model.assignment.QuizSubmission;
import edu.ksu.canvas.model.assignment.QuizSubmissionResponse;
import edu.ksu.canvas.requestOptions.GetQuizSubmissionsOptions;

public interface QuizSubmissionReader extends CanvasReader<QuizSubmission, QuizSubmissionReader> {
    /**
     * Retrieve a list of quiz submissions from Canvas by its course and quiz Canvas ID numbers
     * @param courseId   The Canvas ID of the course
     * @param quizId     The Canvas ID of the quiz
     * @return List of quiz submissions in the course with the course ID
     * @throws IOException When there is an error communicating with Canvas
     */
    List<QuizSubmission> getQuizSubmissions(String courseId, String quizId) throws IOException;

    /**
     * Retrieve a list of quiz submissions with user and quiz information optionally included
     *
     * @param options Options class containing required and optional parameters for this API call
     * @return List of quiz submissions in the course with the course ID
     * @throws IOException When there is an error communicating with Canvas
     */
    QuizSubmissionResponse getQuizSubmissions(GetQuizSubmissionsOptions options) throws IOException;

}
