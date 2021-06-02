package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.assignment.Rubric;
import edu.ksu.canvas.requestOptions.GetRubricOptions;

import java.io.IOException;
import java.util.Optional;

public interface RubricReader extends CanvasReader<Rubric, RubricReader> {

    /**
     * Get a single rubric by ID from an account.
     * @param options Request parameters
     * @return The requested rubric object associated with an account
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<Rubric> getRubricInAccount(GetRubricOptions options) throws IOException;

    /**
     * Get a single rubric by ID from a course.
     * @param options Request parameters
     * @return The requested rubric object associated with a course
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<Rubric> getRubricInCourse(GetRubricOptions options) throws IOException;
}
