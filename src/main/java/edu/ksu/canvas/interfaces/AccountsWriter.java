package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.exception.InvalidOauthTokenException;

import java.io.IOException;

/**
 * Created by prv on 10/8/15.
 */
public interface AccountsWriter {
    /**
     *
     * @param oauthToken
     * @param userId  canvas user if for the user
     * @return
     * @throws InvalidOauthTokenException
     * @throws IOException
     */
    public Boolean deleteUser(String oauthToken, Integer userId) throws InvalidOauthTokenException, IOException;
}
