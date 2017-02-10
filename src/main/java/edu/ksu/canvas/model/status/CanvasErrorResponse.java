package edu.ksu.canvas.model.status;

import java.util.List;

public class CanvasErrorResponse {

    private Long errorReportId;
    private List<ErrorMessage> errors;

    public void setErrorReportId(Long errorReportId) {
        this.errorReportId = errorReportId;
    }

    public Long getErrorReportId() {
        return errorReportId;
    }

    public void setErrors(List<ErrorMessage> errors) {
        this.errors = errors;
    }

    public List<ErrorMessage> getErrors() {
        return errors;
    }

    public class ErrorMessage {
        private String message;

        public void setMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
