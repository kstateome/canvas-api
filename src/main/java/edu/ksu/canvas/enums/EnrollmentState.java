package edu.ksu.canvas.enums;

public enum EnrollmentState {

    ACTIVE, INVITED, CREATION_PENDING, DELETED, REJECTED, COMPLETED, INACTIVE;

    public String toString() {
        return name().toLowerCase();
    }
}
