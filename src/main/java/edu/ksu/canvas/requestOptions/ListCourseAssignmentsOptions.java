package edu.ksu.canvas.requestOptions;

import java.util.List;

public class ListCourseAssignmentsOptions extends BaseOptions {

    public enum Include {
        SUBMISSION, ASSIGNMENT_VISIBILITY, ALL_DATES, OVERRIDES, OBSERVED_USERS;

        @Override
        public String toString() { return name().toLowerCase(); }
    }

    public enum Bucket {
        PAST, OVERDUE, UNDATED, UNGRADED, UNSUBMITTED, UPCOMING, FUTURE;

        @Override
        public String toString() { return name().toLowerCase(); }
    }

    private String courseId;

    public ListCourseAssignmentsOptions(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }

    /**
     * Associations to include with the assignment.
     * The “assignment_visibility” option requires that the Differentiated Assignments course feature be turned on.
     * @param includes List of optional associations to include in the assignment request
     * @return this to continue building options
     */
    public ListCourseAssignmentsOptions includes(List<Include> includes) {
        addEnumList("include[]", includes);
        return this;
    }

    /**
     * Restrict results to partial matches in the assignment title
     * @param searchTerm Search term to match
     * @return this to continue building options
     */
    public ListCourseAssignmentsOptions searchTerm(String searchTerm) {
        addSingleItem("search_term", searchTerm);
        return this;
    }

    /**
     * Apply assignment overrides to the assignment, defaults to true.
     * @param overrideDates Whether or not to apply override
     * @return this to continue building options
     */
    public ListCourseAssignmentsOptions overrideAssignmentDates(Boolean overrideDates) {
        addSingleItem("override_assignment_dates", overrideDates.toString());
        return this;
    }

    /**
     * Specify whether or not to split up the "needs grading count" by sections
     * into the "needs_grading_count_by_section key. Defaults to false.
     * @param gradingCountBySection Whether or not to split up needs_grading_count by section
     * @return this to continue building options
     */
    public ListCourseAssignmentsOptions needsGradingCountBySection(Boolean gradingCountBySection) {
        addSingleItem("needs_grading_count_by_section", gradingCountBySection.toString());
        return this;
    }

    /**
     * Only return assignments that match the due date and submission status of the given bucket
     * @param bucket Status bucket to filter assignments by
     * @return this to continue building options
     */
    public ListCourseAssignmentsOptions bucketFilter(Bucket bucket) {
        addSingleItem("bucket", bucket.toString());
        return this;
    }
}
