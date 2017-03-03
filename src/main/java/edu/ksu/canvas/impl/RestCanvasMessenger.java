package edu.ksu.canvas.impl;


import com.google.gson.JsonObject;

import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.interfaces.CanvasMessenger;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/*
 * This class uses the canvas rest api to communicate with canvas
 */
public class RestCanvasMessenger implements CanvasMessenger {
    private static final Logger LOG = Logger.getLogger(RestCanvasMessenger.class);
    private RestClient restClient;
    private int connectTimeout;
    private int readTimeout;
    public RestCanvasMessenger(int connectTimeout, int readTimeout, RestClient restClient) {
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        this.restClient = restClient;
    }

    public List<Response> getFromCanvas(@NotNull OauthToken oauthToken, @NotNull String url) throws InvalidOauthTokenException, IOException {
        return getFromCanvas(oauthToken, url, null);
    }


    public List<Response> getFromCanvas(@NotNull OauthToken oauthToken, @NotNull String url, Consumer<Response> callback) throws InvalidOauthTokenException, IOException {
        LOG.debug("Sending GET request to: " + url);
        final List<Response> responses = new ArrayList<>();
        while (StringUtils.isNotBlank(url)) {
            Response response = getSingleResponseFromCanvas(oauthToken, url);
            if (response.getErrorHappened() || response.getResponseCode() != 200) {
                LOG.error("Errors retrieving responses from canvas for url:  " + url);
                return Collections.emptyList();
            }
            responses.add(response);
            url = response.getNextLink();
            if (callback != null) {
                callback.accept(response);
            }
        }
        return responses;
    }

    @Override
    public Response sendToCanvas(@NotNull OauthToken oauthToken, @NotNull String url, @NotNull Map<String, List<String>> parameters) throws InvalidOauthTokenException, IOException {
        return restClient.sendApiPost(oauthToken, url, parameters, connectTimeout, readTimeout);
   }

    @Override
    public Response sendJsonPostToCanvas(OauthToken oauthToken, String url, JsonObject requestBody) throws InvalidOauthTokenException, IOException {
        return restClient.sendJsonPost(oauthToken, url, requestBody.toString(), connectTimeout, readTimeout);
    }

    @Override
    public Response sendJsonPutToCanvas(OauthToken oauthToken, String url, JsonObject requestBody) throws InvalidOauthTokenException, IOException {
        return restClient.sendJsonPut(oauthToken, url, requestBody.toString(), connectTimeout, readTimeout);
    }

    @Override
    public Response deleteFromCanvas(@NotNull OauthToken oauthToken, @NotNull String url, @NotNull Map<String, List<String>> parameters) throws InvalidOauthTokenException, IOException {
        return restClient.sendApiDelete(oauthToken, url, parameters, connectTimeout, readTimeout);
    }

    @Override
    public Response putToCanvas(@NotNull OauthToken oauthToken, @NotNull String url, @NotNull Map<String, List<String>> parameters) throws InvalidOauthTokenException, IOException {
        return restClient.sendApiPut(oauthToken, url, parameters, connectTimeout, readTimeout);
    }

    @Override
    public Response getSingleResponseFromCanvas(@NotNull OauthToken oauthToken, @NotNull String url) throws InvalidOauthTokenException, IOException {
        LOG.debug("Sending GET request to: " + url);
        return restClient.sendApiGet(oauthToken, url, connectTimeout, readTimeout);
    }

}
