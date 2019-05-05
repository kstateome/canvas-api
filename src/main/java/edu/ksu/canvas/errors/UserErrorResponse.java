package edu.ksu.canvas.errors;

import java.util.List;

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
        private User user;
        private Pseudonym pseudonym;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Pseudonym getPseudonym() {
            return pseudonym;
        }

        public void setPseudonym(Pseudonym pseudonym) {
            this.pseudonym = pseudonym;
        }

        public static class User {
            private List<Error> pseudonyms;

            public List<Error> getPseudonyms() {
                return pseudonyms;
            }

            public void setPseudonyms(List<Error> pseudonyms) {
                this.pseudonyms = pseudonyms;
            }
        }

        public static class Pseudonym {
            private List<Error> uniqueId;

            public List<Error> getUniqueId() {
                return uniqueId;
            }

            public void setUniqueId(List<Error> uniqueId) {
                this.uniqueId = uniqueId;
            }
        }

        public static class Error {
            private String attribute;
            private String type;
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
    }
}
