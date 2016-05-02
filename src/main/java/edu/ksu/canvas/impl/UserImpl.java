package edu.ksu.canvas.impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.constants.CanvasConstants;
import edu.ksu.canvas.enums.CourseIncludes;
import edu.ksu.canvas.enums.EnrollmentType;
import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.exception.OauthTokenRequiredException;
import edu.ksu.canvas.interfaces.UserReader;
import edu.ksu.canvas.interfaces.UserWriter;
import edu.ksu.canvas.model.User;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class UserImpl extends BaseImpl<User, UserReader, UserWriter> implements UserReader, UserWriter{
    private static final Logger LOG = Logger.getLogger(UserImpl.class);

    private static final String API_RESULTS_PER_PAGE = "100";
    /**
     * Construct a new CanvasApi class with an OAuth token
     *
     * @param canvasBaseUrl The base URL of your canvas instance
     * @param apiVersion    The version of the Canvas API (currently 1)
     * @param oauthToken    OAuth token to use when executing API calls
     * @param restClient    The rest client implementation to use
     */
    public UserImpl(String canvasBaseUrl, Integer apiVersion, String oauthToken, RestClient restClient, int connectTimeout, int readTimeout) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout);
    }

    @Override
    public Optional<User> createUser(User user) throws InvalidOauthTokenException, IOException {
        Map<String, String> postParameters = new HashMap<>();
        postParameters.put("name", user.getName());
        postParameters.put("pseudonym[unique_id]", user.getLoginId());
        String createdUrl = buildCanvasUrl( "accounts/" +CanvasConstants.ACCOUNT_ID + "/users", Collections.emptyMap());
        LOG.debug("create URl for user creation : "+ createdUrl);
        Response response = canvasMessenger.sendToCanvas(oauthToken, createdUrl, postParameters);
        if (response.getErrorHappened() || ( response.getResponseCode() != 200)) {
            LOG.debug("Failed to create user, error message: " + response.toString());
            return Optional.empty();
        }
        return responseParser.parseToObject(User.class,response);
    }

    @Override
    public Optional<User> updateUser(User user) throws InvalidOauthTokenException, IOException {
        Map<String, String> postParameters = new HashMap<>();
        postParameters.put("name", user.getName());
        postParameters.put("pseudonym[unique_id]", user.getLoginId());
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
    public List<User> getUsersInCourse(String courseId,
            List<EnrollmentType> enrollmentTypes, Optional<Integer> enrollmentRoleId,
            List<CourseIncludes> includes)
                    throws OauthTokenRequiredException, IOException {

        Builder<String, List<String>> paramsBuilder = ImmutableMap.<String, List<String>>builder();
        paramsBuilder.put("enrollment_type[]", enrollmentTypes.stream().map(Enum::name).collect(Collectors.toList()));
        enrollmentRoleId.ifPresent(e -> paramsBuilder.put("enrollment_role_id", Collections.singletonList(e.toString())));
        paramsBuilder.put("include[]", includes.stream().map(Enum::name).collect(Collectors.toList()));

        ImmutableMap<String, List<String>> parameters = paramsBuilder.build();
        String url = buildCanvasUrl("courses/" + courseId + "/users", parameters);

        return getListFromCanvas(url);
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
