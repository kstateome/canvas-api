package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.Assignment;

import java.io.IOException;
import java.util.Optional;

/**
 * Methods to read information from and about Assignments
 */
public interface AssignmentReader {
    /**
     * Retrieve a specific course from Canvas by its Canvas ID number
     * @param oauthToken    OAuth token to use when executing API calls
     * @param courseId The Canvas ID of the course
     * @param assignmentId The Canvas ID of the assignment
     * @return The course returned by Canvas or an empty Optional
     * @throws IOException
     */
    Optional<Assignment> getSingleCourse(String oauthToken, String courseId, String assignmentId) throws IOException;
}
