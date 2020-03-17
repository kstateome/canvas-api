package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.Optional;

import edu.ksu.canvas.model.Login;

public interface LoginWriter extends CanvasWriter<Login, LoginWriter> {

    /**
     * Write changes to a login to Canvas.
     * @param login The modified login to update. Must contain account ID and login ID.
     * @return The modified login object if update was successful
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<Login> updateLogin(Login login) throws IOException;

    /**
     * Delete a login from Canvas
     * @param login The login record to delete. Must contain User ID and login ID.
     * @return The now deleted login record
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<Login> deleteLogin(Login login) throws IOException;
}
