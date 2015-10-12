package edu.ksu.canvas.net;

import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.util.JsonTestUtil;
import org.apache.log4j.Logger;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FakeRestClient implements RestClient {
    private static final Logger LOG = Logger.getLogger(FakeRestClient.class);
    public static int NO_TIMEOUT = 1000;
    public static int TIMEOUT = 2000;
    private Map<String, Response> responseMap = new HashMap<>();

    @Override
    public Response sendApiGet(@NotNull String token, @NotNull String url, int connectTimeout, int readTimeout) throws IOException {
        checkForTimeout(connectTimeout, readTimeout);
        return response(url);
    }

    @Override
    public Response sendJsonPost(String token, String url, String json, int connectTimeout, int readTimeout) throws IOException {
        checkForTimeout(connectTimeout, readTimeout);
        return response(url);
    }

    @Override
    public Response sendApiPost(String token, String url, Map<String, String> postParameters, int connectTimeout, int readTimeout) throws InvalidOauthTokenException, IOException {
        checkForTimeout(connectTimeout, readTimeout);
        return response(url);
    }

    @Override
    public Response sendApiDelete(@NotNull String token, @NotNull String url, Map<String, String> deleteParameters, int connectTimeout, int readTimeout) throws InvalidOauthTokenException, IOException {
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
        Response response = responseMap.get(url);
        if (response == null) {
            throw new IOException("Url does not exist in responseMap!");
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