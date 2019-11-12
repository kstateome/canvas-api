package edu.ksu.canvas.requestOptions;

import java.util.List;

public class GetSubmissionsOptions extends BaseOptions {
    private String canvasId;
    private Integer assignmentId;

    public enum Include {
        //Submissions can optionally have sub-objects returned with them. The commented out ones are not implemented yet
        //SUBMISSION_HISTORY, RUBRIC_ASSESSMENT, GROUP
        ASSIGNMENT, COURSE, SUBMISSION_COMMENTS, USER, VISIBILITY;

        public String toString() {
            return name().toLowerCase();
        }
    }

    /**
     * Construct options class with required parameters to retrieve Submissions from courses or sections
     * @param canvasId The Course or Section ID, depending on which API is being targeted. May also be an SIS ID with appropriate prefix
     * @param assignmentId The Canvas ID of the assignment to query for submissions
     */
    public GetSubmissionsOptions(final String canvasId, final Integer assignmentId) {
        this.canvasId = canvasId;
        this.assignmentId = assignmentId;
    }

    /**
     * Optionally include more information with the returned Quiz Submission objects.
     * @param includes List of optional includes
     * @return This object to allow adding more options
     */
    public GetSubmissionsOptions includes(final List<Include> includes) {
        addEnumList("include[]", includes);
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
}
