package edu.ksu.canvas.impl;

import com.google.common.reflect.TypeToken;
import edu.ksu.canvas.interfaces.DiscussionTopicReader;
import edu.ksu.canvas.interfaces.DiscussionTopicWriter;
import edu.ksu.canvas.model.discussion.DiscussionTopic;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.GetSingleDiscussionTopicOptions;
import edu.ksu.canvas.requestOptions.ListCourseDiscussionTopicsOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public class DiscussionTopicImpl extends BaseImpl<DiscussionTopic, DiscussionTopicReader, DiscussionTopicWriter> implements DiscussionTopicReader, DiscussionTopicWriter {
	private static final Logger LOG = LoggerFactory.getLogger(DiscussionTopicImpl.class);

	public DiscussionTopicImpl(String canvasBaseURL, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
							   int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
		super(canvasBaseURL, apiVersion, oauthToken, restClient, connectTimeout, readTimeout, paginationPageSize, serializeNulls);
	}

	@Override
	public List<DiscussionTopic> listCourseDiscussionTopics(ListCourseDiscussionTopicsOptions options) throws IOException {
		String url = buildCanvasUrl("courses/" + options.getCourseId() + "/discussion_topics", options.getOptionsMap());
		return getListFromCanvas(url);
	}

	@Override
	public Optional<DiscussionTopic> getSingleDiscussionTopic(GetSingleDiscussionTopicOptions options) throws IOException {
		String url = buildCanvasUrl("courses/" + options.getCourseId() + "/discussion_topics/" + options.getDiscussionTopicId(), options.getOptionsMap());
		Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
		return responseParser.parseToObject(DiscussionTopic.class, response);
	}

	@Override
	public Optional<DiscussionTopic> createDiscussionTopic(String courseId, DiscussionTopic discussionTopic) throws IOException {
		return Optional.empty();
	}

	@Override
	public Optional<DiscussionTopic> deleteDiscussionTopic(String courseId, Long discussionTopicId) throws IOException {
		return Optional.empty();
	}

	@Override
	public Optional<DiscussionTopic> editDiscussionTopic(String courseId, DiscussionTopic discussionTopic) throws IOException {
		return Optional.empty();
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
