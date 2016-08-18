package edu.ksu.canvas.requestOptions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class ListCurrentUserCoursesOptions extends BaseOptions {

    public enum EnrollmentType {
        student, teacher, ta, observer, designer;
    }

    public enum Include {
        needs_grading_count, syllabus_body, total_scores, term, course_progress,
        sections, storage_quota_used_mb, total_students, favorites, teachers,
        observed_users, current_grading_period_scores
    }

    public enum State {
        unpublished, available, completed, deleted
    }

    /**
     * When given, only return courses where the user is enrolled as the given type(s)
     * @param enrollmentType Enrollment type to restrict the list to
     * @return This object to allow adding more options
     */
    public ListCurrentUserCoursesOptions withEnrollmentType(EnrollmentType enrollmentType) {
        optionsMap.put("enrollment_type", Arrays.asList(enrollmentType.name()));
        return this;
    }

    /**
     * When set, only return courses where the user is enrolled with the specified course-level role
     * @param roleId The ID of the role to restrict the list to
     * @return This object to allow adding more options
     */
    public ListCurrentUserCoursesOptions withEnrollmentRoleId(Integer roleId) {
        optionsMap.put("enrollment_role_id", Arrays.asList(roleId.toString()));
        return this;
    }

    /**
     * Include additional optional fields in the course objects
     * @param includes List of additional fields to include
     * @return This object to allow adding more options
     */
    public ListCurrentUserCoursesOptions includes(List<Include> includes) {
        List<String> includeStrings = includes.stream().map(i -> i.name()).collect(Collectors.toList());
        optionsMap.put("include[]", includeStrings);
        return this;
    }

    /**
     * If set, only return courses that are in the given state(s)
     * @param states A list of states to filter on
     * @return This object to allow adding more options
     */
    public ListCurrentUserCoursesOptions states(List<State> states) {
        List<String> stateStrings = states.stream().map(i -> i.name()).collect(Collectors.toList());
        optionsMap.put("state[]", stateStrings);
        return this;
    }
}
