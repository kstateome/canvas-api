package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.model.Course;

import java.io.IOException;
import java.util.Optional;

public interface CourseWriter {
    /**
     *
     * @param oauthToken
     * @return CreatedCourse
     * @throws InvalidOauthTokenException
     * @throws IOException
     */
     Optional<Course> createCourse(String oauthToken,Course course) throws InvalidOauthTokenException, IOException;

    /**
     *
     * @param oauthToken
     * @return
     * @throws InvalidOauthTokenException
     * @throws IOException
     */
     Boolean deleteCourse(String oauthToken, String course) throws InvalidOauthTokenException, IOException;
}
