package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.enums.AssignmentType;
import edu.ksu.canvas.model.Assignment;

import java.io.IOException;
import java.util.Optional;

public interface AssignmentWriter extends CanvasWriter<Assignment, AssignmentWriter> {

    /**
     * Create an assignment in Canvas. The only required field is name.
     * @param courseId ID of the course to create the assignment in
     * @param assignment Assignment object to create. Must have at least a name set
     * @return The created assignment object
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<Assignment> createASsignment(String courseId, Assignment assignment) throws IOException;

    /**
     * Creates an assignment in canvas
     * @param courseId id of the course the quiz is going to be in
     * @param assignmentName  name of assignment
     * @param pointsPossible  highest possible number of points of assignment
     * @return The newly created assignment
     * @throws IOException When there is an error communicating with Canvas
     * @deprecated Use createAssignment(courseId, assignment) instead
     */
    @Deprecated
    Optional<Assignment> createAssignment (String courseId, String assignmentName, String pointsPossible) throws IOException;

    /**
     * Creates an assignment in canvas
     * @param courseId id of the course the quiz is going to be in
     * @param assignmentName  name of assignment
     * @param pointsPossible  highest possible number of points of assignment
     * @param assignmentType type of the assignment to be created
     * @param published publish status of created assignment
     * @param muted muted status of created assignment
     * @return The newly created assignment
     * @throws IOException When there is an error communicating with Canvas
     * @deprecated Use createAssignment(courseId, assignment) instead
     */
    @Deprecated
    Optional<Assignment> createAssignment(String courseId, String assignmentName, String pointsPossible,
                                                 AssignmentType assignmentType, boolean published, boolean muted) throws IOException;

    /**
     * Deletes a specified assignment in canvas
     * @param courseId Course ID of course to delete assignment from
     * @param assignmentId Assignment ID of assignment to delete
     * @return True if the operation succeeded
     * @throws IOException When there is an error communicating with Canvas
     */
    Boolean deleteAssignment(String courseId, String assignmentId)
            throws IOException;

    /**
     * Sets a specified assignment to be visible to overrides in canvas
     * @param courseId Course the assignment is in
     * @param assignmentId ID of the assignment to create override for
     * @param onlyVisibleToOverrides Whether this assignment is only visible to people who are assigned to it
     * @return Assignment object update in Canvas
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<Assignment> setOnlyVisibleToOverrides(String courseId, String assignmentId, boolean onlyVisibleToOverrides) throws IOException;
}
