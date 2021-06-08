package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;

import edu.ksu.canvas.interfaces.CommunicationChannelReader;
import edu.ksu.canvas.interfaces.CommunicationChannelWriter;
import edu.ksu.canvas.model.CommunicationChannel;
import edu.ksu.canvas.requestOptions.CreateCommunicationChannelOptions;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyMap;

public class CommunicationChannelImpl extends BaseImpl<CommunicationChannel, CommunicationChannelReader, CommunicationChannelWriter> implements CommunicationChannelReader, CommunicationChannelWriter {
    private static final Logger LOG = LoggerFactory.getLogger(CommunicationChannelImpl.class);

    public CommunicationChannelImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                     int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    public List<CommunicationChannel> getCommunicationChannelsForUser(String userId) throws IOException {
        LOG.debug("Retrieving communication channels for user id " + userId);
        String url = buildCanvasUrl(String.format("users/%s/communication_channels", userId), emptyMap());

        return getListFromCanvas(url);
    }

    @Override
    public Optional<CommunicationChannel> createCommunicationChannel(CreateCommunicationChannelOptions options) throws IOException {
        LOG.debug("Creating communication channel for user {}", options.getUserId());
        if(StringUtils.isAnyBlank(options.getUserId())) {
            throw new IllegalArgumentException("User ID is required to create a communication channel");
        }

        String url = buildCanvasUrl(String.format("users/%s/communication_channels", options.getUserId()), emptyMap());
        Response response = canvasMessenger.sendToCanvas(oauthToken, url, options.getOptionsMap());
        return responseParser.parseToObject(CommunicationChannel.class, response);
    }

    @Override
    public Optional<CommunicationChannel> deleteCommunicationChannel(CommunicationChannel cc) throws IOException {
        LOG.debug("Deleting communication channel {} for user {}", cc.getId(), cc.getUserId());
        if(StringUtils.isAnyBlank(cc.getUserId(), cc.getId())) {
            throw new IllegalArgumentException("User ID and communication channel ID are required to delete a communication channel");
        }

        String url = buildCanvasUrl(String.format("users/%s/communication_channels/%s", cc.getUserId(), cc.getId()), emptyMap());
        Response response = canvasMessenger.deleteFromCanvas(oauthToken, url, emptyMap());
        return responseParser.parseToObject(CommunicationChannel.class, response);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<CommunicationChannel>>() {
        }.getType();
    }

    @Override
    protected Class<CommunicationChannel> objectType() {
        return CommunicationChannel.class;
    }
}
