package edu.ksu.canvas.errors;

import java.util.List;
import java.util.Map;

/**
 * The error object that is returned when the creation of certain objects fails because the data isn't good.
 * This allows you to retrieve more information about what was wrong with your request.
 */
public class GenericErrorResponse {

    private Map<String, List<ErrorDetails>> errors;

    public Map<String, List<ErrorDetails>> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, List<ErrorDetails>> errors) {
        this.errors = errors;
    }

}
