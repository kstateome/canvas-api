package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.assignment.AssignmentGroup;
import edu.ksu.canvas.requestOptions.AssignmentGroupOptions;

import java.io.IOException;
import java.util.List;

public interface AssignmentGroupReader extends CanvasReader<AssignmentGroup, AssignmentGroupReader>{

    /**
     * Return a list of AssignmentGroups that the current user can view or manage.
     * @param courseId The course which contains the desired groups
     * @param options A mapping of optional parameters for assignment groups
     * @return List of AssignmentGroups
     * @throws IOException When there is an error communicating with Canvas
     */
    List<AssignmentGroup> listAssignmentGroup(String courseId, AssignmentGroupOptions options) throws IOException;

}
