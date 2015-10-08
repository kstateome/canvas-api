package edu.ksu.canvas.impl;

import com.fasterxml.jackson.databind.deser.Deserializers;
import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.interfaces.CourseReader;
import edu.ksu.canvas.interfaces.EnrollmentsReader;
import edu.ksu.canvas.interfaces.EnrollmentsWriter;
import edu.ksu.canvas.model.Enrollment;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.util.CanvasURLBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by prv on 10/8/15.
 */
public class EnrollmentsImpl extends BaseImpl implements EnrollmentsReader,EnrollmentsWriter {
    private static final Logger LOG = Logger.getLogger(CourseReader.class);
    /**
     * Construct a new CanvasApi class with an OAuth token
     *
     * @param canvasBaseUrl The base URL of your canvas instance
     * @param apiVersion    The version of the Canvas API (currently 1)
     * @param oauthToken    OAuth token to use when executing API calls
     */
    public EnrollmentsImpl(String canvasBaseUrl, Integer apiVersion, String oauthToken) {
        super(canvasBaseUrl, apiVersion, oauthToken);
    }

    @Override
    public List<Enrollment> getUserEnrollments(String oauthToken, Integer user_Id) throws InvalidOauthTokenException, IOException {
        List<Enrollment> enrollments = new ArrayList<Enrollment>();
        String createdUrl = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "users/" + user_Id+ "/enrollments", null);
        LOG.debug("create URl for get enrollments for user : "+ createdUrl);
        Response response = canvasMessenger.getFromCanvas(oauthToken, createdUrl);
        while(StringUtils.isNotBlank(createdUrl)) {
            if (response.getErrorHappened() || (response.getResponseCode() != 200)) {
                LOG.debug("Failed to get enrollments, error message: " + response.toString());
                return Collections.emptyList();
            }
            enrollments.addAll(responseParser.parseToList(Enrollment.class, response));
            createdUrl = response.getNextLink();
        }
        return enrollments;
    }
    @Override
    public Optional<Enrollment> enrollUser(String oauthToken, Integer course_Id, Integer userId) throws InvalidOauthTokenException, IOException {
        Map<String,List<String>> courseMap = new HashMap<String,List<String>>();
        courseMap.put(URLEncoder.encode("enrollment[user_id]", "UTF-8"), Collections.singletonList(String.valueOf(userId)));
        String createdUrl = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "courses/" + course_Id + "/enrollments", courseMap);
        LOG.debug("create URl for course enrollments: "+ createdUrl);
        Response response = canvasMessenger.sendToCanvas(oauthToken, createdUrl, null);
        if (response.getErrorHappened() ||  response.getResponseCode() != 200) {
            LOG.debug("Failed to enroll in course, error message: " + response.toString());
            return Optional.empty();
        }
        return responseParser.parseToObject(Enrollment.class,response);
    }

    @Override
    public Optional<Enrollment> dropUser(String oauthToken, Integer course_id, Long enrollment_id) throws InvalidOauthTokenException, IOException {
        Map<String,List<String>> courseMap = new HashMap<String,List<String>>();
        courseMap.put(URLEncoder.encode("task","UTF-8"),Collections.singletonList(URLEncoder.encode("delete","UTF-8")));
        String createdUrl = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "courses/" + course_id+ "/enrollments/"+enrollment_id, courseMap);
        LOG.debug("create URl for course enrollments: "+ createdUrl);
        Response response = canvasMessenger.deleteFromCanvas(oauthToken, createdUrl, null);
        if (response.getErrorHappened() ||  response.getResponseCode() != 200) {
            LOG.debug("Failed to enroll in course, error message: " + response.toString());
            return Optional.empty();
        }
        return responseParser.parseToObject(Enrollment.class,response);
    }
}
