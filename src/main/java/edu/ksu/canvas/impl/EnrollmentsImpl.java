package edu.ksu.canvas.impl;

import com.google.common.collect.ImmutableMap;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.enums.EnrollmentType;
import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.interfaces.CourseReader;
import edu.ksu.canvas.interfaces.EnrollmentsReader;
import edu.ksu.canvas.interfaces.EnrollmentsWriter;
import edu.ksu.canvas.model.Enrollment;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.util.CanvasURLBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class EnrollmentsImpl extends BaseImpl implements EnrollmentsReader,EnrollmentsWriter {
    private static final Logger LOG = Logger.getLogger(CourseReader.class);
    /**
     * Construct a new CanvasApi class with an OAuth token
     *
     * @param canvasBaseUrl The base URL of your canvas instance
     * @param apiVersion    The version of the Canvas API (currently 1)
     * @param oauthToken    OAuth token to use when executing API calls
     * @param restClient    The rest client implementation to use
     */
    public EnrollmentsImpl(String canvasBaseUrl, Integer apiVersion, String oauthToken, RestClient restClient) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient);
    }

    @Override
    public List<Enrollment> getUserEnrollments(String oauthToken, Integer user_Id) throws InvalidOauthTokenException, IOException {
        String createdUrl = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "users/" + user_Id+ "/enrollments", Collections.emptyMap());
        LOG.debug("create URl for get enrollments for user : "+ createdUrl);
        return retrieveEnrollments(oauthToken, createdUrl);
    }

    @Override
    public List<Enrollment> getSectionEnrollments(String oauthToken, Integer sectionId, List<EnrollmentType> enrollmentTypes) throws InvalidOauthTokenException, IOException {
        Map<String, List<String>> parameters = buildParameters(enrollmentTypes);
        String createdUrl = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "sections/" + sectionId + "/enrollments", parameters);
        LOG.debug("create URl for get enrollments for section : "+ createdUrl);
        return retrieveEnrollments(oauthToken, createdUrl);
    }

    @Override
    public Optional<Enrollment> enrollUser(String oauthToken, Integer course_Id, Integer userId) throws InvalidOauthTokenException, IOException {
        Map<String,String> courseMap = new HashMap<>();
        courseMap.put("enrollment[user_id]", String.valueOf(userId));
        String createdUrl = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "courses/" + course_Id + "/enrollments", Collections.emptyMap());
        LOG.debug("create URl for course enrollments: "+ createdUrl);
        Response response = canvasMessenger.sendToCanvas(oauthToken, createdUrl, courseMap);
        if (response.getErrorHappened() ||  response.getResponseCode() != 200) {
            LOG.debug("Failed to enroll in course, error message: " + response.toString());
            return Optional.empty();
        }
        return responseParser.parseToObject(Enrollment.class,response);
    }

    @Override
    public Optional<Enrollment> dropUser(String oauthToken, Integer course_id, Long enrollment_id) throws InvalidOauthTokenException, IOException {
        Map<String,String> postParams = new HashMap<>();
        postParams.put("task", "delete");
        String createdUrl = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "courses/" + course_id+ "/enrollments/"+enrollment_id, Collections.emptyMap());
        LOG.debug("create URl for course enrollments: "+ createdUrl);
        Response response = canvasMessenger.deleteFromCanvas(oauthToken, createdUrl, postParams);
        if (response.getErrorHappened() ||  response.getResponseCode() != 200) {
            LOG.debug("Failed to enroll in course, error message: " + response.toString());
            return Optional.empty();
        }
        return responseParser.parseToObject(Enrollment.class, response);
    }

    private List<Enrollment> retrieveEnrollments(String oauthToken, String url) throws IOException {
        List<Response> responses = canvasMessenger.getFromCanvas(oauthToken, url);
        return responses.stream().
                map(this::parseEnrollmentList).
                flatMap(Collection::stream).
                collect(Collectors.toList());
    }

    private Map<String, List<String>> buildParameters(List<EnrollmentType> enrollmentTypes) {
        return ImmutableMap.<String,List<String>>builder()
                .put("type[]", enrollmentTypes.stream().map(EnrollmentType::canvasValue).collect(Collectors.toList()))
                .build();
    }

    private List<Enrollment> parseEnrollmentList(Response response) {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        Type listType = new TypeToken<List<Enrollment>>(){}.getType();
        return gson.fromJson(response.getContent(), listType);
    }
}
