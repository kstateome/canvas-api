package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.User;
import edu.ksu.canvas.requestOptions.GetUsersInCourseOptions;

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
     * Retrieve a specific user details from Canvas by user identifier
     * @param userIdentifier The object holding a user identifier value, for example:
     *    Numeric Canvas user ID
     *    A string like "sis_integration_id:123" to query by SIS integration ID
     *    A string like "sis_user_id:ABC" to query by SIS ID
     *    The special string "self" that the Canvas API interprets as "the currently logged in user"
     * @return The User returned by Canvas or an empty Optional
     * @throws IOException When there is an error communicating with Canvas
     */
     Optional<User> showUserDetails(String userIdentifier) throws IOException;
}
