package edu.ksu.canvas.requestOptions;

import java.util.List;

public class ListCurrentUserCoursesOptions extends BaseOptions {

    public enum EnrollmentType {
        STUDENT, TEACHER, TA, OBSERVER, DESIGNER;

        @Override
        public String toString() { return name().toLowerCase(); }
    }

    public enum Include {
        NEEDS_GRADING_COUNT, SYLLABUS_BODY, TOTAL_SCORES, TERM, COURSE_PROGRESS,
        SECTIONS, STORAGE_QUOTA_USED_MB, TOTAL_STUDENTS, FAVORITES, TEACHERS,
        OBSERVED_USERS, CURRENT_GRADING_PERIOD_SCORES;

        @Override
        public String toString() { return name().toLowerCase(); }
    }

    public enum State {
        UNPUBLISHED, AVAILABLE, COMPLETED, DELETED;

        @Override
        public String toString() { return name().toLowerCase(); }
    }

    /**
     * When given, only return courses where the user is enrolled as the given type(s)
     * @param enrollmentType Enrollment type to restrict the list to
     * @return This object to allow adding more options
     */
    public ListCurrentUserCoursesOptions withEnrollmentType(EnrollmentType enrollmentType) {
        addSingleItem("enrollment_type", enrollmentType.toString());
        return this;
    }

    /**
     * When set, only return courses where the user is enrolled with the specified course-level role
     * @param roleId The ID of the role to restrict the list to
     * @return This object to allow adding more options
     */
    public ListCurrentUserCoursesOptions withEnrollmentRoleId(Integer roleId) {
        addSingleItem("enrollment_role_id", roleId.toString());
        return this;
    }

    /**
     * Include additional optional fields in the course objects
     * @param includes List of additional fields to include
     * @return This object to allow adding more options
     */
    public ListCurrentUserCoursesOptions includes(List<Include> includes) {
        addEnumList("include[]", includes);
        return this;
    }

    /**
     * If set, only return courses that are in the given state(s)
     * @param states A list of states to filter on
     * @return This object to allow adding more options
     */
    public ListCurrentUserCoursesOptions states(List<State> states) {
        addEnumList("state[]", states);
        return this;
    }
}
