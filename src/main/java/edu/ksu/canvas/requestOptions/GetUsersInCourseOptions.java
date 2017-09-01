package edu.ksu.canvas.requestOptions;

import java.util.List;


public class GetUsersInCourseOptions extends BaseOptions {

    public enum EnrollmentType {
        TEACHER, STUDENT, STUDENT_VIEW, TA, OBSERVER, DESIGNER;

        @Override
        public String toString() { return name().toLowerCase(); }
    }

    public enum Include {
        EMAIL, ENROLLMENTS, LOCKED, AVATAR_URL, TEST_STUDENT, BIO, CUSTOM_LINKS;

        @Override
        public String toString() { return name().toLowerCase(); }
    }

    public enum EnrollmentState{
        ACTIVE, INVITED, REJECTED, COMPLETED, INACTIVE;

        @Override
        public String toString() { return name().toLowerCase(); }
    }

    private String courseId;

    public GetUsersInCourseOptions(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }

    /**
     * Only return users who match search term. Can be a partial name or a full ID
     * @param searchTerm Search term to filter users by
     * @return This object to allow adding more options
     */
    public GetUsersInCourseOptions searchTerm(String searchTerm) {
        addSingleItem("search_term", searchTerm);
        return this;
    }

    /**
     * Only return users who match the given enrollment type. Ignored if enrollmentRole is used.
     * @param enrollmentTypes List of enrollment types to include
     * @return This object to allow adding more options
     */
    public GetUsersInCourseOptions enrollmentType(List<EnrollmentType> enrollmentTypes) {
        addEnumList("enrollment_type[]", enrollmentTypes);
        return this;
    }

    /**
     * Filter users by course-level role ID. This can be a custom role created via the Role API or a built in role
     * @param roleId ID of the role to filter users by
     * @return This object to allow adding more options
     */
    public GetUsersInCourseOptions enrollmentRoleId(Integer roleId) {
        addSingleItem("enrollment_role_id", roleId.toString());
        return this;
    }

    /**
     * Optional sub-items to include in the user response
     * @param includes List of includes to request
     * @return This object to allow adding more options
     */
    public GetUsersInCourseOptions include(List<Include> includes) {
        addEnumList("include[]", includes);
        return this;
    }

    /**
     * If the given user ID exists in a course, the page parameter will be modified to return the page containing the given user.
     * @param userId User ID to page to
     * @return This object to allow adding more options
     */
    public GetUsersInCourseOptions userId(String userId) {
        addSingleItem("user_id", userId);
        return this;
    }

    /**
     * Only return the users in the given list of user IDs
     * @param userIds List of user IDs to return
     * @return This object to allow adding more options
     */
    public GetUsersInCourseOptions userIds(List<String> userIds) {
        optionsMap.put("user_ids[]", userIds);
        return this;
    }

    /**
     * Filter users by enrollment state
     * @param enrollmentStates List of enrollment states to filter users by
     * @return  This object to allow adding more options
     */
    public GetUsersInCourseOptions enrollmentState(List<EnrollmentState> enrollmentStates) {
        addEnumList("enrollment_state[]", enrollmentStates);
        return this;
    }
}
