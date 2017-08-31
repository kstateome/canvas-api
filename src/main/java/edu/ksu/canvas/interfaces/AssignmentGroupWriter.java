package edu.ksu.canvas.interfaces;


import edu.ksu.canvas.model.assignment.AssignmentGroup;

import java.io.IOException;
import java.util.Optional;

public interface AssignmentGroupWriter extends CanvasWriter<AssignmentGroup, AssignmentGroupWriter>{

    /**
     * Creates an assignment group in canvas. The group object must have a non-null name set.
     * @param courseId Course that the assignment is in
     * @param assignmentGroup The Assignment Group object to create
     * @return AssignmentGroup object created in Canvas
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<AssignmentGroup> createAssignmentGroup(String courseId, AssignmentGroup assignmentGroup) throws IOException;
}
