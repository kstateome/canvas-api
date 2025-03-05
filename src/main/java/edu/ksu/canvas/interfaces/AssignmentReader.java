package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.assignment.Assignment;
import edu.ksu.canvas.requestOptions.GetSingleAssignmentOptions;
import edu.ksu.canvas.requestOptions.ListCourseAssignmentsOptions;
import edu.ksu.canvas.requestOptions.ListUserAssignmentOptions;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * Methods to read information from and about Assignments
 */
public interface AssignmentReader extends CanvasReader<Assignment, AssignmentReader> {
    /**
     * Retrieve a specific assignment from Canvas by its Canvas ID number
     * @param options Options class containing required and optional parameters for this API call
     * @return The assignment returned by Canvas or an empty Optional
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<Assignment> getSingleAssignment(GetSingleAssignmentOptions options) throws IOException, URISyntaxException, ParseException;

    /**
     * Retrieve a list of assignments associated with a course
     * @param options Options class containing required and optional parameters for this API call
     * @return List of assignments in the requested course
     * @throws IOException When there is an error communicating with Canvas
     */
    List<Assignment> listCourseAssignments(ListCourseAssignmentsOptions options) throws IOException, ParseException, URISyntaxException;

    /**
     * Retrieve a list of assignments associated with the given user/course combination
     * @param options Options class containing required and optional parameters for this API call
     * @return List of assignments for the user/course combination
     * @throws IOException When there is an error communicating with Canvas
     */
    List<Assignment> listUserAssignments(ListUserAssignmentOptions options) throws IOException, URISyntaxException, ParseException;
}
