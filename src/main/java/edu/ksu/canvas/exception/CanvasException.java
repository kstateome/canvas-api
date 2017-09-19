package edu.ksu.canvas.exception;

/**
 * Base exception for errors arising while talking to Canvas.
 * When thrown, it can optionally carry a string containing the
 * human readable error message returned by Canvas, if any.
 * Sometimes it may be appropriate to display this error message
 * to the user.It can also carry the URL of the failed request.
 */
public class CanvasException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final String canvasErrorMessage;
    private final String requestUrl;

    public CanvasException() {
        canvasErrorMessage = null;
        requestUrl = null;
    }

    public CanvasException(String canvasErrorString, String url) {
        super(String.format("Error from URL %s : %s", url, canvasErrorString));
        canvasErrorMessage = canvasErrorString;
        requestUrl = url;
    }

    public String getCanvasErrorMessage() {
        return canvasErrorMessage;
    }

    public String getRequestUrl() {
        return requestUrl;
    }
}
