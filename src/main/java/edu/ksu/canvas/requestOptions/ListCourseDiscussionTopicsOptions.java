package edu.ksu.canvas.requestOptions;

import java.util.List;

public class ListCourseDiscussionTopicsOptions extends BaseOptions {

	public enum Include {
		ALL_DATES, SECTIONS, SECTIONS_USER_COUNT, OVERRIDES;

		@Override
		public String toString() { return name().toLowerCase(); }
	}

	public enum OrderBy {
		POSITION, RECENT_ACTIVITY, TITLE;

		@Override
		public String toString() { return name().toLowerCase(); }
	}

	public enum Scope {
		LOCKED, UNLOCKED, PINNED, UNPINNED;

		@Override
		public String toString() { return name().toLowerCase(); }
	}

	public enum FilterBy {
		ALL, UNREAD;

		@Override
		public String toString() { return name().toLowerCase(); }
	}

	private String courseId;

	public ListCourseDiscussionTopicsOptions(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseId() {
		return courseId;
	}

	public ListCourseDiscussionTopicsOptions includes(List<Include> includes) {
		addEnumList("include[]", includes);
		return this;
	}

	public ListCourseDiscussionTopicsOptions orderBy(OrderBy orderBy) {
		addSingleItem("order_by", orderBy.toString());
		return this;
	}

	public ListCourseDiscussionTopicsOptions scope(List<Scope> scopes) {
		addEnumList("scope", scopes);
		return this;
	}

	public ListCourseDiscussionTopicsOptions onlyAnnouncements(Boolean onlyAnnouncements) {
		addSingleItem("only_announcements", onlyAnnouncements.toString());
		return this;
	}

	public ListCourseDiscussionTopicsOptions searchTerm(String searchTerm) {
		addSingleItem("search_term", searchTerm);
		return this;
	}

	public ListCourseDiscussionTopicsOptions filterBy(FilterBy filterBy) {
		addSingleItem("filter_by", filterBy.toString());
		return this;
	}

	public ListCourseDiscussionTopicsOptions excludeContextModuleLockedTopics(Boolean exclude) {
		addSingleItem("exclude_context_module_locked_topics", exclude.toString());
		return this;
	}

}
