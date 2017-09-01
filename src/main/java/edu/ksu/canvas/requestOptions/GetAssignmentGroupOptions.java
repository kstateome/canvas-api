package edu.ksu.canvas.requestOptions;

import java.util.List;

public class GetAssignmentGroupOptions extends BaseOptions {

    public enum Include {
        ASSIGNMENTS, DISCUSSION_TOPIC, ASSIGNMENT_VISIBILITY, SUBMISSION;

        public String toString() {
            return name().toLowerCase();
        }
    }

    private String courseId;
    private Long assignmentGroupId;

    public GetAssignmentGroupOptions(String courseId, Long assignmentGroupId) {
        this.courseId = courseId;
        this.assignmentGroupId = assignmentGroupId;
    }

    public String getCourseId() {
        return courseId;
    }

    public Long getAssignmentGroupId() {
        return assignmentGroupId;
    }

    /**
     * Additional objects to include with the requested group.
     * Note that all the optional includes depend on "assignments" also being included.
     * @param includes List of included objects to return
     * @return this object to continue building options
     */
    public GetAssignmentGroupOptions includes(List<Include> includes) {
        addEnumList("include[]", includes);
        return this;
    }

    /**
     * Whether or not to apply assignment overrides for each assignment. Defaults to true.
     * @param overrideDates Boolean indicating whether to apply assignment overrides
     * @return this object to continue building options
     */
    public GetAssignmentGroupOptions overrideAssignmentDates (Boolean overrideDates){
        addSingleItem("override_assignment_dates", overrideDates.toString());
        return this;
    }

    /**
     * Limit assignment groups to a grading period.
     * Requires grading periods to exist at the account level
     * @param periodId The ID of the grading period
     * @return this object to continue building options
     */
    public GetAssignmentGroupOptions gradingPeriodId (Integer periodId){
        addSingleItem("grading_period_id", periodId.toString());
        return this;
    }
}
