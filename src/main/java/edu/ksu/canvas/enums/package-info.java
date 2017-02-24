/**
 * Shared enums
 * <p>
 * The hope was to consolidate enums, especially in the API options classes into
 * one place. The problem is that even when two different API calls accept (for
 * example) an <code>enrollment_type</code> parameter, they may either accept
 * different values or require different string literals for the same logical
 * value.
 * <p>
 * Example: the <code>List your courses</code> API call takes an
 * <code>enrollment_type</code> parameter which accepts the values: teacher,
 * student, ta, observer, designer. The <code>Get users in course</code> API
 * call also has an <code>enrollment_type</code> parameter and accepts all the
 * previously listed ones plus student_view. The <code>List enrollments</code>
 * API call also has the same parameter but it requires the strings
 * StudentEnrollment, TeacherEnrollment, TaEnrollment, DesignerEnrollment, and
 * ObserverEnrollment.
 * <p>
 * So all the enums ended up getting sucked into the options classes where they
 * could reflect the actual values that Canvas allows and is expecting.
 */
package edu.ksu.canvas.enums;