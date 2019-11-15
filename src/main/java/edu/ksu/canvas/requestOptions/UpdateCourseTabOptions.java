package edu.ksu.canvas.requestOptions;

/**
 * Options for updating a tab for a given course.
 */
public class UpdateCourseTabOptions extends BaseOptions {

    private String courseId;

    private String tabId;

    private int position;

    private boolean hidden;

    /**
     * Constructor.
     *
     * @param courseId identifies the course
     * @param tabId identifies the tab within the course
     */
    public UpdateCourseTabOptions(String courseId, String tabId) {
        this.courseId = courseId;
        this.tabId = tabId;
    }

    /**
     * Sets the position (1-based).
     * @param position The new position of the tab (1-based)
     * @return This object to continue adding options
     */
    public UpdateCourseTabOptions position(int position) {
        addSingleItem("position", Integer.toString(position));
        return this;
    }

    public UpdateCourseTabOptions hidden(boolean hidden) {
        addSingleItem("hidden", Boolean.toString(hidden));
        return this;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getTabId() {
        return tabId;
    }
}
