package edu.ksu.canvas.requestOptions;

/**
 * Class for capturing options passed to the "List assignments for user" API call
 * The options are identical to the "List assignments for course" call except
 * the additional user ID parameter is required.
 */
public class ListUserAssignmentOptions extends ListCourseAssignmentsOptions {
    private String userId;

    public ListUserAssignmentOptions(String courseId, String userId) {
        super(courseId);
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
