package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.List;

import edu.ksu.canvas.model.assignment.QuizQuestion;
import edu.ksu.canvas.requestOptions.GetQuizQuestionsOptions;

public interface QuizQuestionReader extends CanvasReader<QuizQuestion, QuizQuestionReader> {
    /**
     * Retrieve a list of quiz questions from Canvas by its course and quiz Canvas ID numbers
     * @param options The API options associated with this request
     * @return List of quizzes questions in the course with the course ID
     * @throws IOException When there is an error communicating with Canvas
     */
    List<QuizQuestion> getQuizQuestions(GetQuizQuestionsOptions options) throws IOException;

}
