package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.discussion.DiscussionTopic;
import edu.ksu.canvas.requestOptions.GetSingleDiscussionTopicOptions;
import edu.ksu.canvas.requestOptions.ListCourseDiscussionTopicsOptions;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface DiscussionTopicReader extends CanvasReader<DiscussionTopic, DiscussionTopicReader> {

    /**
     * Retireve a specific Discussion Topic from Canvas by its Canvas ID number
     * @param options Options class containing required and optional parameters for this API call
     * @return The DiscussionTopic returned by Canvas or an empty Optional
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<DiscussionTopic> getSingleDiscussionTopic(GetSingleDiscussionTopicOptions options) throws IOException;

    /**
     * Retrieve a list of Discussion Topics associated with a course
     * @param options Options class containing required and optional parameters for this API call
     * @return List of DiscussionTopics in the requested course
     * @throws IOException When there is an error communicating with Canvas
     */
    List<DiscussionTopic> listCourseDiscussionTopics(ListCourseDiscussionTopicsOptions options) throws IOException;

}
