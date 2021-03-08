package edu.ksu.canvas.errors;

import java.util.List;
import java.util.Map;

/**
 * The error object that is returned when the creation of a user fails because the data isn't good.
 * This allows you to retrieve more information about what was wrong with your request.
 */
public class UserErrorResponse {

    private Errors errors;

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }

    public static class Errors {
        private Map<String, List<ErrorDetails>>  user;
        private Map<String, List<ErrorDetails>>  pseudonym;

        public Map<String, List<ErrorDetails>>  getUser() {
            return user;
        }

        public void setUser(Map<String, List<ErrorDetails>>  user) {
            this.user = user;
        }

        public Map<String, List<ErrorDetails>>  getPseudonym() {
            return pseudonym;
        }

        public void setPseudonym(Map<String, List<ErrorDetails>>  pseudonym) {
            this.pseudonym = pseudonym;
        }
    }
}
