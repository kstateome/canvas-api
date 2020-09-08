package edu.ksu.canvas.exception;

/**
 * Thrown if Canvas returns a retriable response, for example, a 504, Gateway Timeout.
 */
public class RetriableException extends CanvasException {
    private static final long serialVersionUID = 1L;

    public RetriableException() {
        super();
    }

    public RetriableException(String canvasErrorString, String url) {
        super(canvasErrorString, url);
    }
}
