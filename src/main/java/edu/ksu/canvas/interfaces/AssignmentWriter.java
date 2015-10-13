package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.enums.AssignmentType;
import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.model.Assignment;

import java.io.IOException;
import java.util.Optional;

public interface AssignmentWriter {
    /**
     *
     * @param oauthToken
     * @param courseId id of the course the quiz is going to be in
     * @param assignmentName  name of assignment
     * @param pointsPossible  highest possible number of points of assignment
     * @return CreatedAssignment
     * @throws InvalidOauthTokenException
     * @throws IOException
     */
    Optional<Assignment> createAssignment (String oauthToken, String courseId, String assignmentName, String pointsPossible) throws InvalidOauthTokenException, IOException;

    /**
     *
     * @param oauthToken
     * @param courseId id of the course the quiz is going to be in
     * @param assignmentName  name of assignment
     * @param pointsPossible  highest possible number of points of assignment
     * @param assignmentType type of the assignment to be created
     * @param published publish status of created assignment
     * @param muted muted status of created assignment
     * @return CreatedAssignment
     * @throws InvalidOauthTokenException
     * @throws IOException
     */
    Optional<Assignment> createAssignment(String oauthToken, String courseId, String assignmentName, String pointsPossible,
                                                 AssignmentType assignmentType, boolean published, boolean muted) throws InvalidOauthTokenException, IOException;

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
