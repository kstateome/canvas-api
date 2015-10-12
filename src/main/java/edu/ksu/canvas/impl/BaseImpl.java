package edu.ksu.canvas.impl;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.ksu.canvas.interfaces.CanvasMessenger;
import edu.ksu.canvas.interfaces.ResponseParser;
import edu.ksu.canvas.net.RestClient;

/**
 * Base class for accessing the Canvas API
 */
public class BaseImpl {

    protected String canvasBaseUrl;
    protected Integer apiVersion;
    protected String oauthToken;
    protected ResponseParser responseParser;
    protected CanvasMessenger canvasMessenger;

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

    protected Gson getDefaultGsonParser() {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }
}
