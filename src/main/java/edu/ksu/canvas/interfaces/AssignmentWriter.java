package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.model.Assignment;

import java.io.IOException;
import java.util.Optional;

public interface AssignmentWriter {
    /**
     *
     * @param oauthToken
     * @param assignmentName  name of assignment
     * @param pointsPossible  highest possible number of points of assignment
     * @return CreatedAssignment
     * @throws InvalidOauthTokenException
     * @throws IOException
     */
    Optional<Assignment> createAssignment (String oauthToken, String assignmentName, String pointsPossible) throws InvalidOauthTokenException, IOException;

    /**
     *
     * @param oauthToken
     * @param courseId
     * @param assignmentId
     * @return
     * @throws InvalidOauthTokenException
     * @throws IOException
     */
    Boolean deleteAssignment(String oauthToken, String courseId, String assignmentId) throws InvalidOauthTokenException, IOException;
}
