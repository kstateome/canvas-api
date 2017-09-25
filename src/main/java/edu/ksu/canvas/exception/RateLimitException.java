package edu.ksu.canvas.exception;

/**
 * Thrown if Canvas returns a 403 Rate Limit Exceeded response.
 */
public class RateLimitException extends CanvasException {

    public RateLimitException() {
        super();
    }

    public RateLimitException(String canvasErrorString, String url) {
        super(canvasErrorString, url);
    }
}
