package edu.ksu.canvas.requestOptions;

/**
 * Options available when creating an enrollment.  See
 * https://canvas.instructure.com/doc/api/enrollments.html#method.enrollments_api.create
 * for a discussion of these.
 */
public class EnrollUserOptions extends BaseOptions {

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
        ACTIVE,
        INVITED,
        INACTIVE;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    public EnrollUserOptions userId(String userId) {
        addSingleItem("enrollment[user_id]", userId);
        return this;
    }

    public EnrollUserOptions type(EnrollmentType enrollmentType) {
        addSingleItem("enrollment[type]", enrollmentType.toString());
        return this;
    }

    public EnrollUserOptions role(long role) {
        addSingleItem("enrollment[role_id]", Long.toString(role));
        return this;
    }

    public EnrollUserOptions state(EnrollmentState enrollmentState) {
        addSingleItem("enrollment[enrollment_state]", enrollmentState.toString());
        return this;
    }

    public EnrollUserOptions courseSectionId(long courseSectionId) {
        addSingleItem("enrollment[course_section_id]", Long.toString(courseSectionId));
        return this;
    }

    public EnrollUserOptions limitPrivilegesToCourseSection(boolean limit) {
        addSingleItem("enrollment[limit_privileges_to_course_section]", Boolean.toString(limit));
        return this;
    }

    public EnrollUserOptions notify(boolean notify) {
        addSingleItem("enrollment[notify]", Boolean.toString(notify));
        return this;
    }

    public EnrollUserOptions selfEnrollmentCode(String selfEnrollmentCode) {
        addSingleItem("enrollment[self_enrollment_code]", selfEnrollmentCode);
        return this;
    }

    public EnrollUserOptions selfEnrolled(boolean selfEnrolled) {
        addSingleItem("enrollment[self_enrolled]", Boolean.toString(selfEnrolled));
        return this;
    }

    public EnrollUserOptions associatedUserId(long associatedUserId) {
        addSingleItem("enrollment[associated_user_id]", Long.toString(associatedUserId));
        return this;
    }
}
