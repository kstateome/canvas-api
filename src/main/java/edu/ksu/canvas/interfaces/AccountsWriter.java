package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.exception.InvalidOauthTokenException;

import java.io.IOException;

public interface AccountsWriter {
    /**
     *
     * @param oauthToken
     * @param userId  canvas user if for the user
     * @return
     * @throws InvalidOauthTokenException
     * @throws IOException
     */
     Boolean deleteUser(String oauthToken, Integer userId) throws InvalidOauthTokenException, IOException;
}
