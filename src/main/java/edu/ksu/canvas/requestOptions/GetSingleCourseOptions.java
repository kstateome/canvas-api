package edu.ksu.canvas.requestOptions;

import java.util.List;
import java.util.stream.Collectors;

public class GetSingleCourseOptions extends BaseOptions {

    private String courseId;

    public enum Include {
        //same include options as list courses call
        needs_grading_count, syllabus_body, total_scores, term, course_progress, 
        sections, storage_quota_used_mb, total_students, favorites, teachers, 
        observed_users, current_grading_period_scores,
        //options specific to get single course call
        all_courses, permissions
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
        List<String> includeStrings = includes.stream().map(i -> i.name()).collect(Collectors.toList());
        optionsMap.put("include[]", includeStrings);
        return this;
    }
}
