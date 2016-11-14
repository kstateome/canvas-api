package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.interfaces.CourseReader;
import edu.ksu.canvas.interfaces.EnrollmentReader;
import edu.ksu.canvas.interfaces.EnrollmentWriter;
import edu.ksu.canvas.model.Enrollment;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.requestOptions.GetEnrollmentOptions;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class EnrollmentImpl extends BaseImpl<Enrollment, EnrollmentReader, EnrollmentWriter> implements EnrollmentReader,EnrollmentWriter {
    private static final Logger LOG = Logger.getLogger(CourseReader.class);

    public EnrollmentImpl(String canvasBaseUrl, Integer apiVersion, String oauthToken, RestClient restClient, int connectTimeout, int readTimeout, Integer paginationPageSize) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout, paginationPageSize);
    }

    @Override
    public List<Enrollment> getUserEnrollments(GetEnrollmentOptions options) throws IOException {
        LOG.debug("Retrieving user enrollments for user " + options.getObjectId());
        String url = buildCanvasUrl("users/" + options.getObjectId() + "/enrollments", options.getOptionsMap());
        return getListFromCanvas(url);
    }

    @Override
    public List<Enrollment> getSectionEnrollments(GetEnrollmentOptions options) throws IOException {
        LOG.debug("Retrieving section enrollments for section " + options.getObjectId());
        String url = buildCanvasUrl("sections/" + options.getObjectId() + "/enrollments", options.getOptionsMap());
        return getListFromCanvas(url);
    }

    @Override
    public List<Enrollment> getCourseEnrollments(GetEnrollmentOptions options) throws IOException {
        LOG.debug("Retrieving course enrollments for course " + options.getObjectId());
        String url = buildCanvasUrl("courses/" + options.getObjectId() + "/enrollments", options.getOptionsMap());
        return getListFromCanvas(url);
    }

    @Override
    public Optional<Enrollment> enrollUser(String courseId, String userId) throws InvalidOauthTokenException, IOException {
        Map<String,String> courseMap = new HashMap<>();
        courseMap.put("enrollment[user_id]", String.valueOf(userId));
        String createdUrl = buildCanvasUrl("courses/" + courseId + "/enrollments", Collections.emptyMap());
        LOG.debug("create URl for course enrollments: "+ createdUrl);
        Response response = canvasMessenger.sendToCanvas(oauthToken, createdUrl, courseMap);
        if (response.getErrorHappened() ||  response.getResponseCode() != 200) {
            LOG.debug("Failed to enroll in course, error message: " + response.toString());
            return Optional.empty();
        }
        return responseParser.parseToObject(Enrollment.class,response);
    }

    @Override
    public Optional<Enrollment> dropUser(String courseId, String enrollmentId) throws InvalidOauthTokenException, IOException {
        Map<String,String> postParams = new HashMap<>();
        postParams.put("task", "delete");
        String createdUrl = buildCanvasUrl("courses/" + courseId + "/enrollments/" + enrollmentId, Collections.emptyMap());
        LOG.debug("create URl for course enrollments: "+ createdUrl);
        Response response = canvasMessenger.deleteFromCanvas(oauthToken, createdUrl, postParams);
        if (response.getErrorHappened() ||  response.getResponseCode() != 200) {
            LOG.debug("Failed to enroll in course, error message: " + response.toString());
            return Optional.empty();
        }
        return responseParser.parseToObject(Enrollment.class, response);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<Enrollment>>(){}.getType();
    }

    @Override
    protected Class<Enrollment> objectType() {
        return Enrollment.class;
    }

}
