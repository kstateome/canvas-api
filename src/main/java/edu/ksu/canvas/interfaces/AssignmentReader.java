package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.Assignment;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Methods to read information from and about Assignments
 */
public interface AssignmentReader extends CanvasReader<Assignment, AssignmentReader> {
    /**
     * Retrieve a specific assignment from Canvas by its Canvas ID number
     * @param courseId The Canvas ID of the course
     * @param assignmentId The Canvas ID of the assignment
     * @return The assignment returned by Canvas or an empty Optional
     * @throws IOException
     */
    Optional<Assignment> getSingleAssignment(String courseId, String assignmentId) throws IOException;

    /**
     * Retrieve a specific list of assignments from Canvas by its Canvas ID number
     * @param courseId The Canvas ID of the course
     * @return List of assignments in the course with the course ID
     * @throws IOException
     */
    List<Assignment> listCourseAssignments(String courseId) throws IOException;
}
