package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.model.Course;
import edu.ksu.canvas.model.Enrollment;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by prakashreddy on 10/6/15.
 * Methods to manager course
 */

public interface CourseManager {
    /**
     *
     * @param oauthToken
     * @return CreatedCourse
     * @throws InvalidOauthTokenException
     * @throws IOException
     */
    public Optional<Course> createCourse (String oauthToken,Course course) throws InvalidOauthTokenException, IOException;

    /**
     *
     * @param oauthToken
     * @return
     * @throws InvalidOauthTokenException
     * @throws IOException
     */
    public Boolean deleteCourse(String oauthToken, Course course) throws InvalidOauthTokenException, IOException;

    public Optional<Enrollment> enrollUser(String oauthToken, Integer course_Id, Integer userId) throws InvalidOauthTokenException, IOException;

    public Optional<Enrollment> dropUser(String oauthToken, Integer course_id, Long enrollment_id) throws InvalidOauthTokenException, IOException;
}
