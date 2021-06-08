package edu.ksu.canvas.errors;

/**
  * This allows you to retrieve more information about what was wrong with your request.
 */
public class ErrorDetails {

    private String type;
    private String attribute;
    private String message;

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
