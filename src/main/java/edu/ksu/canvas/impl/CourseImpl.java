package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.CourseReader;
import edu.ksu.canvas.interfaces.CourseWriter;
import edu.ksu.canvas.model.Course;
import edu.ksu.canvas.model.status.Delete;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.GetSingleCourseOptions;
import edu.ksu.canvas.requestOptions.ListActiveCoursesInAccountOptions;
import edu.ksu.canvas.requestOptions.ListCurrentUserCoursesOptions;
import edu.ksu.canvas.requestOptions.ListUserCoursesOptions;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class CourseImpl extends BaseImpl<Course, CourseReader, CourseWriter> implements CourseReader, CourseWriter {
    private static final Logger LOG = Logger.getLogger(CourseReader.class);

    public CourseImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient, int connectTimeout, int readTimeout, Integer paginationPageSize) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout, paginationPageSize);
    }

    @Override
    public List<Course> listCurrentUserCourses(ListCurrentUserCoursesOptions options) throws IOException {
        LOG.info("listing courses for user");
        String url = buildCanvasUrl("courses/", options.getOptionsMap());
        return getListFromCanvas(url);
    }

    public List<Course> listUserCourses(ListUserCoursesOptions options) throws  IOException {
        LOG.info("listing course for user");
        String url = buildCanvasUrl("users/" + options.getUserId() + "/courses", options.getOptionsMap());
        return getListFromCanvas(url);
    }

    @Override
    public Optional<Course> getSingleCourse(GetSingleCourseOptions options) throws IOException {
        LOG.debug("getting course " + options.getCourseId());
        String url = buildCanvasUrl("courses/" + options.getCourseId(), options.getOptionsMap());
        LOG.debug("Final URL of API call: " + url);

        return retrieveCourseFromCanvas(oauthToken, url);
    }

    private Optional<Course> retrieveCourseFromCanvas(OauthToken oauthToken, String url) throws IOException {
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        if (response.getErrorHappened() || response.getResponseCode() != 200) {
            return Optional.empty();
        }
        return responseParser.parseToObject(Course.class, response);
    }

    @Override
    public Optional<Course> createCourse(String accountId, Course course) throws IOException {
        LOG.debug("creating course");
        String url = buildCanvasUrl("accounts/" + accountId + "/courses", Collections.emptyMap());
        Response response = canvasMessenger.sendJsonPostToCanvas(oauthToken, url, course.toJsonObject());
        return responseParser.parseToObject(Course.class, response);
    }

    @Override
    public Optional<Course> updateCourse(Course course) throws IOException {
        LOG.debug("updating course");
        String url = buildCanvasUrl("courses/" + course.getId(), Collections.emptyMap());
        Response response = canvasMessenger.sendJsonPutToCanvas(oauthToken, url, course.toJsonObject());
        return responseParser.parseToObject(Course.class, response);
    }

    @Override
    public Boolean deleteCourse(String courseId) throws IOException {
        Map<String, List<String>> postParams = new HashMap<>();
        postParams.put("event", Collections.singletonList("delete"));
        String createdUrl = buildCanvasUrl("courses/" + courseId, Collections.emptyMap());
        Response response = canvasMessenger.deleteFromCanvas(oauthToken, createdUrl, postParams);
        LOG.debug("response "+ response.toString());
        if (response.getErrorHappened() || response.getResponseCode() != 200) {
            LOG.debug("Failed to delete course, error message: " + response.toString());
            return false;
        }
        Optional<Delete> responseParsed = responseParser.parseToObject(Delete.class, response);

        return responseParsed.map(r -> r.getDelete()).orElse(false);
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
