package edu.ksu.canvas.requestOptions;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class GetSubmissionsOptions extends BaseOptions {
    private String courseId;
    private String assignmentId;

    public enum Include {
        //Submissions can optionally have sub-objects returned with them. The commented out ones are not implemented yet
        //SUBMISSION_HISTORY, RUBRIC_ASSESSMENT, GROUP
        ASSIGNMENT, COURSE, SUBMISSION_COMMENTS, USER, VISIBILITY;

        public String toString() {
            return name().toLowerCase();
        }
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

    public GetSubmissionsOptions(final String id, final String assignmentId, final Include... includes) {
        this.courseId = id;
        this.assignmentId = assignmentId;
        if (includes.length > 0) {
            includes(ImmutableList.copyOf(includes));
        }
    }

    public String getId() {
        return courseId;
    }

    public void setCourseId(final String courseId) {
        this.courseId = courseId;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(final String assignmentId) {
        this.assignmentId = assignmentId;
    }
}
