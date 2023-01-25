package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.discussion.DiscussionTopic;

import java.io.IOException;
import java.util.Optional;

public interface DiscussionTopicWriter extends CanvasWriter<DiscussionTopic, DiscussionTopicWriter> {

	Optional<DiscussionTopic> createDiscussionTopic(String courseId, DiscussionTopic discussionTopic) throws IOException;

	Optional<DiscussionTopic> deleteDiscussionTopic(String courseId, Long discussionTopicId) throws IOException;

	Optional<DiscussionTopic> editDiscussionTopic(String courseId, DiscussionTopic discussionTopic) throws IOException;

}
