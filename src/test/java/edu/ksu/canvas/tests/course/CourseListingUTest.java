package edu.ksu.canvas.tests.course;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.CourseImpl;
import edu.ksu.canvas.interfaces.CourseReader;
import edu.ksu.canvas.model.Course;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.requestOptions.ListCurrentUserCoursesOptions;
import edu.ksu.canvas.requestOptions.ListUserCoursesOptions;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class CourseListingUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private CourseReader courseReader;

    private static final String ARBITRARY_USER_ID = "899123456";
    private static final String ARBITRARY_LOGIN_ID = "someLoginId";

    @BeforeEach
    void setupData() {
        courseReader = new CourseImpl(baseUrl,apiVersion,SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    void testGettingCoursesForCurrentUser() throws IOException, URISyntaxException, ParseException {
        String url = baseUrl + "/api/v1/courses";
        fakeRestClient.addSuccessResponse(url, "SampleJson/course/UserCourseListResponse.json");
        List<Course> userCourses = courseReader.listCurrentUserCourses(new ListCurrentUserCoursesOptions());
        assertEquals(2, userCourses.size(), "expected that user had 2 courses");
    }

    @Test
    void testGettingCoursesForUser() throws IOException, URISyntaxException, ParseException {
        String url = baseUrl + "/api/v1/users/" + ARBITRARY_USER_ID + "/courses";
        fakeRestClient.addSuccessResponse(url, "SampleJson/course/UserCourseListResponse.json");
        ListUserCoursesOptions listUserCoursesOptions = new ListUserCoursesOptions(ARBITRARY_USER_ID);
        List<Course> userCourses = courseReader.listUserCourses(listUserCoursesOptions);
        assertEquals(2, userCourses.size(), "expected that user had 2 courses");
        for(Course course: userCourses){
            assertCourseInfoIsExpected(course);
        }
    }

    @Test
    void testGettingCoursesForUserUsingSisLoginId() throws IOException, URISyntaxException, ParseException {
        String url = baseUrl + "/api/v1/users/sis_login_id:" + ARBITRARY_LOGIN_ID + "/courses";
        fakeRestClient.addSuccessResponse(url, "SampleJson/course/UserCourseListResponse.json");
        ListUserCoursesOptions listUserCoursesOptions = new ListUserCoursesOptions("sis_login_id:" + ARBITRARY_LOGIN_ID);
        List<Course> userCourses = courseReader.listUserCourses(listUserCoursesOptions);
        assertEquals(2, userCourses.size(), "expected that user had 2 courses");
        for(Course course: userCourses){
            assertCourseInfoIsExpected(course);
        }
    }

    private void assertCourseInfoIsExpected(Course course) {
        assertNotNull(course.getSections(), "expected that user courses had sections");
        assertNotNull(course.getEnrollmentTerm(), "expected that user courses has term info");
        assertNotNull(course.getEnrollmentTerm().getSisTermId(), "expected that user courses has term info, sisTermId(semesterKey)");
    }

}
