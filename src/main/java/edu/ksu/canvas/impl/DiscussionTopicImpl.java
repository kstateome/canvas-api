package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.DiscussionTopicReader;
import edu.ksu.canvas.interfaces.DiscussionTopicWriter;
import edu.ksu.canvas.model.DiscussionTopic;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.GetSingleDiscussionTopicOptions;
import edu.ksu.canvas.requestOptions.ListDiscussionTopicsOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DiscussionTopicImpl extends BaseImpl<DiscussionTopic, DiscussionTopicReader, DiscussionTopicWriter> implements DiscussionTopicReader, DiscussionTopicWriter {

    private static final Logger LOG = LoggerFactory.getLogger(DiscussionTopicImpl.class);


    public DiscussionTopicImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient, int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout, paginationPageSize, serializeNulls);
    }


    @Override
    public List<DiscussionTopic> listDiscussionTopics(ListDiscussionTopicsOptions options) throws IOException {
        LOG.debug("Getting discussion topics for course/group: {}", options.getCourseOrGroupId());
        String url = buildCanvasUrl(options.getStringIdType() + "/" + options.getCourseOrGroupId() + "/discussion_topics", options.getOptionsMap());
        return getListFromCanvas(url);
    }

    @Override
    public Optional<DiscussionTopic> getDiscussionTopic(GetSingleDiscussionTopicOptions options) throws IOException {
        LOG.debug("Getting discussion topic with id: {} for course/group: {}", options.getDiscussionId(), options.getCourseOrGroupId());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, buildCanvasUrl(options.getStringIdType() + "/" + options.getCourseOrGroupId() + "/discussion_topics/" + options.getDiscussionId(), options.getOptionsMap()));
        if (response.getErrorHappened() || response.getResponseCode() != 200) {
            return Optional.empty();
        }
        return responseParser.parseToObject(DiscussionTopic.class, response);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<DiscussionTopic>>(){}.getType();
    }

    @Override
    protected Class<DiscussionTopic> objectType() {
        return DiscussionTopic.class;
    }
}