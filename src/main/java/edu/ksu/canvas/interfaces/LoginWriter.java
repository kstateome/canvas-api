package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.Optional;

import edu.ksu.canvas.model.Login;
import edu.ksu.canvas.requestOptions.UpdateLoginOptions;

public interface LoginWriter extends CanvasWriter<Login, LoginWriter> {

    /**
     * Write login update details to Canvas.
     * @param options options used to build the PUT query
     * @return an optional login record, if the update was successful
     * @see UpdateLoginOptions
     */
    Optional<Login> updateLogin(UpdateLoginOptions options) throws IOException;
}
