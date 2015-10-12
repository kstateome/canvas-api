package edu.ksu.canvas.impl;

import edu.ksu.canvas.constants.CanvasConstants;
import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.interfaces.UserReader;
import edu.ksu.canvas.interfaces.UserWriter;
import edu.ksu.canvas.model.User;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.util.CanvasURLBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.*;

public class UserImpl  extends BaseImpl implements UserReader,UserWriter{
    private static final Logger LOG = Logger.getLogger(UserImpl.class);
    /**
     * Construct a new CanvasApi class with an OAuth token
     *
     * @param canvasBaseUrl The base URL of your canvas instance
     * @param apiVersion    The version of the Canvas API (currently 1)
     * @param oauthToken    OAuth token to use when executing API calls
     * @param restClient    The rest client implementation to use
     */
    public UserImpl(String canvasBaseUrl, Integer apiVersion, String oauthToken, RestClient restClient) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient);
    }

    @Override
    public Optional<User> createUser(String oauthToken, User user) throws InvalidOauthTokenException, IOException {
        Map<String, String> postParameters = new HashMap<>();
        postParameters.put("name", user.getName());
        postParameters.put("pseudonym[unique_id]", user.getLoginId());
        String createdUrl = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "accounts/" +CanvasConstants.ACCOUNT_ID + "/users", Collections.emptyMap());
        LOG.debug("create URl for user creation : "+ createdUrl);
        Response response = canvasMessenger.sendToCanvas(oauthToken, createdUrl, postParameters);
        if (response.getErrorHappened() || ( response.getResponseCode() != 200)) {
            LOG.debug("Failed to create user, error message: " + response.toString());
            return Optional.empty();
        }
        return responseParser.parseToObject(User.class,response);
    }



    @Override
    public Optional<User> updateUser(String oauthToken, User user) throws InvalidOauthTokenException, IOException {
        Map<String, String> postParameters = new HashMap<>();
        postParameters.put("name", user.getName());
        postParameters.put("pseudonym[unique_id]", user.getLoginId());
        String createdUrl = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "accounts/" + String.valueOf(user.getId()) + "/users", Collections.emptyMap());
        LOG.debug("create URl for user creation : " + createdUrl);
        Response response = canvasMessenger.sendToCanvas(oauthToken, createdUrl, postParameters);
        if (response.getErrorHappened() || ( response.getResponseCode() != 200)) {
            LOG.debug("Failed to create user, error message: " + response.toString());
            return Optional.empty();
        }
        return responseParser.parseToObject(User.class,response);
    }


}
