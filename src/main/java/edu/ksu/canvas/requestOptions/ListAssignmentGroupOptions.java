package edu.ksu.canvas.requestOptions;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ListAssignmentGroupOptions extends BaseOptions{
    private String courseId;


    public enum Include {
        ASSIGNMENTS, DISCUSSION_TOPIC, ALL_DATES, ASSIGNMENT_VISIBILITY, OVERRIDES, SUBMISSION;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    public enum ExcludeSubmissionTypes {
        ONLINE_QUIZ, DISCUSSION_TOPIC, WIKI_PAGE, EXTERNAL_TOOL;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    public ListAssignmentGroupOptions(String courseId) {
        if(StringUtils.isBlank(courseId)) {
            throw new IllegalArgumentException("Course ID can not be blank");
        }
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }

    /**
     * Associations to include with the group. “discussion_topic”, “all_dates” “assignment_visibility” and “submission”
     * are only valid if “assignments” is also included. The “assignment_visibility” option additionally
     * requires that the Differentiated Assignments course feature be turned on.
     * @param includes List of optional associations to include in the assignment request.
     * @return this to continue building options
     */
    public ListAssignmentGroupOptions includes(List<Include> includes) {
        List<Include> assignmentDependents = Arrays.asList(Include.DISCUSSION_TOPIC, Include.ALL_DATES, Include.ASSIGNMENT_VISIBILITY, Include.SUBMISSION);
        if(includes.stream().anyMatch(assignmentDependents::contains) && !includes.contains(Include.ASSIGNMENTS)) {
            throw new IllegalArgumentException("Including discussion topics, all dates, assignment visibility or submissions is only valid if you also include assignments");
        }
        addEnumList("include[]", includes);
        return this;
    }

    /**
     * If “assignments” are included, those with the specified submission types will be excluded
     * from the assignment groups.
     * @param toExclude A list of the types of assignments that will be excluded.
     * @return this to continue building options
     */
    public ListAssignmentGroupOptions excludeAssignmentSubmissionTypes (List<ExcludeSubmissionTypes> toExclude) {
        addEnumList("exclude_assignment_submission_types[]", toExclude);
        return this;
    }

    /**
     * Apply assignment overrides to the assignment, defaults to true.
     * @param overrideDates Whether or not to apply override
     * @return this to continue building options
     */
    public ListAssignmentGroupOptions overrideAssignmentDates (Boolean overrideDates){
        addSingleItem("override_assignment_dates", overrideDates.toString());
        return this;
    }

    /**
     * The id of the grading period in which assignment groups are being requested (Requires grading periods to exist.)
     * @param periodId The ID of the appropriate period
     * @return this to continue building options
     */
    public ListAssignmentGroupOptions gradingPeriodId (Integer periodId){
        addSingleItem("grading_period_id", periodId.toString());
        return this;
    }

    /**
     * If true, all assignments returned will apply to the current user in the specified grading period. If assignments
     * apply to other students in the specified grading period, but not the current user, they will not be returned.
     * (Requires the grading_period_id argument and grading periods to exist. In addition, the current user must be a
     * student.)
     * @param scopeToStudent Whether or not all assignments will apply to the current user in the specified grading period.
     * @return this to continue building options
     */
    public ListAssignmentGroupOptions scopeAssignmentsToStudent (Boolean scopeToStudent){
        addSingleItem("scope_assignments_to_student", scopeToStudent.toString());
        return this;
    }

}
