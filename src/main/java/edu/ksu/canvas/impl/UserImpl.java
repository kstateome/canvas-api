package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;

import edu.ksu.canvas.constants.CanvasConstants;
import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.interfaces.UserReader;
import edu.ksu.canvas.interfaces.UserWriter;
import edu.ksu.canvas.model.User;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.GetUsersInCourseOptions;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserImpl extends BaseImpl<User, UserReader, UserWriter> implements UserReader, UserWriter{
    private static final Logger LOG = Logger.getLogger(UserImpl.class);

    public UserImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                    int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    public Optional<User> createUser(User user) throws InvalidOauthTokenException, IOException {
        String createdUrl = buildCanvasUrl( "accounts/" + CanvasConstants.ACCOUNT_ID + "/users", Collections.emptyMap());
        LOG.debug("create URl for user creation : "+ createdUrl);
        Response response = canvasMessenger.sendToCanvas(oauthToken, createdUrl, user.toPostMap());
        if (response.getErrorHappened() || ( response.getResponseCode() != 200)) {
            LOG.debug("Failed to create user, error message: " + response.toString());
            return Optional.empty();
        }
        return responseParser.parseToObject(User.class,response);
    }

    @Override
    public Optional<User> updateUser(User user) throws InvalidOauthTokenException, IOException {
        Map<String, List<String>> postParameters = new HashMap<>();
        postParameters.put("name", Collections.singletonList(user.getName()));
        postParameters.put("pseudonym[unique_id]", Collections.singletonList(user.getLoginId()));
        String createdUrl = buildCanvasUrl("accounts/" + String.valueOf(user.getId()) + "/users", Collections.emptyMap());
        LOG.debug("create URl for user creation : " + createdUrl);
        Response response = canvasMessenger.sendToCanvas(oauthToken, createdUrl, postParameters);
        if (response.getErrorHappened() || ( response.getResponseCode() != 200)) {
            LOG.debug("Failed to create user, error message: " + response.toString());
            return Optional.empty();
        }
        return responseParser.parseToObject(User.class,response);
    }

    @Override
    public List<User> getUsersInCourse(GetUsersInCourseOptions options) throws IOException {
        LOG.debug("Retrieving users in course " + options.getCourseId());
        String url = buildCanvasUrl("courses/" + options.getCourseId() + "/users", options.getOptionsMap());

        return getListFromCanvas(url);
    }

    @Override
    public Optional<User> showUserDetails(String userIdentifier) throws IOException{
        LOG.debug("Retrieving user details");
        String url = buildCanvasUrl("users/" + userIdentifier, Collections.emptyMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToObject(User.class, response);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<User>>(){}.getType();
    }

    @Override
    protected Class<User> objectType() {
        return User.class;
    }

}
