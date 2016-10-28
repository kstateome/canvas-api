package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.Optional;

import edu.ksu.canvas.model.assignment.AssignmentOverride;

public interface AssignmentOverrideWriter extends CanvasWriter<AssignmentOverride, AssignmentOverrideWriter> {

    /**
     * Creates an assignment override in canvas. The override object must have an assignment ID set.
     * @param courseId Course that the assignment is in
     * @param assignmentOverride The Override object to create
     * @return AssignmentOverride object created in Canvas
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<AssignmentOverride> createAssignmentOverride(String courseId, AssignmentOverride assignmentOverride) throws IOException;
}
