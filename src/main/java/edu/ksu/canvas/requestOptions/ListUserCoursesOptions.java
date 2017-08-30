package edu.ksu.canvas.requestOptions;

import java.util.List;

public class ListUserCoursesOptions extends BaseOptions {
    public enum Include {
        NEEDS_GRADING_COUNT, SYLLABUS_BODY, PUBLIC_DESCRIPTION, TOTAL_SCORES,
        CURRENT_GRADING_PERIOD_SCORES, TERM, COURSE_PROGRESS, SECTIONS,
        STORAGE_QUOTA_USED_MB, TOTAL_STUDENTS, PASSBACK_STATUS, FAVORITES,
        TEACHERS, OBSERVED_USERS, TABS, COURSE_IMAGE;

        @Override
        public String toString() { return name().toLowerCase(); }
    }

    public enum State {
        UNPUBLISHED, AVAILABLE, COMPLETED, DELETED;

        @Override
        public String toString() { return name().toLowerCase(); }
    }

    public enum EnrollmentState {
        ACTIVE, INVITED_OR_PENDING, COMPLETED;

        @Override
        public String toString() { return name().toLowerCase(); }
    }

    private final String userId;

    /**
     * Constructor with required userId
     * @param userId can be the canvasUserId or sis_login_id:loginName
     */
    public ListUserCoursesOptions(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    /**
     * Include additional optional fields in the course objects
     * @param includes List of additional fields to include
     * @return This object to allow adding more options
     */
    public ListUserCoursesOptions includes(List<Include> includes) {
        addEnumList("include[]", includes);
        return this;
    }

    /**
     * If set, only return courses that are in the given state(s)
     * @param states A list of states to filter on
     * @return This object to allow adding more options
     */
    public ListUserCoursesOptions states(List<State> states) {
        addEnumList("state[]", states);
        return this;
    }
    /**
     * If set, only return courses that are in the given enrollment state
     * @param enrollmentState A list of states to filter on
     * @return This object to allow adding more options
     */
    public ListUserCoursesOptions enrollmentStates(EnrollmentState enrollmentState) {
        addSingleItem("enrollment_state", enrollmentState.toString());
        return this;
    }
}
