package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.discussion.DiscussionTopic;

import java.io.IOException;
import java.util.Optional;

public interface DiscussionTopicWriter extends CanvasWriter<DiscussionTopic, DiscussionTopicWriter> {

	/**
	 * Create a Discussion Topic in Canvas. The API docs interestingly do not list any fields as required.
	 * @param courseId the ID of the course to create the discussion topic in
	 * @param discussionTopic DiscussionTopic object to create
	 * @param isAnnouncement Whether the topic is an Announcement
	 * @return The created DiscussionTopic object
	 * @throws IOException When there is an error communicating with Canvas
	 */
	Optional<DiscussionTopic> createDiscussionTopic(String courseId, DiscussionTopic discussionTopic, boolean isAnnouncement) throws IOException;

	/**
	 * Delete a specified Discussion Topic from Canvas
	 * @param courseId Course ID of the course to delete the Discussion Topic from
	 * @param discussionTopicId Canvas ID of the Discussion Topic to delete
	 * @return The deleted DiscussionTopic object as returned by the Canvas API
	 * @throws IOException When there is an error communicating with Canvas
	 */
	Optional<DiscussionTopic> deleteDiscussionTopic(String courseId, Long discussionTopicId) throws IOException;

	/**
	 * Modify a Discussion Topic object on Canvas
	 * @param courseId Course ID of the course to modify the Discussion Topic within
	 * @param discussionTopic The DiscussionTopic object whose parameters should be written to Canvas
	 * @return The modified DiscussionTopic returned by the Canvas API
	 * @throws IOException When there is an error communicating with Canvas
	 */
	Optional<DiscussionTopic> editDiscussionTopic(String courseId, DiscussionTopic discussionTopic) throws IOException;

}
