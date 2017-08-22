package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.assignment.AssignmentGroup;

import java.io.IOException;
import java.util.List;

public interface AssignmentGroupReader extends CanvasReader<AssignmentGroup, AssignmentGroupReader>{

    List<AssignmentGroup> listAssignmentGroup(String courseId) throws IOException;

}
