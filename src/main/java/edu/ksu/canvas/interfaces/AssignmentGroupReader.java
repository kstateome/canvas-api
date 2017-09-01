package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.assignment.AssignmentGroup;
import edu.ksu.canvas.requestOptions.GetAssignmentGroupOptions;
import edu.ksu.canvas.requestOptions.ListAssignmentGroupOptions;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface AssignmentGroupReader extends CanvasReader<AssignmentGroup, AssignmentGroupReader>{

    /**
     * Return a list of AssignmentGroups that the current user can view or manage.
     * @param options A mapping of optional parameters for assignment groups
     * @return List of AssignmentGroups
     * @throws IOException When there is an error communicating with Canvas
     */
    List<AssignmentGroup> listAssignmentGroup(ListAssignmentGroupOptions options) throws IOException;

    /**
     * Returns the requested assignment group by ID
     * @param options Collection of optional parameters to the Canvas API
     * @return The requested assignment group
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<AssignmentGroup> getAssignmentGroup(GetAssignmentGroupOptions options) throws IOException;
}
