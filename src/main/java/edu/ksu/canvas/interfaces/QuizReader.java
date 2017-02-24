package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import edu.ksu.canvas.model.assignment.Quiz;

public interface QuizReader extends CanvasReader<Quiz, QuizReader>{
    /**
     * Retrieve a specific quiz from Canvas by its course and quiz Canvas ID numbers
     * @param courseId   The Canvas ID of the course
     * @param quizId     The Canvas ID of the quiz
     * @return The assignment returned by Canvas or an empty Optional
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<Quiz> getSingleQuiz(String courseId, String quizId) throws IOException;

    /**
     * Retrieve a list of quizzes from Canvas by its course Canvas ID number
     * @param courseId   The Canvas ID of the course
     * @return List of quizzes in the course with the course ID
     * @throws IOException When there is an error communicating with Canvas
     */
    List<Quiz> getQuizzesInCourse(String courseId) throws IOException;
}