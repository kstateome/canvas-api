package edu.ksu.canvas.requestOptions;

/**
 * Encapsulates the allowed input to the Canvas API DELETE call for courses.
 */
public class DeleteCourseOptions extends BaseOptions {

    /**
     * The event: whether we are deleting a course permanently, or marking it
     * as concluded.
     */
    public enum EventType {

        DELETE,
        CONCLUDE;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    private final String courseId;

    private final EventType eventType;

    /**
     * Constructor.
     *
     * @param courseId identifies the course to delete
     * @param eventType whether to delete or conclude the course
     */
    public DeleteCourseOptions(String courseId, EventType eventType) {
        this.courseId = courseId;
        this.eventType = eventType;
        addSingleItem("event", eventType.toString());
    }

    public String getCourseId() {
        return courseId;
    }

    public EventType getEventType() {
        return eventType;
    }
}
