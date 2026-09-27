package edu.ksu.canvas.requestOptions;

import java.util.List;

public class GetSingleDiscussionTopicOptions extends BaseOptions {

    public enum Include {
        ALL_DATES, SECTIONS, SECTIONS_USER_COUNT, OVERRIDES
    }

    public enum IdType {
        COURSES, GROUPS
    }

    private final String courseOrGroupId;
    private final String discussionId;
    private IdType idType;

    public GetSingleDiscussionTopicOptions(String courseOrGroupId, String discussionId, IdType idType) {
        this.courseOrGroupId = courseOrGroupId;
        this.discussionId = discussionId;
        this.idType = idType;
    }

    public String getCourseOrGroupId() {
        return courseOrGroupId;
    }

    public String getDiscussionId() {
        return discussionId;
    }

    public String getStringIdType() {
        return idType.toString().toLowerCase();
    }

    public GetSingleDiscussionTopicOptions includes(List<Include> includes) {
        addEnumList("include[]", includes);
        return this;
    }
}