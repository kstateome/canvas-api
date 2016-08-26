package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.Assignment;
import edu.ksu.canvas.requestOptions.GetSingleAssignmentOptions;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Methods to read information from and about Assignments
 */
public interface AssignmentReader extends CanvasReader<Assignment, AssignmentReader> {
    /**
     * Retrieve a specific assignment from Canvas by its Canvas ID number
     * @param options An object encapsulating required and optional parameters to this API call
     * @return The assignment returned by Canvas or an empty Optional
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<Assignment> getSingleAssignment(GetSingleAssignmentOptions options) throws IOException;

    /**
     * Retrieve a specific list of assignments from Canvas by its Canvas ID number
     * @param courseId The Canvas ID of the course
     * @return List of assignments in the course with the course ID
     * @throws IOException When there is an error communicating with Canvas
     */
    List<Assignment> listCourseAssignments(String courseId) throws IOException;
}
