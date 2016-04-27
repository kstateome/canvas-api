package edu.ksu.canvas.impl;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.ksu.canvas.interfaces.CanvasMessenger;
import edu.ksu.canvas.interfaces.CanvasReader;
import edu.ksu.canvas.interfaces.CanvasWriter;
import edu.ksu.canvas.interfaces.ResponseParser;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Base class for accessing the Canvas API
 */


public abstract class BaseImpl<T, READERTYPE extends CanvasReader> implements CanvasReader<T, READERTYPE>, CanvasWriter {
    private static final Logger LOG = Logger.getLogger(BaseImpl.class);

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
        return responseParser.parseToObject(objectType(), response);
    }

    protected List<T> getListFromCanvas(String url) throws IOException {
        Consumer<Response> consumer = null;
        if (responseCallback != null) {
            consumer = response -> responseCallback.accept(responseParser.parseToList(listType(), response));
        }
        List<Response> responses = canvasMessenger.getFromCanvas(oauthToken, url, consumer);
        responseCallback = null;
        return parseListOfResponses(responses);
    }

    protected Gson getDefaultGsonParser() {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    @Override
    public READERTYPE withCallback(Consumer<List<T>> responseReceivedCallBack) {
        responseCallback = responseReceivedCallBack;
        return (READERTYPE) this;
    }

    // Declaring this as a separate method fixes some of Java's type inference problems when using it in parseListOfResponses(..)
    private List<T> parseListResponse(Response response) {
        return responseParser.parseToList(listType(), response);
    }

    /*
     * Subclasses should return the type of a list that will be parsed by gson when using call that returns lists.
     * For example, CourseReaderImpl returns 'TypeToken<List<Course>>(){}.getType();'
     */
    protected abstract Type listType();

    /*
     * Subclasses should return the type of model that will be parsed by gson when using a call that returns a single
     * object. For example, CourseReaderImpl returns Course.class.
     */
    protected abstract Class<T> objectType();

    protected List<T> parseListOfResponses(List<Response> responses) {
        return responses
                .stream()
                .map(this::parseListResponse)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
