package edu.ksu.canvas.enums;

public enum SectionIncludes {
    STUDENTS,
    AVATAR_URL,
    ENROLLMENTS,
    TOTAL_STUDENTS,
    PASSBACK_STATUS;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
