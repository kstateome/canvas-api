package edu.ksu.canvas.requestOptions;

import java.util.List;

public class GetSubmissionsOptions extends BaseOptions {

    private String canvasId;
    private Integer assignmentId;
    private String userId;

    public enum Include {
        //Submissions can optionally have sub-objects returned with them. The commented out ones are not implemented yet
        // RUBRIC_ASSESSMENT, GROUP
        ASSIGNMENT, COURSE, SUBMISSION_COMMENTS, USER, VISIBILITY, SUBMISSION_HISTORY;

        public String toString() {
            return name().toLowerCase();
        }
    }

    /**
     * Construct options class with required parameters to retrieve a list of Submissions from courses or sections
     * @param canvasId The Course or Section ID, depending on which API is being targeted. May also be an SIS ID with appropriate prefix
     * @param assignmentId The Canvas ID of the assignment to query for submissions
     */
    public GetSubmissionsOptions(final String canvasId, final Integer assignmentId) {
        this.canvasId = canvasId;
        this.assignmentId = assignmentId;
    }

    /**
     * Construct options class with required parameters to retrieve a single user's Submission from a course or section
     * @param canvasId The Course or Section ID, depending on which API is being targeted. May also be an SIS ID with appropriate prefix
     * @param assignmentId The Canvas ID of the assignment to query for submissions
     * @param userId The Canvas ID of the user to query (or SIS user ID with the appropriate prefix)
     */
    public GetSubmissionsOptions(final String canvasId, final Integer assignmentId, String userId) {
        this.canvasId = canvasId;
        this.assignmentId = assignmentId;
        this.userId = userId;
    }

    /**
     * Optionally include more information with the returned assignment Submission objects.
     * @param includes List of optional includes
     * @return This object to allow adding more options
     */
    public GetSubmissionsOptions includes(final List<Include> includes) {
        addEnumList("include[]", includes);
        return this;
    }

    /**
     * When set to true, response will be grouped by student groups.
     * Only valid for Submission lists, not individual submission queries.
     * @param grouped Whether to group submissions by student group
     * @return This object to allow adding more options
     */
    public GetSubmissionsOptions grouped(Boolean grouped) {
        addSingleItem("grouped", Boolean.toString(grouped));
        return this;
    }

    public String getCanvasId() {
        return canvasId;
    }

    public void setCanvasId(final String canvasId) {
        this.canvasId = canvasId;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(final Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
