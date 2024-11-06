package edu.ksu.canvas.requestOptions;

import java.util.List;

public class ListDiscussionTopicsOptions extends BaseOptions {

    public enum Include {
        ALL_DATES, SECTIONS, SECTIONS_USER_COUNT, OVERRIDES
    }

    public enum OrderBy {
        POSITION, RECENT_ACTIVITY, DATE
    }

    public enum Scope {
        LOCKED, UNLOCKED, PINNED, UNPINNED
    }

    public enum FilterBy {
        ALL, UNREAD
    }

    public enum IdType {
        COURSES, GROUPS
    }

    private final String courseOrGroupId;
    private final IdType idType;

    public ListDiscussionTopicsOptions(String courseOrGroupId, IdType idType) {
        this.courseOrGroupId = courseOrGroupId;
        this.idType = idType;
    }

    public String getCourseOrGroupId() {
        return courseOrGroupId;
    }

    public String getStringIdType() {
        return idType.toString().toLowerCase();
    }

    public ListDiscussionTopicsOptions includes(List<Include> includes) {
        addEnumList("include[]", includes);
        return this;
    }

    public ListDiscussionTopicsOptions orderBy(OrderBy orderBy) {
        addSingleItem("order_by", orderBy.toString());
        return this;
    }

    public ListDiscussionTopicsOptions scope(Scope scope) {
        addSingleItem("scope", scope.toString());
        return this;
    }

    public ListDiscussionTopicsOptions onlyAnnouncements() {
        addSingleItem("only_announcements", "true");
        return this;
    }

    public ListDiscussionTopicsOptions filterBy(FilterBy filterBy) {
        addSingleItem("filter_by", filterBy.toString());
        return this;
    }

    public ListDiscussionTopicsOptions searchTerm(String search) {
        addSingleItem("search_term", search);
        return this;
    }

    public ListDiscussionTopicsOptions excludeContextModuleLockedTopics() {
        addSingleItem("exclude_context_module_locked_topics", "true");
        return this;
    }
}
