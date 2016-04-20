package edu.ksu.canvas.exception;

public class MessageUndeliverableException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String message;

    public MessageUndeliverableException(String message) {
        this.setMessage(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
