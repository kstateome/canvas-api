package edu.ksu.canvas.impl;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.ksu.canvas.interfaces.*;
import edu.ksu.canvas.model.BaseCanvasModel;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Base class for accessing the Canvas API
 */
public abstract class BaseImpl<T> implements CanvasReader<T> {
    private static Logger LOG = Logger.getLogger(BaseImpl.class);

    protected String canvasBaseUrl;
    protected Integer apiVersion;
    protected String oauthToken;
    protected ResponseParser responseParser;
    protected CanvasMessenger canvasMessenger;
    protected Consumer<List<T>> responseCallback;

    /**
     * Construct a new CanvasApi class with an OAuth token
     * @param canvasBaseUrl The base URL of your canvas instance
     * @param apiVersion The version of the Canvas API (currently 1)
     * @param oauthToken OAuth token to use when executing API calls
     */
    public BaseImpl(String canvasBaseUrl, Integer apiVersion, String oauthToken, RestClient restClient) {
        this.canvasBaseUrl = canvasBaseUrl;
        this.apiVersion = apiVersion;
        this.oauthToken = oauthToken;
        responseParser = new GsonResponseParser();
        canvasMessenger = new RestCanvasMessenger(1000, 1000, restClient);
    }

    protected Optional<T> getFromCanvas(String url) throws IOException {
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        if (response.getErrorHappened() || response.getResponseCode() != 200) {
            LOG.warn("Error " + response.getResponseCode() + "on GET from url " + url);
            throw new IOException("Error accessing url " + url);
        }
        return parseObjectResponse(response);
    }

    protected List<T> getListFromCanvas(String url) throws IOException {
        Consumer<Response> consumer = response -> responseCallback.accept(parseListResponse(response));
        List<Response> responses = canvasMessenger.getFromCanvas(oauthToken, url, consumer);
        responseCallback = null;
        return parseListOfResponses(responses);
    }

    protected Gson getDefaultGsonParser() {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    @Override
    public CanvasReader withCallBack(Consumer<List<T>> responseReceivedCallBack) {
        responseCallback = responseReceivedCallBack;
        return this;
    }

    protected abstract List<T> parseListResponse(Response response);

    protected abstract Optional<T> parseObjectResponse(Response response);

    protected List<T> parseListOfResponses(List<Response> responses) {
        return responses
                .stream()
                .map(this::parseListResponse)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
