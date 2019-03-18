package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.assignment.AssignmentOverride;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface AssignmentOverrideReader extends CanvasReader<AssignmentOverride, AssignmentOverrideReader> {
    
    /***
     * Returns the list of overrides for this assignment that target sections/groups/students visible to the current user.
     * @param courseId Canvas course ID or "sis_course_id:1234" for SIS course ID
     * @param assignmentId Canvas assignment ID
     * @return List of assignment overrides
     * @throws IOException When there is an error communicating with Canvas
     */
    List<AssignmentOverride> listAssignmentOverrides(String courseId, Integer assignmentId) throws IOException;
    
    /***
     * Returns details of the the override with the given id.
     * @param courseId Canvas course ID or "sis_course_id:1234" for SIS course ID
     * @param assignmentId Canvas assignment ID
     * @param overrideId Canvas ID of the specific assignment override to query for
     * @return The requested assigment override object
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<AssignmentOverride> getAssignmentOverride(String courseId, Integer assignmentId, Integer overrideId) throws IOException;
}