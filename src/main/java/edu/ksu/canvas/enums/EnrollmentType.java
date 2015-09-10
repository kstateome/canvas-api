package edu.ksu.canvas.enums;

public enum EnrollmentType {
    STUDENT("StudentEnrollment"),
    TEACHER("TeacherEnrollment"),
    TA("TaEnrollment"),
    DESIGNER_ENROLL("DesignerEnrollment"),
    OBSERVER("ObserverEnrollment");

    private String canvasValue;

    EnrollmentType(String canvasValue) {
        this.canvasValue = canvasValue;
    }

    public String canvasValue() {
        return canvasValue;
    }

}
