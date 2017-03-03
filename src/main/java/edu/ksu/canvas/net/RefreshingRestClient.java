package edu.ksu.canvas.net;

import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.oauth.OauthToken;
import org.apache.log4j.Logger;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This class wraps SimpleRestClient. It provides functionality to
 * catch OAuth errors that may be the result of an expired access
 * token that needs to be refreshed using the user's refresh token.
 * After refreshing the token it retries the request once. If it
 * still fails, the error is thrown up to the caller.
 */
public class RefreshingRestClient implements RestClient {
    private static final Logger LOG = Logger.getLogger(RefreshingRestClient.class);
    private RestClient restClient = new SimpleRestClient();

    @Override
    public Response sendApiGet(@NotNull OauthToken token, @NotNull String url, int connectTimeout, int readTimeout) throws IOException {
        try {
            return restClient.sendApiGet(token, url, connectTimeout, readTimeout);
        } catch (InvalidOauthTokenException e) {
            LOG.debug("Caught invalidOauthToken from " + url);
            token.refresh();
            return restClient.sendApiGet(token, url, connectTimeout, readTimeout);
        }
    }

    @Override
    public Response sendJsonPost(@NotNull OauthToken token, @NotNull String url, String json, int connectTimeout, int readTimeout) throws IOException {
        try {
            return restClient.sendJsonPost(token, url, json, connectTimeout, readTimeout);
        } catch (InvalidOauthTokenException e) {
            LOG.debug("Caught invalidOauthToken from " + url);
            token.refresh();
            return restClient.sendJsonPost(token, url, json, connectTimeout, readTimeout);
        }
    }

    @Override
    public Response sendJsonPut(@NotNull OauthToken token, @NotNull String url, String json, int connectTimeout, int readTimeout) throws IOException {
        try {
            return restClient.sendJsonPut(token, url, json, connectTimeout, readTimeout);
        } catch (InvalidOauthTokenException e) {
            LOG.debug("Caught invalidOauthToken from " + url);
            token.refresh();
            return restClient.sendJsonPut(token, url, json, connectTimeout, readTimeout);
        }
    }

    @Override
    public Response sendApiPost(@NotNull OauthToken token, @NotNull String url, Map<String, List<String>> postParameters, int connectTimeout, int readTimeout) throws InvalidOauthTokenException, IOException {
        try {
            return restClient.sendApiPost(token, url, postParameters, connectTimeout, readTimeout);
        } catch (InvalidOauthTokenException e) {
            LOG.debug("Caught invalidOauthToken from " + url);
            token.refresh();
            return restClient.sendApiPost(token, url, postParameters, connectTimeout, readTimeout);
        }
    }

    @Override
    public Response sendApiDelete(@NotNull OauthToken token, @NotNull String url, Map<String, List<String>> deleteParameters, int connectTimeout, int readTimeout) throws InvalidOauthTokenException, IOException {
        try {
            return restClient.sendApiDelete(token, url, deleteParameters, connectTimeout, readTimeout);
        } catch (InvalidOauthTokenException e) {
            LOG.debug("Caught invalidOauthToken from " + url);
            token.refresh();
            return restClient.sendApiDelete(token, url, deleteParameters, connectTimeout, readTimeout);
        }
    }

    @Override
    public Response sendApiPut(@NotNull OauthToken token, @NotNull String url, Map<String, List<String>> putParameters, int connectTimeout, int readTimeout) throws InvalidOauthTokenException, IOException {
        try {
            return restClient.sendApiPut(token, url, putParameters, connectTimeout, readTimeout);
        } catch (InvalidOauthTokenException e) {
            LOG.debug("Caught invalidOauthToken from " + url);
            token.refresh();
            return restClient.sendApiPut(token, url, putParameters, connectTimeout, readTimeout);
        }
    }
}
