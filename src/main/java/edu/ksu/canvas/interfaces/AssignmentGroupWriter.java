package edu.ksu.canvas.interfaces;


import edu.ksu.canvas.model.assignment.AssignmentGroup;

import java.io.IOException;
import java.util.Optional;

public interface AssignmentGroupWriter extends CanvasWriter<AssignmentGroup, AssignmentGroupWriter>{

    Optional<AssignmentGroup> createAssignmentGroup(String courseId, AssignmentGroup assignmentGroup) throws IOException;
}
