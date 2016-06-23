package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.AssignmentOverride;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface AssignmentOverrideWriter extends CanvasWriter<AssignmentOverride, AssignmentOverrideWriter> {
    /**
     * Creates an assignment override in canvas for the specified assignment
     * @param courseId Course the assignment is in
     * @param assignmentId ID of the assignment to create override for
     * @param studentIds List of Canvas User IDs to create the override for
     * @param title Title to give the override (description)
     * @return AssingmentOverride object created in Canvas
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<AssignmentOverride> createAssignmentOverride(String courseId, String assignmentId, List<Integer> studentIds, String title)
            throws IOException;
}
