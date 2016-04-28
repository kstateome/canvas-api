package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.model.Course;

import java.io.IOException;
import java.util.Optional;

public interface CourseWriter extends CanvasWriter<Course, CourseWriter> {
    /**
     *
     * @return CreatedCourse
     * @throws InvalidOauthTokenException
     * @throws IOException
     */
     Optional<Course> createCourse(Course course) throws InvalidOauthTokenException, IOException;

    /**
     *
     * @return
     * @throws InvalidOauthTokenException
     * @throws IOException
     */
     Boolean deleteCourse(String course) throws InvalidOauthTokenException, IOException;
}
