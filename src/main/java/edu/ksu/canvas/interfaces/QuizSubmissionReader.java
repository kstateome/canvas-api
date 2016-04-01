package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.quizzes.QuizSubmission;
import edu.ksu.lti.error.OauthTokenRequiredException;

import java.io.IOException;
import java.util.List;

public interface QuizSubmissionReader {
    /**
     * Retrieve a list of quiz submissions from Canvas by its course and quiz Canvas ID numbers
     * @param oauthToken OAuth token to use when executing API calls
     * @param courseId   The Canvas ID of the course
     * @param quizId     The Canvas ID of the quiz
     * @return List of quiz submissions in the course with the course ID
     * @throws OauthTokenRequiredException
     * @throws IOException
     */
    List<QuizSubmission> getQuizSubmissions(String oauthToken, String courseId, String quizId) throws OauthTokenRequiredException, IOException;

}
