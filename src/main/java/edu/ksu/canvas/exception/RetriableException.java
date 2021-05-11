package edu.ksu.canvas.exception;

/**
 * Thrown if Canvas returns a retriable response, for example, a 504, Gateway Timeout.
 * This usually means that the front end web server has timed out while waiting for the
 * application server to complete the request. The request could still be in flight on
 * the application server but there is no guarantee as to what state it is in.
 * Usually you will probably want to retry the request.
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
