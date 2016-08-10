package edu.ksu.canvas.impl;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.constants.CanvasConstants;
import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.interfaces.CourseWriter;
import edu.ksu.canvas.model.Delete;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.requestOptions.GetSingleCourseOptions;
import edu.ksu.canvas.requestOptions.ListActiveCoursesInAccountOptions;
import edu.ksu.canvas.requestOptions.ListCurrentUserCoursesOptions;
import edu.ksu.canvas.model.Course;
import org.apache.log4j.Logger;

import edu.ksu.canvas.interfaces.CourseReader;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.util.CanvasURLBuilder;


public class CoursesImpl extends BaseImpl<Course, CourseReader, CourseWriter> implements CourseReader, CourseWriter {
    private static final Logger LOG = Logger.getLogger(CourseReader.class);

    public CoursesImpl(String canvasBaseUrl, Integer apiVersion, String oauthToken, RestClient restClient, int connectTimeout, int readTimeout, Integer paginationPageSize) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout, paginationPageSize);
    }

    @Override
    public List<Course> listCurrentUserCourses(ListCurrentUserCoursesOptions options) throws IOException {
        LOG.info("listing courses for user");
        String url = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "courses/", options.getOptionsMap());
        return getListFromCanvas(url);
    }

    @Override
    public Optional<Course> getSingleCourse(GetSingleCourseOptions options) throws IOException {
        LOG.debug("geting course " + options.getCourseId());
        String url = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "courses/" + options.getCourseId(), options.getOptionsMap());
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
        Optional<Delete> responseParsed = responseParser.parseToObject(Delete.class, response);
        return responseParsed.get().getDelete();
    }

    @Override
    public List<Course> listActiveCoursesInAccount(ListActiveCoursesInAccountOptions options) throws IOException {
        String url = buildCanvasUrl("accounts/" + options.getAccountId() + "/courses", options.getOptionsMap());
        return getListFromCanvas(url);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<Course>>(){}.getType();
    }

    @Override
    protected Class<Course> objectType() {
        return Course.class;
    }

}
