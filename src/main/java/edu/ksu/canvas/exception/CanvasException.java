package edu.ksu.canvas.exception;

import java.util.Optional;

/**
 * Base exception for errors arising while talking to Canvas.
 * When thrown, it can optionally carry a string containing the
 * human readable error message returned by Canvas, if any.
 * Sometimes it may be appropriate to display this error message
 * to the user.It can also carry the URL of the failed request.
 */
public class CanvasException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final Optional<String> canvasErrorMessage;
    private final Optional<String> requestUrl;
    
    public CanvasException() {
        canvasErrorMessage = Optional.empty();
        requestUrl = Optional.empty();
    }

    public CanvasException(String canvasErrorString, String url) {
        canvasErrorMessage = Optional.ofNullable(canvasErrorString);
        requestUrl = Optional.ofNullable(url);
    }

    public Optional<String> getCanvasErrorMessage() {
        return canvasErrorMessage;
    }

    public Optional<String> getRequestUrl() {
        return requestUrl;
    }
}
