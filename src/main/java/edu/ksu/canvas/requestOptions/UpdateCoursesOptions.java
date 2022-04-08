package edu.ksu.canvas.requestOptions;

import java.util.List;

/**
 * Encapsulates the allowed input to the Canvas API UPDATE call for courses.
 */
public class UpdateCoursesOptions extends BaseOptions {

    /**
     * 	The action to take on each course
     */
    public enum EventType {

        OFFER, CONCLUDE, DELETE, UNDELETE;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    /**
     * Constructor.
     *
     * @param eventType whether to delete or conclude the course
     */
    public UpdateCoursesOptions(EventType eventType) {
        addSingleItem("event", eventType.toString());
    }

    /**
     * List of ids of courses to update. At most 500 courses may be updated in one call.
     *
     * @param courseIds IDs to update
     * @return This object to allow adding more options
     */
    public UpdateCoursesOptions courseIds(List<String> courseIds) {
        optionsMap.put("course_ids[]", courseIds);
        return this;
    }

}
