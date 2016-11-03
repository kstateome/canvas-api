package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.List;

import edu.ksu.canvas.model.assignment.QuizQuestion;

public interface QuizQuestionReader extends CanvasReader<QuizQuestion, QuizQuestionReader> {
    /**
     * Retrieve a list of quiz questions from Canvas by its course and quiz Canvas ID numbers
     * @param courseId   The Canvas ID of the course
     * @param quizId     The Canvas ID of the quiz
     * @return List of quizzes questions in the course with the course ID
     * @throws IOException When there is an error communicating with Canvas
     */
    List<QuizQuestion> getQuizQuestions(String courseId, String quizId) throws IOException;

}
