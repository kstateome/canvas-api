package edu.ksu.canvas.requestOptions;

import org.apache.commons.lang3.StringUtils;

public class DeleteAssignmentGroupOptions extends BaseOptions {

    private String courseId;
    private Integer assignmentGroupId;

    public DeleteAssignmentGroupOptions(String courseId, Integer assignmentGroupId) {
        if(StringUtils.isBlank(courseId) || assignmentGroupId == null || assignmentGroupId == 0) {
            throw new RuntimeException("Course ID and assignment group ID must be specified");
        }
        this.courseId = courseId;
        this.assignmentGroupId = assignmentGroupId;
    }

    public String getCourseId() {
        return courseId;
    }

    public Integer getAssignmentGroupId() {
        return assignmentGroupId;
    }

    /**
     * Optionally specify a new group to move assignments from the deleted group to.
     * @param newAssignmentGroupId ID of the target assignment group
     * @return this object to continue building options
     */
    public DeleteAssignmentGroupOptions moveAssignmentsToGroup(Integer newAssignmentGroupId) {
        addSingleItem("move_assignments_to", newAssignmentGroupId.toString());
        return this;
    }
}
