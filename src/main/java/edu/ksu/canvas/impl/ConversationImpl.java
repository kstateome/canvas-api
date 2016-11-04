package edu.ksu.canvas.impl;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.google.gson.reflect.TypeToken;

import edu.ksu.canvas.interfaces.ConversationReader;
import edu.ksu.canvas.interfaces.ConversationWriter;
import edu.ksu.canvas.model.Conversation;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.requestOptions.GetSingleConversationOptions;

public class ConversationImpl extends BaseImpl<Conversation, ConversationReader, ConversationWriter> implements ConversationReader {
    private static final Logger LOG = Logger.getLogger(ConversationImpl.class);

    public ConversationImpl(String canvasBaseUrl, Integer apiVersion, String oauthToken, RestClient restClient,
            int connectTimeout, int readTimeout, Integer paginationPageSize) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout,readTimeout, paginationPageSize);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<Conversation>>(){}.getType();
    }

    @Override
    protected Class<Conversation> objectType() {
        return Conversation.class;
    }

    @Override
    public Optional<Conversation> getSingleConversation(GetSingleConversationOptions options) throws IOException {
        LOG.debug("getting single conversation: " + options.getConversationId());
        String url = buildCanvasUrl("conversations/" + options.getConversationId(), Collections.emptyMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToObject(Conversation.class, response);
    }

}
