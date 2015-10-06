package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.model.Course;

import java.io.IOException;

/**
 * Created by prakashreddy on 10/6/15.
 * Methods to manager course
 */

public interface CourseManager {
    /**
     *
     * @param oauthToken
     * @param url //<https://k-state.test.instructure.com/api/v1/accounts/1/courses/>
     * @return CreatedCourse
     * @throws InvalidOauthTokenException
     * @throws IOException
     */
    public Course createCourse (String oauthToken, String url) throws InvalidOauthTokenException, IOException;

    /**
     *
     * @param oauthToken
     * @param url//<https://k-state.test.instructure.com/api/v1/courses/1?event=delet/>
     * @return
     * @throws InvalidOauthTokenException
     * @throws IOException
     */
    public Course deleteCourse (String oauthToken, String url) throws InvalidOauthTokenException, IOException;
}
