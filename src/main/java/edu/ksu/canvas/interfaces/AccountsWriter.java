package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.exception.InvalidOauthTokenException;

import java.io.IOException;

public interface AccountsWriter extends CanvasBase {
    /**
     *
     * @param userId  canvas user if for the user
     * @return
     * @throws InvalidOauthTokenException
     * @throws IOException
     */
     Boolean deleteUser(Integer userId) throws InvalidOauthTokenException, IOException;
}
