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
     * Restore a deleted user in Canvas
     * @param accountId account id for restoring user into an account
     * @param userId user id for restoring user
     * @return The newly restored user
     * @throws InvalidOauthTokenException When the supplied OAuth token is not valid
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<User> restoreUser (String accountId, String userId) throws InvalidOauthTokenException, IOException;

    /**
     * Update user information
     * @param user The user object with updated data to push to Canvas
     * @return The updated user
     * @throws InvalidOauthTokenException When the supplied OAuth token is not valid
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<User> updateUser(User user) throws InvalidOauthTokenException, IOException;

    /**
     * Merge a user into another user.
     * @param fromUserId The fromUserId is the user that was deleted in the user_merge process.
     * @param destinationUserId The destinationUserId is the user that remains, that is being split.
     * @return An Optional object that may include the merged user
     * @throws InvalidOauthTokenException When the supplied OAuth token is not valid
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<User> mergeUsers(String fromUserId, String destinationUserId) throws InvalidOauthTokenException, IOException;

    /**
     * Merge a user into another user in a specific account. When finding users by SIS ids in different accounts the destination_account_id is required.
     * @param fromUserId The fromUserId is the user that was deleted in the user_merge process.
     * @param destinationAccountId The account can also be identified by passing the domain in destination_account_id.
     * @param destinationUserId The destinationUserId is the user that remains, that is being split.
     * @return An Optional object that may include the merged user
     * @throws InvalidOauthTokenException When the supplied OAuth token is not valid
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<User> mergeUsersIntoAccount(String fromUserId, String destinationAccountId, String destinationUserId) throws InvalidOauthTokenException, IOException;
}
