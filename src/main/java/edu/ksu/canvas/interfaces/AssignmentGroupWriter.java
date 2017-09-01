package edu.ksu.canvas.interfaces;


import edu.ksu.canvas.model.assignment.AssignmentGroup;
import edu.ksu.canvas.requestOptions.DeleteAssignmentGroupOptions;

import java.io.IOException;
import java.util.Optional;

public interface AssignmentGroupWriter extends CanvasWriter<AssignmentGroup, AssignmentGroupWriter>{

    /**
     * Creates an assignment group in Canvas.
     * @param courseId Course that the assignment group is in
     * @param assignmentGroup The Assignment Group object to create
     * @return AssignmentGroup object created in Canvas
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<AssignmentGroup> createAssignmentGroup(String courseId, AssignmentGroup assignmentGroup) throws IOException;

    /**
     * Modify an existing assignment group in Canvas. It must have an ID assigned by Canvas.
     * @param courseId Course ID of the course containing the assignment group to edit
     * @param assignmentGroup Modified assignment group to save
     * @return Modified assignment group from Canvas
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<AssignmentGroup> editAssignmentGroup(String courseId, AssignmentGroup assignmentGroup) throws IOException;

    /**
     * Delete an assignment group from Canvas.
     * Optionally, move assignments in the deleted group to a new group. If not specified, these assignments are deleted.
     * @param options Collection of optional parameters for the Canvas API call
     * @return The deleted assignment group object
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<AssignmentGroup> deleteAssignmentGroup(DeleteAssignmentGroupOptions options) throws IOException;
}
