package edu.ksu.canvas.requestOptions;

import java.util.List;

public class GetEnrollmentOptions extends BaseOptions {

    //I was hoping to reuse this enum between different classes that take an 
    //enrollment type parameter but nope. In the calls covered by this class 
    //the string passed to the API is (for example) "StudentEnrollment" wile 
    //in other places it is just "student"
    //I opened an issue to try and convince them to make things compatible:
    //https://github.com/instructure/canvas-lms/issues/946
    public enum EnrollmentType {
        STUDENT("StudentEnrollment"),
        TEACHER("TeacherEnrollment"),
        TA("TaEnrollment"),
        DESIGNER("DesignerEnrollment"),
        OBSERVER("ObserverEnrollment");
    
        private String canvasValue;
    
        EnrollmentType(String canvasValue) {
            this.canvasValue = canvasValue;
        }
    
        @Override
        public String toString() {
            return canvasValue;
        }
    }

    public enum EnrollmentState {
        ACTIVE, INVITED, CREATION_PENDING, DELETED, REJECTED, COMPLETED, INACTIVE;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    public enum Include {
        AVATAR_URL, GROUP_IDS, LOCKED, OBSERVED_USERS, CAN_BE_REMOVED;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    private String objectId;

    /**
     * Constructs object to hold API options for the enrollment retriever calls.
     * Courses, sections and users all have an identical API call that all use this object.
     * @param objectId The course, section or user ID, depending on which API call you are hitting
     */
    public GetEnrollmentOptions(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectId() {
        return objectId;
    }

    /**
     * Specify enrollment types to filter the returned list by (student, teacher, TA, designer, observer)
     * This option is ignored if a role is specified.
     * @param enrollmentTypes All the enrollment types you want to retrieve
     * @return This object to allow adding more options
     */
    public GetEnrollmentOptions type(List<EnrollmentType> enrollmentTypes) {
        addEnumList("type[]", enrollmentTypes);
        return this;
    }

    /**
     * Filter the list of enrollments by the given enrollment roles. 
     * Accepted values are the same as the base enrollment types in the "type" parameter
     * and any custom roles created via the Roles API.
     * @param roles List of roles to filter enrollments by
     * @return This object to allow adding more options
     */
    public GetEnrollmentOptions role(List<String> roles) {
        optionsMap.put("role[]", roles);
        return this;
    }

    /**
     * Filter the list of enrollments by the given enrollment states. Defaults to "active" and "invited"
     * @param enrollmentStates List of enrollment states to filter on
     * @return This object to allow adding more options
     */
    public GetEnrollmentOptions state(List<EnrollmentState> enrollmentStates) {
        addEnumList("state[]", enrollmentStates);
        return this;
    }

    /**
     * Additional objects to include in the returned enrollments.
     * Avatar URL and group IDs will be returned on the user record
     * @param includes List of additional items to include
     * @return This object to allow adding more options
     */
    public GetEnrollmentOptions include(List<Include> includes) {
        addEnumList("include[]", includes);
        return this;
    }

    /**
     * Filter by the given user ID. Only valid on course or section enrollment queries.
     * If set to the current user's id, this is a way to determine if the user has any enrollments
     * in the course or section, independent of whether the user has permission to view other people on the roster.
     * @param userId User ID to filter enrollments by
     * @return This object to allow adding more options
     */
    public GetEnrollmentOptions userId(String userId) {
        addSingleItem("user_id", userId);
        return this;
    }

    /**
     * Return grades for the given grading period. If not set, grades will be for the entire course.
     * @param gradingPeriod Grading period to restrict reported grades to
     * @return This object to allow adding more options
     */
    public GetEnrollmentOptions gradingPeriodId(Integer gradingPeriod) {
        addSingleItem("grading_period_id", gradingPeriod.toString());
        return this;
    }

    /**
     * Only return enrollments for the specified SIS account IDs. Does not recurse into sub-accounts.
     * @param sisAccountIds SIS account Ids
     * @return This object to allow adding more options
     */
    public GetEnrollmentOptions sisAccountId(List<String> sisAccountIds) {
        optionsMap.put("sis_account_id[]", sisAccountIds);
        return this;
    }

    /**
     * Only return enrollments for the specified SIS course IDs.
     * @param sisCourseIds SIS course Ids
     * @return This object to allow adding more options
     */
    public GetEnrollmentOptions sisCourseId(List<String> sisCourseIds) {
        optionsMap.put("sis_course_id[]", sisCourseIds);
        return this;
    }

    /**
     * Only return enrollments for the specified SIS section IDs.
     * @param sisSectionIds SIS section IDs
     * @return This object to allow adding more options
     */
    public GetEnrollmentOptions sisSectionId(List<String> sisSectionIds) {
        optionsMap.put("sis_section_id[]", sisSectionIds);
        return this;
    }

    /**
     * Only return enrollments for the specified SIS user IDs.
     * @param sisUserIds SIS user Ids
     * @return This object to allow adding more options
     */
    public GetEnrollmentOptions sisUserId(List<String> sisUserIds) {
        optionsMap.put("sis_user_id[]", sisUserIds);
        return this;
    }
}
