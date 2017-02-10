package edu.ksu.canvas.exception;

import java.util.Optional;

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
