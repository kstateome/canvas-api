package edu.ksu.canvas.requestOptions;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class GetSubmissionsOptions extends BaseOptions {
    private String courseId;
    private String assignmentId;

    public enum Include {
        // The canvas API gives a 3rd option here with a value of "submissions" but does not document what is
        // returned so I am not including it as an option until we can determine exactly what it is.
        USER, QUIZ;

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

    public GetSubmissionsOptions(final String courseId, final String assignmentId, final Include... includes) {
        this.courseId = courseId;
        this.assignmentId = assignmentId;
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
}
