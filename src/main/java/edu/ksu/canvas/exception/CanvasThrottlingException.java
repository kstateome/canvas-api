package edu.ksu.canvas.exception;

/**
 * Thrown if Canvas returns a 404 response.
 */
public class CanvasThrottlingException extends CanvasException {
    private static final long serialVersionUID = 1L;

    public CanvasThrottlingException() {
        super();
    }

    public CanvasThrottlingException(String canvasErrorString, String url) {
        super(canvasErrorString, url);
    }
}
