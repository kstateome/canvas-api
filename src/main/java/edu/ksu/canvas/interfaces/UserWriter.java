package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.model.User;
import edu.ksu.canvas.requestOptions.CreateUserOptions;

import java.io.IOException;
import java.util.Optional;

public interface UserWriter extends CanvasWriter<User, UserWriter> {
    /**
     * Create a new user in Canvas
     * @param user user data for creating user account
     * @return The newly created user
     * @throws InvalidOauthTokenException When the supplied OAuth token is not valid
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<User> createUser(User user) throws InvalidOauthTokenException, IOException;

    /**
     * Create a new user in Canvas
     * @param user  user data for creating user account
     * @param options the options for the user creation
     * @return The newly created user
     * @throws InvalidOauthTokenException When the supplied OAuth token is not valid
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<User> createUser (User user, CreateUserOptions options) throws InvalidOauthTokenException, IOException;


    /**
     * Update user information
     * @param user The user object with updated data to push to Canvas
     * @return The updated user
     * @throws InvalidOauthTokenException When the supplied OAuth token is not valid
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<User> updateUser(User user) throws InvalidOauthTokenException, IOException;

    /**
     * Terminates all login sessions for the given user ID.
     * This includes expiring all access tokens for OAuth applications
     * @param userId The user ID to operate on
     * @throws InvalidOauthTokenException When the supplied OAuth token is not valid
     * @throws IOException When there is an error communicating with Canvas
     */
    void terminateUserSessions(String userId) throws InvalidOauthTokenException, IOException;
}
