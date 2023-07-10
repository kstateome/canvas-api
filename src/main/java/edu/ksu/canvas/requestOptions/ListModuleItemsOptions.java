package edu.ksu.canvas.requestOptions;

import java.util.List;

/**
 * Options for listing the items in a module.
 *
 * @see <a href="https://canvas.instructure.com/doc/api/modules.html#method.context_module_items_api.index">List module items</a>
 */
public class ListModuleItemsOptions extends BaseOptions {

	public enum Include {
		CONTENT_DETAILS
	}

	private Long courseId;

	private Long moduleId;

	public ListModuleItemsOptions(Long courseId, Long moduleId) {
		this.courseId = courseId;
		this.moduleId = moduleId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public ListModuleItemsOptions includes(List<Include> includes) {
		addEnumList("include[]", includes);
		return this;
	}

	public ListModuleItemsOptions searchTerm(String searchTerm) {
		addSingleItem("search_term", searchTerm);
		return this;
	}

	public ListModuleItemsOptions studentId(String studentId) {
		addSingleItem("student_id", studentId);
		return this;
	}
}