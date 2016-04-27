package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.quizzes.QuizQuestion;
import edu.ksu.canvas.exception.OauthTokenRequiredException;

import java.io.IOException;
import java.util.List;

public interface QuizQuestionReader extends CanvasReader {
    /**
     * Retrieve a list of quiz questions from Canvas by its course and quiz Canvas ID numbers
     * @param courseId   The Canvas ID of the course
     * @param quizId     The Canvas ID of the quiz
     * @return List of quizzes questions in the course with the course ID
     * @throws OauthTokenRequiredException
     * @throws IOException
     */
    List<QuizQuestion> getQuizQuestions(String courseId, String quizId) throws OauthTokenRequiredException, IOException;

}
