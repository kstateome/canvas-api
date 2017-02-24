package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.User;
import edu.ksu.canvas.requestOptions.GetUsersInCourseOptions;

import java.io.IOException;
import java.util.List;

public interface UserReader extends CanvasReader<User, UserReader> {
    /**
     * Retrieve a list of users in a course
     * @param options API options for this API call
     * @return List of users in a course
     * @throws IOException When there is an error communicating with Canvas
     */
    List<User> getUsersInCourse(GetUsersInCourseOptions options) throws IOException;
}
