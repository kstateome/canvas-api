package edu.ksu.canvas.enums;

public enum SectionIncludes {
    STUDENTS,
    AVATAR_URL,
    ENROLLMENTS,
    TOTAL_STUDENTS,
    PASSBACK_STATUS;

    public String toString() {
        return name().toLowerCase();
    }
}
