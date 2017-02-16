package edu.ksu.canvas.model.status;

import java.util.List;

/**
 * Class to represent JSON error messages returned by Canvas.
 * <p>
 * For some errors, Canvas returns JSON error strings that can be
 * parsed and displayed to the user. There isn't much documentation
 * on exactly how these messages are structured but here is an example
 * of something that is returned (along with an HTTP 400 in this case)
 * if a student tries to submit answers to a quiz that they are not
 * authorized to participate in:
 * <pre>{"status":"bad_request","message":"you are not allowed to participate in this quiz","error_report_id":9005006}</pre>
 */
public class CanvasErrorResponse {

    private Long errorReportId;
    private String status;
    private List<ErrorMessage> errors;

    public void setErrorReportId(Long errorReportId) {
        this.errorReportId = errorReportId;
    }

    public Long getErrorReportId() {
        return errorReportId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
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
