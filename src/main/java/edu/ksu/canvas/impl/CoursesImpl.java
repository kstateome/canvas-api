package edu.ksu.canvas.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

import edu.ksu.canvas.enums.CourseIncludes;
import edu.ksu.canvas.enums.EnrollmentType;
import edu.ksu.canvas.enums.CourseState;
import edu.ksu.canvas.interfaces.CourseReader;
import edu.ksu.canvas.model.Course;
import edu.ksu.canvas.model.User;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.util.CanvasURLBuilder;

public class CoursesImpl extends BaseImpl implements CourseReader {
    private static final Logger LOG = Logger.getLogger(CourseReader.class);

    public CoursesImpl(String canvasBaseUrl, Integer apiVersion, String oauthToken) {
        super(canvasBaseUrl, apiVersion, oauthToken);
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
        LOG.debug("Final URL of API call: " + url);
        return retrieveCourseListFromCanvas(oauthToken, url);
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

    private List<Course> retrieveCourseListFromCanvas(String oauthToken, String url) throws IOException {
        List<Course> courses = new ArrayList<>();
        while(StringUtils.isNotBlank(url)) {
            Response response = canvasMessenger.getFromCanvas(oauthToken, url);
            if (response.getErrorHappened() || response.getResponseCode() != 200) {
                return Collections.emptyList();
            }
            courses.addAll(responseParser.parseToList(Course.class, response));
            url = response.getNextLink();
        }
        return courses;
    }

    private Optional<Course> retrieveCourseFromCanvas(String oauthToken, String url) throws IOException {
        Response response = canvasMessenger.getFromCanvas(oauthToken, url);
        if (response.getErrorHappened() || response.getResponseCode() != 200) {
            return Optional.empty();
        }
        return responseParser.parseToObject(Course.class, response);
    }

//    @Override
//    public List<User> getUsersInCourse(Integer courseId) throws IOException {
//        String url = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "courses/" + courseId + "/users", Collections.emptyMap());
//        Response response = canvasMessenger.getFromCanvas(oauthToken, url);
//        return responseParser.parseToList(User.class, response);
//    }

}
