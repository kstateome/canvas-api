package edu.ksu.canvas.tests.course;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.CourseImpl;
import edu.ksu.canvas.interfaces.CourseReader;
import edu.ksu.canvas.model.Course;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.requestOptions.ListCurrentUserCoursesOptions;
import edu.ksu.canvas.requestOptions.ListUserCoursesOptions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

public class CourseListingUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private CourseReader courseReader;

    private static final String ARBITRARY_USER_ID = "899123456";
    private static final String ARBITRARY_LOGIN_ID = "someLoginId";

    @Before
    public void setupData() {
        courseReader = new CourseImpl(baseUrl,apiVersion,SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    public void testGettingCoursesForCurrentUser() throws IOException {
        String url = baseUrl + "/api/v1/courses";
        fakeRestClient.addSuccessResponse(url, "SampleJson/course/UserCourseListResponse.json");
        List<Course> userCourses = courseReader.listCurrentUserCourses(new ListCurrentUserCoursesOptions());
        Assert.assertEquals("expected that user had 2 courses", 2, userCourses.size());
    }

    @Test
    public void testGettingCoursesForUser() throws IOException {
        String url = baseUrl + "/api/v1/users/" + ARBITRARY_USER_ID + "/courses";
        fakeRestClient.addSuccessResponse(url, "SampleJson/course/UserCourseListResponse.json");
        ListUserCoursesOptions listUserCoursesOptions = new ListUserCoursesOptions(ARBITRARY_USER_ID);
        List<Course> userCourses = courseReader.listUserCourses(listUserCoursesOptions);
        Assert.assertEquals("expected that user had 2 courses", 2, userCourses.size());
        for(Course course: userCourses){
            assertCourseInfoIsExpected(course);
        }
    }

    @Test
    public void testGettingCoursesForUserUsingSisLoginId() throws IOException {
        String url = baseUrl + "/api/v1/users/sis_login_id:" + ARBITRARY_LOGIN_ID + "/courses";
        fakeRestClient.addSuccessResponse(url, "SampleJson/course/UserCourseListResponse.json");
        ListUserCoursesOptions listUserCoursesOptions = new ListUserCoursesOptions("sis_login_id:" + ARBITRARY_LOGIN_ID);
        List<Course> userCourses = courseReader.listUserCourses(listUserCoursesOptions);
        Assert.assertEquals("expected that user had 2 courses", 2, userCourses.size());
        for(Course course: userCourses){
            assertCourseInfoIsExpected(course);
        }
    }

    private void assertCourseInfoIsExpected(Course course) {
        Assert.assertNotNull("expected that user courses had sections", course.getSections());
        Assert.assertNotNull("expected that user courses has term info", course.getEnrollmentTerm());
        Assert.assertNotNull("expected that user courses has term info, sisTermId(semesterKey)", course.getEnrollmentTerm().getSisTermId());
    }

}
