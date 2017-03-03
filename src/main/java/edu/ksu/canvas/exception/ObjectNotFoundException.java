package edu.ksu.canvas.exception;

/**
 * Thrown if Canvas returns a 404 response.
 */
public class ObjectNotFoundException extends CanvasException {
    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException() {
        super();
    }

    public ObjectNotFoundException(String canvasErrorString, String url) {
        super(canvasErrorString, url);
    }
}
