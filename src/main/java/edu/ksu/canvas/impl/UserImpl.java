package edu.ksu.canvas.impl;

import edu.ksu.canvas.constants.CanvasConstants;
import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.interfaces.CourseReader;
import edu.ksu.canvas.interfaces.UserManager;
import edu.ksu.canvas.model.Course;
import edu.ksu.canvas.model.Enrollment;
import edu.ksu.canvas.model.User;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.util.CanvasURLBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by prakashreddy on 10/7/15.
 */
public class UserImpl  extends BaseImpl implements UserManager{
    private static final Logger LOG = Logger.getLogger(UserImpl.class);
    /**
     * Construct a new CanvasApi class with an OAuth token
     *
     * @param canvasBaseUrl The base URL of your canvas instance
     * @param apiVersion    The version of the Canvas API (currently 1)
     * @param oauthToken    OAuth token to use when executing API calls
     */
    public UserImpl(String canvasBaseUrl, Integer apiVersion, String oauthToken) {
        super(canvasBaseUrl, apiVersion, oauthToken);
    }

    @Override
    public Optional<User> createUser(String oauthToken, User user) throws InvalidOauthTokenException, IOException {
        Map<String,List<String>> userMap = new HashMap<String,List<String>>();
        if(user!=null) {
            //userMap.put("account_id", Collections.singletonList((URLEncoder.encode(String.valueOf(user.getId()), "utf-8"))));
            userMap.put("name",Collections.singletonList(user.getName()));
            userMap.put(URLEncoder.encode("pseudonym[unique_id]","UTF-8"),Collections.singletonList(user.getLoginId()));
        }
        String createdUrl = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "accounts/" +CanvasConstants.ACCOUNT_ID + "/users", userMap);
        LOG.debug("create URl for user creation : "+ createdUrl);
        Response response = canvasMessenger.sendToCanvas(oauthToken, createdUrl, null);
        if (response.getErrorHappened() || ( response.getResponseCode() != 200)) {
            LOG.debug("Failed to create user, error message: " + response.toString());
            return Optional.empty();
        }
        return responseParser.parseToObject(User.class,response);
    }

    @Override
    public Boolean deleteUser(String oauthToken, Integer userId) throws InvalidOauthTokenException, IOException {
        String createdUrl = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "accounts/" + CanvasConstants.ACCOUNT_ID+"/users/"+userId, null);
        LOG.debug("create URl for user creation : "+ createdUrl);
        Response response = canvasMessenger.sendToCanvas(oauthToken, createdUrl, null);
        if (response.getErrorHappened() || ( response.getResponseCode() != 200)) {
            LOG.debug("Failed to create user, error message: " + response.toString());
            return false;
        }
        return true;
    }

    @Override
    public Optional<User> updateUser(String oauthToken, User user) throws InvalidOauthTokenException, IOException {
        Map<String,List<String>> userMap = new HashMap<String,List<String>>();
        if(user!=null) {
            //userMap.put("account_id", Collections.singletonList((URLEncoder.encode(String.valueOf(user.getId()), "utf-8"))));
            userMap.put("name",Collections.singletonList(user.getName()));
            userMap.put("pseudonym[unique_id]",Collections.singletonList(user.getLoginId()));
        }
        String createdUrl = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "accounts/" + String.valueOf(user.getId()) + "/users", userMap);
        LOG.debug("create URl for user creation : "+ createdUrl);
        Response response = canvasMessenger.sendToCanvas(oauthToken, createdUrl, null);
        if (response.getErrorHappened() || ( response.getResponseCode() != 200)) {
            LOG.debug("Failed to create user, error message: " + response.toString());
            return Optional.empty();
        }
        return responseParser.parseToObject(User.class,response);
    }

    @Override
    public List<Enrollment> getUserEnrollments(String oauthToken, Integer user_Id) throws InvalidOauthTokenException, IOException {
        List<Enrollment> enrollments = new ArrayList<Enrollment>();
        String createdUrl = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "users/" + user_Id+ "/enrollments", null);
        LOG.debug("create URl for get enrollments for user : "+ createdUrl);
        Response response = canvasMessenger.getFromCanvas(oauthToken, createdUrl);
        if (response.getErrorHappened() || ( response.getResponseCode() != 200)) {
            LOG.debug("Failed to get enrollments, error message: " + response.toString());
            return Collections.emptyList();
        }
        enrollments.addAll(responseParser.parseToList(Enrollment.class,response));
        createdUrl=response.getNextLink();
        return enrollments;
    }
}
