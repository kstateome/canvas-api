package edu.ksu.canvas.requestOptions;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class ListCourseAssignmentSubmissionsOptions extends BaseOptions {
    private String courseId;
    private String assignmentId;
    private String userId;
    private boolean grouped;

    public enum Include {
        //A submission supports these options, more work is required to support all of them
        //SUBMISSION_HISTORY, SUBMISSION_COMMENTS, RUBRIC_ASSESSMENT, ASSIGNMENT, VISIBILITY, COURSE, USER, GROUP;
        SUBMISSION_HISTORY, SUBMISSION_COMMENTS, VISIBILITY, GROUP;

        public String toString() {
            return name().toLowerCase();
        }
    }

    /**
     * Optionally include more information with the returned Assignment Submission objects.
     * @param includes List of optional includes
     * @return This object to allow adding more options
     */
    public ListCourseAssignmentSubmissionsOptions includes(final List<Include> includes) {
        addEnumList("include[]", includes);
        return this;
    }

    public ListCourseAssignmentSubmissionsOptions(final String courseId, final String assignmentId, final boolean grouped, final Include... includes) {
        this.courseId = courseId;
        this.assignmentId = assignmentId;
        this.grouped = grouped;
        if (includes.length > 0) {
            includes(ImmutableList.copyOf(includes));
        }
    }
    
    public ListCourseAssignmentSubmissionsOptions(final String courseId, final String assignmentId, final String userId, final boolean grouped, final Include... includes) {
        this.courseId = courseId;
        this.assignmentId = assignmentId;
        this.userId = userId;
        this.grouped = grouped;
        if (includes.length > 0) {
            includes(ImmutableList.copyOf(includes));
        }
    }

    public String getCourseId() {
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
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }    
    
    public boolean getGrouped() {
        return grouped;
    }

    public void setGrouped(final boolean grouped) {
        this.grouped = grouped;
    }
}
