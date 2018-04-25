package edu.ksu.canvas.requestOptions;

import java.util.List;

public class GetSingleCourseOptions extends BaseOptions {

    private String courseId;

    public enum Include {
        //same include options as list courses call
        NEEDS_GRADING_COUNT, SYLLABUS_BODY, TOTAL_SCORES, TERM, COURSE_PROGRESS,
        SECTIONS, STORAGE_QUOTA_USED_MB, TOTAL_STUDENTS, FAVORITES, TEACHERS,
        OBSERVED_USERS, CURRENT_GRADING_PERIOD_SCORES,
        //options specific to get single course call
        ALL_COURSES, PERMISSIONS, LICENSE;

        @Override
        public String toString() { return name().toLowerCase(); }
    }

    /**
     * Create an options object for retrieving a single course. Must have a course ID set.
     * @param courseId Required: ID of the course to get. May be of the form sis_course_id:abc123
     */
    public GetSingleCourseOptions(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }

    /**
     * Include additional optional fields in the course objects
     * @param includes List of additional fields to include
     * @return This object to allow adding more options
     */
    public GetSingleCourseOptions includes(List<Include> includes) {
        addEnumList("include[]", includes);
        return this;
    }
}
