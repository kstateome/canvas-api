package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import edu.ksu.canvas.model.assignment.Assignment;
import org.apache.hc.core5.http.ParseException;

public interface AssignmentWriter extends CanvasWriter<Assignment, AssignmentWriter> {

    /**
     * Create an assignment in Canvas. The only required field is name.
     * @param courseId ID of the course to create the assignment in
     * @param assignment Assignment object to create. Must have at least a name set
     * @return The created assignment object
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<Assignment> createAssignment(String courseId, Assignment assignment) throws IOException, URISyntaxException, ParseException;

    /**
     * Deletes a specified assignment in canvas
     * @param courseId Course ID of course to delete assignment from
     * @param assignmentId Assignment ID of assignment to delete
     * @return The deleted Assignment object as returned by the Canvas API
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<Assignment> deleteAssignment(String courseId, Long assignmentId) throws IOException, URISyntaxException, ParseException;

    /**
     * Writes an Assignment object to the Canvas API
     * @param courseId Course ID that this assignment is associated with
     * @param assignment The assignment settings to write to the API
     * @return The modified Assignment returned by the API
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<Assignment> editAssignment(String courseId, Assignment assignment) throws IOException, URISyntaxException, ParseException;
}
