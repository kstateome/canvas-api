package edu.ksu.canvas.exception;

/**
 * Thrown if Canvas returns a 403 response in response to too many API requests
 * See https://canvas.instructure.com/doc/api/file.throttling.html
 */
public class ThrottlingException extends CanvasException {
    private static final long serialVersionUID = 1L;

    public ThrottlingException() {
        super();
    }

    public ThrottlingException(String canvasErrorString, String url) {
        super(canvasErrorString, url);
    }
}
