package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.User;
import edu.ksu.canvas.requestOptions.GetUsersInCourseOptions;
import edu.ksu.canvas.requestOptions.ShowUserDetailsOptions;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserReader extends CanvasReader<User, UserReader> {
    /**
     * Retrieve a list of users in a course
     * @param options API options for this API call
     * @return List of users in a course
     * @throws IOException When there is an error communicating with Canvas
     */
    List<User> getUsersInCourse(GetUsersInCourseOptions options) throws IOException;

    /**
     * Retrieve a specific user details from Canvas by Id or SisIntegrationId
     * @param options The object holding options for this API call
     * @return The User returned by Canvas or an empty Optional
     * @throws IOException When there is an error communicating with Canvas
     */
     Optional<User> showUserDetails(ShowUserDetailsOptions options) throws IOException;
}
