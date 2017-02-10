package edu.ksu.canvas.exception;

public class ObjectNotFoundException extends CanvasException {
    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException() {
        super();
    }

    public ObjectNotFoundException(String canvasErrorString, String url) {
        super(canvasErrorString, url);
    }
}
