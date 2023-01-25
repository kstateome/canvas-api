package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.discussion.DiscussionTopic;
import edu.ksu.canvas.requestOptions.GetSingleDiscussionTopicOptions;
import edu.ksu.canvas.requestOptions.ListCourseDiscussionTopicsOptions;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface DiscussionTopicReader extends CanvasReader<DiscussionTopic, DiscussionTopicReader> {

	Optional<DiscussionTopic> getSingleDiscussionTopic(GetSingleDiscussionTopicOptions options) throws IOException;

	List<DiscussionTopic> listCourseDiscussionTopics(ListCourseDiscussionTopicsOptions options) throws IOException;

}
