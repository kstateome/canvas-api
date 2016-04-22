package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.quizzes.Quiz;
import edu.ksu.canvas.model.quizzes.QuizQuestion;
import edu.ksu.canvas.model.quizzes.QuizSubmission;
import edu.ksu.canvas.exception.OauthTokenRequiredException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface QuizReader extends CanvasBase {
    /**
     * Retrieve a specific quiz from Canvas by its course and quiz Canvas ID numbers
     * @param courseId   The Canvas ID of the course
     * @param quizId     The Canvas ID of the quiz
     * @return The assignment returned by Canvas or an empty Optional
     * @throws OauthTokenRequiredException
     */
    Optional<Quiz> getSingleQuiz(String courseId, String quizId) throws OauthTokenRequiredException, IOException;

    /**
     * Retrieve a list of quizzes from Canvas by its course Canvas ID number
     * @param courseId   The Canvas ID of the course
     * @return List of quizzes in the course with the course ID
     * @throws OauthTokenRequiredException
     * @throws IOException
     */
    List<Quiz> getQuizzesInCourse(String courseId) throws OauthTokenRequiredException, IOException;
}