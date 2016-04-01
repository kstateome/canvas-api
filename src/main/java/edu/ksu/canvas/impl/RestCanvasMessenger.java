package edu.ksu.canvas.impl;


import com.google.gson.JsonObject;
import edu.ksu.canvas.entity.lti.OauthToken;
import edu.ksu.canvas.exception.OauthTokenRequiredException;
import edu.ksu.canvas.net.RestClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.interfaces.CanvasMessenger;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClientImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


import javax.validation.constraints.NotNull;

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

    //Todo: If we really need to do something as each response is received then we should provide a callback parameter
    public List<Response> getFromCanvas(@NotNull String oauthToken, @NotNull String url) throws InvalidOauthTokenException, IOException {
        LOG.debug("Sending GET request to: " + url);
        final List<Response> responses = new ArrayList<>();
        while (!StringUtils.isBlank(url)) {
            Response response = getSingleResponseFromCanvas(oauthToken, url);
            if (response.getResponseCode() == 401) {
                throw new InvalidOauthTokenException();
            } else if (response.getErrorHappened() || response.getResponseCode() != 200) {
                LOG.error("Errors retrieving responses from canvas for url:  " + url);
                return Collections.emptyList();
            }
            responses.add(response);
            url = response.getNextLink();
        }
        return responses;
    }

    @Override
    public Response sendToCanvas(@NotNull String oauthToken, @NotNull String url, @NotNull Map<String,String> parameters) throws InvalidOauthTokenException, IOException {
        final Response response = restClient.sendApiPost(oauthToken, url, parameters, connectTimeout, readTimeout);
        if (response.getResponseCode() == 401) {
            throw new InvalidOauthTokenException();
        }
        return response;
   }

    @Override
    public Response sendToJsonCanvas(String oauthToken, String url, JsonObject requestBody) throws InvalidOauthTokenException, IOException {
        final Response response = restClient.sendJsonPost(oauthToken, url, requestBody.getAsString(), connectTimeout, readTimeout);
        if (response.getResponseCode() == 401) {
            throw new InvalidOauthTokenException();
        }
        return response;
    }

    @Override
    public Response deleteFromCanvas(@NotNull String oauthToken, @NotNull String url, @NotNull Map<String,String> parameters) throws InvalidOauthTokenException, IOException {
        final Response response = restClient.sendApiDelete(oauthToken, url, parameters, connectTimeout, readTimeout);
        if (response.getResponseCode() == 401) {
            throw new InvalidOauthTokenException();
        }
        return response;
    }

    @Override
    public Response getSingleResponseFromCanvas(@NotNull String oauthToken, @NotNull String url) throws InvalidOauthTokenException, IOException {
        LOG.debug("Sending GET request to: " + url);
        return restClient.sendApiGet(oauthToken, url, connectTimeout, readTimeout);
    }

}
