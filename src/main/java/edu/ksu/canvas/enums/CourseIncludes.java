package edu.ksu.canvas.enums;

/**
 * Optional items to include in calls to retrieve a course object
 */
public enum CourseIncludes {
    enrollments,
    needs_grading_count,
    syllabus_body,
    total_scores,
    term,
    course_progress,
    sections,
    storage_quota_used_mb,
    total_students,
    favorites,
    //next two only used in the "get single course" call
    all_courses,
    permissions
}
