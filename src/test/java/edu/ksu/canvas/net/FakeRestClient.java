package edu.ksu.canvas.net;

import edu.ksu.canvas.constants.CanvasConstants;
import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.util.JsonTestUtil;
import org.apache.log4j.Logger;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeRestClient implements RestClient {
    private static final Logger LOG = Logger.getLogger(FakeRestClient.class);
    public static int NO_TIMEOUT = 1000;
    private Map<String, Response> responseMap = new HashMap<>();

    @Override
    public Response sendApiGet(@NotNull OauthToken token, @NotNull String url, int connectTimeout, int readTimeout) throws IOException {
        LOG.debug("Sending fake GET to " + url);
        checkForTimeout(connectTimeout, readTimeout);
        return response(url);
    }

    @Override
    public Response sendJsonPost(OauthToken token, String url, String json, int connectTimeout, int readTimeout) throws IOException {
        LOG.debug("Sending fake JSON POST to " + url);
        checkForTimeout(connectTimeout, readTimeout);
        return response(url);
    }

    @Override
    public Response sendJsonPut(OauthToken token, String url, String json, int connectTimeout, int readTimeout) throws IOException {
        LOG.debug("Sending fake JSON PUT to " + url);
        checkForTimeout(connectTimeout, readTimeout);
        return response(url);
    }

    @Override
    public Response sendApiPost(OauthToken token, String url, Map<String, List<String>> postParameters, int connectTimeout, int readTimeout) throws InvalidOauthTokenException, IOException {
        LOG.debug("Sending fake POST to " + url);
        checkForTimeout(connectTimeout, readTimeout);
        return response(url);
    }

    @Override
    public Response sendApiDelete(@NotNull OauthToken token, @NotNull String url, Map<String, List<String>> deleteParameters, int connectTimeout, int readTimeout) throws InvalidOauthTokenException, IOException {
        LOG.debug("Sending fake DEL to " + url);
        checkForTimeout(connectTimeout, readTimeout);
        return response(url);
    }

    @Override
    public Response sendApiPut(@NotNull OauthToken token, @NotNull String url, Map<String, List<String>> putParameters, int connectTimeout, int readTimeout) throws InvalidOauthTokenException, IOException {
        LOG.debug("Sending fake PUT to " + url);
        checkForTimeout(connectTimeout, readTimeout);
        return response(url);
    }

    private void checkForTimeout(int connectTimeout, int readTimeout) throws IOException {
        if (connectTimeout > NO_TIMEOUT) {
            throw new IOException("Connect timeout exceeded");
        } else if (readTimeout > NO_TIMEOUT) {
            throw new IOException("Read timeout exceeded");
        }
    }

    private Response response(String url) throws IOException {
        Response response = responseMap.get(URLDecoder.decode(url, CanvasConstants.URLENCODING_TYPE));
        if (response == null) {
            throw new IOException("Url does not exist in responseMap: " + url);
        }
        return response;
    }

    public Response addSuccessResponse(String url, String fileName) {
        return addSuccessResponse(url, null, fileName);
    }

    public Response addSuccessResponse(String url, String nextLink, String fileName) {
        Response response = new Response();
        response.setContent(JsonTestUtil.loadJson("/" + fileName, FakeRestClient.class));
        response.setResponseCode(200);
        response.setNextLink(nextLink);
        LOG.info("adding url to response map: " + url);
        responseMap.put(url, response);
        return response;
    }

    public Response add401Response(String url, String fileName) {
        return add401Response(url, null, fileName);
    }

    public Response add401Response(String url, String nextUrl, String fileName) {
        Response response = new Response();
        response.setContent(JsonTestUtil.loadJson("/" + fileName, FakeRestClient.class));
        response.setResponseCode(401);
        response.setErrorHappened(true);
        response.setNextLink(nextUrl);
        responseMap.put(url, response);
        return response;
    }
}