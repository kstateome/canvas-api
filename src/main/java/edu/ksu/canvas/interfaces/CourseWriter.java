package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.model.Course;

import java.io.IOException;
import java.util.Optional;

public interface CourseWriter extends CanvasWriter<Course, CourseWriter> {
    /**
     * @param course A course object containing the information needed to create a course in Canvas
     * @return The newly created course
     * @throws InvalidOauthTokenException When the supplied OAuth token is invalid
     * @throws IOException When there is an error communicating with Canvas
     */
     Optional<Course> createCourse(Course course) throws InvalidOauthTokenException, IOException;

    /**
     * @param courseId The ID of the course you wish to delete
     * @return True if the operation succeeded
     * @throws InvalidOauthTokenException When the supplied OAuth token is invalid
     * @throws IOException When there is an error communicating with Canvas
     */
     Boolean deleteCourse(String courseId) throws InvalidOauthTokenException, IOException;
}
