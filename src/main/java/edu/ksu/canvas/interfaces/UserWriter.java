package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.model.User;

import java.io.IOException;
import java.util.Optional;

public interface UserWriter extends CanvasBase {
    /**
     *
     * @param user  user data for creating user account
     * @return
     * @throws InvalidOauthTokenException
     * @throws IOException
     */
     Optional<User> createUser (User user) throws InvalidOauthTokenException, IOException;



    /**
     *
     * @param user
     * @return
     * @throws InvalidOauthTokenException
     * @throws IOException
     */
     Optional<User> updateUser(User user) throws InvalidOauthTokenException, IOException;
}
