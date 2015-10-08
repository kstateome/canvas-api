package edu.ksu.canvas.impl;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

import edu.ksu.canvas.constants.CanvasConstants;
import edu.ksu.canvas.entity.lti.OauthToken;
import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.interfaces.CourseManager;
import edu.ksu.canvas.model.Delete;
import edu.ksu.canvas.model.Enrollment;
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

public class CoursesImpl extends BaseImpl implements CourseReader,CourseManager {
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

    @Override
    public Optional<Course> createCourse(String oauthToken, Course course) throws InvalidOauthTokenException, IOException {
        //should add parameters to define the course
        //set course name and code
        //should do this is a better way
        Map<String,String> courseMap = new HashMap<String,String>();
        if(course!=null) {
            courseMap.put("course_code", course.getCourseCode());
            courseMap.put("name", course.getName());
        }

        String createdUrl = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "accounts/" + CanvasConstants.ACCOUNT_ID + "/courses", null);
        LOG.debug("create URl for course creation : "+ createdUrl);
        Response response = canvasMessenger.sendToCanvas(oauthToken, createdUrl, courseMap);
        if (response.getErrorHappened() ||  response.getResponseCode() != 200) {
            LOG.debug("Failed to create course, error message: " + response.toString());
            return Optional.empty();
        }
        return responseParser.parseToObject(Course.class, response);
    }

    @Override
    public Boolean deleteCourse(String oauthToken, Course course) throws InvalidOauthTokenException, IOException {
        //url should be appended with 'event' type either 'delet' or 'closure'
        //append delete data if required
        Map<String,String> courseMap = new HashMap<String,String>();
        if(course!=null) {
            courseMap.put("course_code", course.getCourseCode());
            courseMap.put("name", course.getName());
        }
        String createdUrl = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "courses/"+CanvasConstants.ACCOUNT_ID+"?event=delete",null);
        Response response = canvasMessenger.deleteFromCanvas(oauthToken, createdUrl, courseMap);
        LOG.debug("response "+ response.toString());
        if (response.getErrorHappened() || response.getResponseCode() != 200) {
            LOG.debug("Failed to delete course, error message: " + response.toString());
            return false;
        }
        Optional<Delete> responseParsed = responseParser.parseToObject(Delete.class,response);
        return responseParsed.get().getDelete();
    }



    @Override
    public Optional<Enrollment> enrollUser(String oauthToken, Integer course_Id, Integer userId) throws InvalidOauthTokenException, IOException {
        Map<String,List<String>> courseMap = new HashMap<String,List<String>>();
        courseMap.put(URLEncoder.encode("enrollment[user_id]","UTF-8"), Collections.singletonList(String.valueOf(userId)));
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


//    @Override
//    public List<User> getUsersInCourse(Integer courseId) throws IOException {
//        String url = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "courses/" + courseId + "/users", Collections.emptyMap());
//        Response response = canvasMessenger.getFromCanvas(oauthToken, url);
//        return responseParser.parseToList(User.class, response);
//    }

}
