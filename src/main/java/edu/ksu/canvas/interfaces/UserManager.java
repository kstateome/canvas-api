package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.model.Course;
import edu.ksu.canvas.model.User;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by prakashreddy on 10/7/15.
 */
public interface UserManager {
    /**
     *
     * @param oauthToken
     * @param user  user data for creating user account
     * @return
     * @throws InvalidOauthTokenException
     * @throws IOException
     */
    public Optional<User> createUser (String oauthToken,User user) throws InvalidOauthTokenException, IOException;

    /**
     *
     * @param oauthToken
     * @param userId  canvas user if for the user
     * @return
     * @throws InvalidOauthTokenException
     * @throws IOException
     */
    public Boolean deleteUser(String oauthToken, Integer userId) throws InvalidOauthTokenException, IOException;

    /**
     *
     * @param oauthToken
     * @param user
     * @return
     * @throws InvalidOauthTokenException
     * @throws IOException
     */
    public Optional<User> updateUser(String oauthToken, User user) throws InvalidOauthTokenException, IOException;

}
