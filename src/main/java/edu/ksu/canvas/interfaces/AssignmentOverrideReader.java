package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.assignment.AssignmentOverride;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface AssignmentOverrideReader extends CanvasReader<AssignmentOverride, AssignmentOverrideReader> {
    
    /***
     * Returns the list of overrides for this assignment that target sections/groups/students visible to the current user.
     * @param courseId
     * @param assignmentId
     * @return
     * @throws IOException 
     */
    List<AssignmentOverride> listAssignmentOverrides(String courseId, String assignmentId) throws IOException;
    
    /***
     * Returns details of the the override with the given id.
     * @param courseId
     * @param assignmentId
     * @param id
     * @return
     * @throws IOException 
     */
    Optional<AssignmentOverride> getAssignmentOverride(String courseId, String assignmentId, String id) throws IOException;
}