package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.enums.AssignmentType;
import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.exception.OauthTokenRequiredException;
import edu.ksu.canvas.model.Assignment;
import edu.ksu.lti.error.MessageUndeliverableException;

import java.io.IOException;
import java.util.Optional;

public interface AssignmentWriter {
    /**
     * Creates an assignment in canvas
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
     * Creates an assignment in canvas
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
     * Deletes a specified assignment in canvas
     * @param oauthToken
     * @param courseId
     * @param assignmentId
     * @return
     * @throws InvalidOauthTokenException
     * @throws IOException
     */
    Boolean deleteAssignment(String oauthToken, String courseId, String assignmentId)
            throws InvalidOauthTokenException, IOException;

    /**
     * Sets a specified assignment to be visible to overrides in canvas
     * @param oauthToken OAuth token used for authentication
     * @param courseId Course the assignment is in
     * @param assignmentId ID of the assignment to create override for
     * @return Assignment object update in Canvas
     * @throws MessageUndeliverableException
     * @throws IOException
     * @throws OauthTokenRequiredException
     */
    Optional<Assignment> setOnlyVisibleToOverrides(String oauthToken, String courseId, String assignmentId, boolean onlyVisibleToOverrides)
            throws MessageUndeliverableException, IOException, OauthTokenRequiredException;
}
