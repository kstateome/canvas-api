package edu.ksu.canvas.requestOptions;

import java.util.List;

public class AssignmentGroupOptions extends BaseOptions{
    private String courseId;


    public enum Include {
        ASSIGNMENTS, DISCUSSION_TOPIC, ALL_DATES, ASSIGNMENT_VISIBILITY, OVERRIDES, SUBMISSION;

        public String toString() {
            return name().toLowerCase();
        }
    }

    public enum ExcludeSubmissionTypes {
        ONLINE_QUIZ, DISCUSSION_TOPIC, WIKI_PAGE, EXTERNAL_TOOL;

        public String toString() {
            return name().toLowerCase();
        }
    }

    public AssignmentGroupOptions(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }

    /**
     * Associations to include with the group. “discussion_topic”, “all_dates” “assignment_visibility” & “submission”
     * are only valid are only valid if “assignments” is also included. The “assignment_visibility” option additionally
     * requires that the Differentiated Assignments course feature be turned on.
     * @param includes List of optional associations to include in the assignment request.
     * @return this to continue building options
     */
    public AssignmentGroupOptions includes(List<Include> includes) {
        addEnumList("include[]", includes);
        return this;
    }

    /**
     * If “assignments” are included, those with the specified submission types will be excluded
     * from the assignment groups.
     * @param toExclude A list of the types of assignments that will be excluded.
     * @return this to continue building options
     */
    public AssignmentGroupOptions excludeAssignmentSubmissionTypes (List<ExcludeSubmissionTypes> toExclude) {
        addEnumList("exclude_assignment_submission_types[]", toExclude);
        return this;
    }

    /**
     * Apply assignment overrides to the assignment, defaults to true.
     * @param overrideDates Whether or not to apply override
     * @return this to continue building options
     */
    public AssignmentGroupOptions overrideAssignmentDates (Boolean overrideDates){
        addSingleItem("override_assignment_dates", overrideDates.toString());
        return this;
    }

    /**
     * The id of the grading period in which assignment groups are being requested (Requires grading periods to exist.)
     * @param periodId The ID of the appropriate period
     * @return this to continue building options
     */
    public AssignmentGroupOptions gradingPeriodId (Integer periodId){
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
    public AssignmentGroupOptions scopeAssignmentsToStudent (Boolean scopeToStudent){
        addSingleItem("scope_assignments_to_student", scopeToStudent.toString());
        return this;
    }

}
