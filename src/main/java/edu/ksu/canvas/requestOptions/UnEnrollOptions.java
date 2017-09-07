package edu.ksu.canvas.requestOptions;


/**
 *
 *  UnEnrollOptions denote the canvas Enrollment dropUser's task request parameter supported values. See
 *  https://canvas.instructure.com/doc/api/enrollments.html#method.enrollments_api.destroy
 *  for a discussion of these.
 */
public enum UnEnrollOptions {
    DELETE("delete"),
    CONCLUDE("conclude"),
    INACTIVATE("inactivate"),
    DEACTIVATE("deactivate");

    private String canvasValue;

    UnEnrollOptions(String canvasValue) {this.canvasValue = canvasValue;}

    @Override
    public String toString() {
        return canvasValue;
    }
}
