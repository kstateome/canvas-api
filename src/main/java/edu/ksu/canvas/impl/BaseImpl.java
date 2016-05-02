package edu.ksu.canvas.impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.ksu.canvas.constants.CanvasConstants;
import edu.ksu.canvas.interfaces.CanvasMessenger;
import edu.ksu.canvas.interfaces.CanvasReader;
import edu.ksu.canvas.interfaces.CanvasWriter;
import edu.ksu.canvas.interfaces.ResponseParser;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.util.CanvasURLBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Base class for accessing the Canvas API
 */


public abstract class BaseImpl<T, READERTYPE extends CanvasReader, WRITERTYPE extends CanvasWriter> implements CanvasReader<T, READERTYPE>, CanvasWriter<T,WRITERTYPE>{
    private static final Logger LOG = Logger.getLogger(BaseImpl.class);

    protected String canvasBaseUrl;
    protected Integer apiVersion;
    protected String oauthToken;
    protected ResponseParser responseParser;
    protected CanvasMessenger canvasMessenger;
    protected Consumer<List<T>> responseCallback;
    protected String masqueradeAs;
    protected String masqueradeType;

    /**
     * Construct a new CanvasApi class with an OAuth token
     * @param canvasBaseUrl The base URL of your canvas instance
     * @param apiVersion The version of the Canvas API (currently 1)
     * @param oauthToken OAuth token to use when executing API calls
     */
    public BaseImpl(String canvasBaseUrl, Integer apiVersion, String oauthToken, RestClient restClient, int connectTimeout, int readTimeout) {
        this.canvasBaseUrl = canvasBaseUrl;
        this.apiVersion = apiVersion;
        this.oauthToken = oauthToken;
        responseParser = new GsonResponseParser();
        canvasMessenger = new RestCanvasMessenger(connectTimeout, readTimeout, restClient);
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

    @Override
    public READERTYPE readAsCanvasUser(String masqueradeAs) {
        return (READERTYPE) readAsUser(masqueradeAs, CanvasConstants.MASQUERADE_CANVAS_USER);
    }

    @Override
    public READERTYPE readAsSisUser(String masqueradeAs) {
        return (READERTYPE) readAsUser(masqueradeAs, CanvasConstants.MASQUERADE_SIS_USER);
    }

    private READERTYPE readAsUser(String masqueradeAs, String masqueradeType){
        this.masqueradeAs = masqueradeAs;
        this.masqueradeType = masqueradeType;
        return (READERTYPE) this;
    }

    @Override
    public WRITERTYPE writeAsCanvasUser(String masqueradeAs) {
        return (WRITERTYPE) writeAsUser(masqueradeAs, CanvasConstants.MASQUERADE_CANVAS_USER);
    }

    @Override
    public WRITERTYPE writeAsSisUser(String masqueradeAs) {
        return (WRITERTYPE) writeAsUser(masqueradeAs, CanvasConstants.MASQUERADE_SIS_USER);
    }

    private WRITERTYPE writeAsUser(String masqueradeAs, String masqueradeType){
        this.masqueradeAs = masqueradeAs;
        this.masqueradeType = masqueradeType;
        return (WRITERTYPE) this;
    }

    protected String buildCanvasUrl(String canvasMethod, Map<String, List<String>> parameters) {
        Map<String, List<String>> nonEmptyParams = stripEmptyParams(parameters);

        String finalUrl = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion,
                canvasMethod, nonEmptyParams);
        String separator = nonEmptyParams.isEmpty()? "?" : "&";
        if(!StringUtils.isEmpty(masqueradeAs) && !StringUtils.isEmpty(masqueradeType)){
            finalUrl += separator + "as_user_id=" + masqueradeType + ":" + masqueradeAs;
            masqueradeAs = null;
            masqueradeType = null;
        }
        return finalUrl;
    }

    /**
     * Returns URL parameters after removing any that have an empty list in them.
     * For optional list arguments, we pass an empty list to the API method. This generates
     * an entry like includes[]= with no value. This function strips them out so we only
     * pass parameters to Canvas that have a value to send along.
     */
    private Map<String, List<String>> stripEmptyParams(Map<String, List<String>> parameters) {
        Builder<String, List<String>> paramsBuilder = ImmutableMap.<String, List<String>>builder();
        for (Map.Entry<String, List<String>> entry : parameters.entrySet()) {
            if(entry.getValue() != null && !entry.getValue().isEmpty()) {
                paramsBuilder.put(entry.getKey(), entry.getValue());
            }
        }
        return paramsBuilder.build();
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
