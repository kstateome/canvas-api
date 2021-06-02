package edu.ksu.canvas.requestOptions;

import java.util.List;

/**
 * Options for listing the modules in a course.
 *
 * @see <a href="https://canvas.instructure.com/doc/api/modules.html#method.context_modules_api.index">List modules</a>
 */
public class ListModulesOptions extends BaseOptions {

    public enum Include {
        ITEMS,
        CONTENT_DETAILS
    }

    private Long courseId;

    public ListModulesOptions(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public ListModulesOptions includes(List<Include> includes) {
        addEnumList("include[]", includes);
        return this;
    }

    public ListModulesOptions searchTerm(String searchTerm) {
        addSingleItem("search_term", searchTerm);
        return this;
    }

    public ListModulesOptions studentId(String studentId) {
        addSingleItem("student_id", studentId);
        return this;
    }

}
