package edu.ksu.canvas.impl;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import edu.ksu.canvas.constants.CanvasConstants;
import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.interfaces.CourseWriter;
import edu.ksu.canvas.model.Delete;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.model.Course;
import org.apache.log4j.Logger;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

import edu.ksu.canvas.enums.CourseIncludes;
import edu.ksu.canvas.enums.EnrollmentType;
import edu.ksu.canvas.enums.CourseState;
import edu.ksu.canvas.interfaces.CourseReader;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.util.CanvasURLBuilder;


public class CoursesImpl extends BaseImpl<Course, CourseReader> implements CourseReader, CourseWriter {
    private static final Logger LOG = Logger.getLogger(CourseReader.class);

    public CoursesImpl(String canvasBaseUrl, Integer apiVersion, String oauthToken, RestClient restClient) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient);
    }

    @Override
    public List<Course> listCourses(Optional<EnrollmentType> enrollmentType,
            Optional<Integer> enrollmentRoleId, List<CourseIncludes> includes,
            List<CourseState> states) throws IOException {
        LOG.info("listing courses for user");
        Builder<String, List<String>> paramsBuilder = ImmutableMap.<String, List<String>>builder();
        enrollmentType.ifPresent(e -> paramsBuilder.put("enrollment_type", Collections.singletonList(e.name())));
        enrollmentRoleId.ifPresent(e -> paramsBuilder.put("enrollment_role_id", Collections.singletonList(e.toString())));
        paramsBuilder.put("include[]", includes.stream().map(Enum::name).collect(Collectors.toList()));
        paramsBuilder.put("state[]", states.stream().map(Enum::name).collect(Collectors.toList()));

        ImmutableMap<String, List<String>> parameters = paramsBuilder.build();
        String url = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "courses/", parameters);
        return getListFromCanvas(url);
    }

    @Override
    public Optional<Course> getSingleCourse(String courseId, List<CourseIncludes> includes) throws IOException {
        LOG.debug("geting course " + courseId);
        ImmutableMap<String, List<String>> parameters = ImmutableMap.<String,List<String>>builder()
                .put("include[]", includes.stream().map(Enum::name).collect(Collectors.toList()))
                .build();
        String url = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "courses/" + courseId, parameters);
        LOG.debug("Final URL of API call: " + url);

        return retrieveCourseFromCanvas(oauthToken, url);
    }

    private Optional<Course> retrieveCourseFromCanvas(String oauthToken, String url) throws IOException {
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        if (response.getErrorHappened() || response.getResponseCode() != 200) {
            return Optional.empty();
        }
        return responseParser.parseToObject(Course.class, response);
    }

    @Override
    public Optional<Course> createCourse(Course course) throws InvalidOauthTokenException, IOException {
        //TODO to parse object to map<String,List<String>>
        Map<String,String> postParams = new HashMap<>();
        postParams.put("course[course_code]", course.getCourseCode());
        postParams.put("course[name]", course.getName());
        String createdUrl = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "accounts/" + CanvasConstants.ACCOUNT_ID + "/courses", Collections.emptyMap());
        LOG.debug("create URl for course creation : "+ createdUrl);
        Response response = canvasMessenger.sendToCanvas(oauthToken, createdUrl, postParams);
        if (response.getErrorHappened() ||  response.getResponseCode() != 200) {
            LOG.debug("Failed to create course, error message: " + response.toString());
            return Optional.empty();
        }
        return responseParser.parseToObject(Course.class, response);
    }


    @Override
    public Boolean deleteCourse(String courseId) throws InvalidOauthTokenException, IOException {
        Map<String,String> postParams = new HashMap<>();
        postParams.put("event", "delete");
        String createdUrl = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "courses/" + courseId, Collections.emptyMap());
        Response response = canvasMessenger.deleteFromCanvas(oauthToken, createdUrl, postParams);
        LOG.debug("response "+ response.toString());
        if (response.getErrorHappened() || response.getResponseCode() != 200) {
            LOG.debug("Failed to delete course, error message: " + response.toString());
            return false;
        }
        Optional<Delete> responseParsed = responseParser.parseToObject(Delete.class,response);
        return responseParsed.get().getDelete();
    }

    @Override
    protected Type listType() {
        return null;
    }

    @Override
    protected Class<Course> objectType() {
        return null;
    }

    @Override
    public CourseReader withCallback(Consumer<List<Course>> callback) {
        return null;
    }
}
