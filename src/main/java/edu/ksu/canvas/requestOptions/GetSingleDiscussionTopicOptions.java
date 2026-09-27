package edu.ksu.canvas.requestOptions;

import java.util.List;

public class GetSingleDiscussionTopicOptions extends BaseOptions {

	private String courseId;
	private Long discussionTopicId;

	public enum Include {
		ALL_DATES, SECTIONS, SECTIONS_USER_COUNT, OVERRIDES;

		@Override
		public String toString() { return name().toLowerCase(); }
	}

	public GetSingleDiscussionTopicOptions(String courseId, Long discussionTopicId) {
		if (courseId == null || discussionTopicId == null) {
			throw new IllegalArgumentException("Course and discussion topic ids are required");
		}
		this.courseId = courseId;
		this.discussionTopicId = discussionTopicId;
	}

	public String getCourseId() {
		return courseId;
	}

	public Long getDiscussionTopicId() {
		return discussionTopicId;
	}

	public GetSingleDiscussionTopicOptions includes(List<Include> includes) {
		addEnumList("include[]", includes);
		return this;
	}

}
