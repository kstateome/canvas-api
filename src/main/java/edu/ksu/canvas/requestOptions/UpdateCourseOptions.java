package edu.ksu.canvas.requestOptions;

/**
 * Options for updating a tab for a given course.
 */
public class UpdateCourseOptions extends BaseOptions {
	
    private String courseId;
    
    /**
     * Create an options object for updating a single course. Must have a course ID set.
     * @param courseId Required: ID of the course to get. May be of the form sis_course_id:abc123
     */    
    public UpdateCourseOptions(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void publish() {
        addSingleItem("course[event]", "offer");
    }
}
