package edu.ksu.canvas.requestOptions;

import java.util.List;

public class GetSingleAssignmentOptions extends BaseOptions {
    
    private String courseId;
    private String assignmentId;

    public enum Include {
        SUBMISSION, ASSIGNMENT_VISIBILITY, OVERRIDES, OBSERVED_USERS;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    public GetSingleAssignmentOptions(String courseId, String assignmentId) {
        this.courseId = courseId;
        this.assignmentId = assignmentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    /**
     * Associations to include with the assignment. 
     * The “assignment_visibility” option requires that the Differentiated Assignments course feature be turned on.
     * @param includes List of optional associations to include in the assignment request
     * @return this to continue building options
     */
    public GetSingleAssignmentOptions includes(List<Include> includes) {
        addEnumList("include[]", includes);
        return this;
    }

    /**
     * Apply assignment overrides to the assignment, defaults to true.
     * @param overrideDates Whether or not to apply override
     * @return this to continue building options
     */
    public GetSingleAssignmentOptions overrideAssignmentDates(Boolean overrideDates) {
        addSingleItem("override_assignment_dates", overrideDates.toString());
        return this;
    }

    /**
     * Specify whether or not to split up the "needs grading count" by sections
     * into the "needs_grading_count_by_section key. Defaults to false.
     * @param gradingCountBySection Whether or not to split up needs_grading_count by section
     * @return this to continue building options
     */
    public GetSingleAssignmentOptions needsGradingCountBySection(Boolean gradingCountBySection) {
        addSingleItem("needs_grading_count_by_section", gradingCountBySection.toString());
        return this;
    }

    /**
     * Specify if all dates associated with the assignment should be returned, if applicable
     * @param allDates If all dates should be returned
     * @return this to continue building options
     */
    public GetSingleAssignmentOptions allDates(Boolean allDates) {
        addSingleItem("all_dates", allDates.toString());
        return this;
    }
}
