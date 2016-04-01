package edu.ksu.canvas.enums;

public enum AssignmentType {
    ONLINE_QUIZ,
    NONE,
    ON_PAPER,
    DISCUSSION_TOPIC,
    EXTERNAL_TOOL,
    ONLINE_UPLOAD,
    ONLINE_TEXT_ONLY,
    ONLINE_URL,
    MEDIA_RECORDING;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
