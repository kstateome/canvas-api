package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.DiscussionTopic;
import edu.ksu.canvas.requestOptions.GetSingleDiscussionTopicOptions;
import edu.ksu.canvas.requestOptions.ListDiscussionTopicsOptions;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface DiscussionTopicReader extends CanvasReader<DiscussionTopic, DiscussionTopicReader> {

    List<DiscussionTopic> listDiscussionTopics(ListDiscussionTopicsOptions options) throws IOException;

    Optional<DiscussionTopic> getDiscussionTopic(GetSingleDiscussionTopicOptions options) throws IOException;
}