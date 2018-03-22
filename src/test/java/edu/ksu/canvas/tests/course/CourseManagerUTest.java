package edu.ksu.canvas.tests.course;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.CourseImpl;
import edu.ksu.canvas.interfaces.CourseWriter;
import edu.ksu.canvas.model.Course;
import edu.ksu.canvas.net.FakeRestClient;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Optional;

public class CourseManagerUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private CourseWriter courseWriter;

    private static final String ARBITRARY_USER_ID = "899123456";
    private static final String ARBITRARY_COURSE_ID = "20732";
    private static final String ARBITRARY_ACCOUNT_ID = "9";

    @Before
    public void setupData() {
        courseWriter = new CourseImpl(baseUrl,apiVersion,SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    public void testCourseCreation() throws IOException {
        Course newCourse = new Course();
        newCourse.setCourseCode("SeleniumTestCourseCode");
        newCourse.setName("SeleniumTestCourse");
        String url = baseUrl + "/api/v1/accounts/" + ARBITRARY_ACCOUNT_ID + "/courses";
        fakeRestClient.addSuccessResponse(url, "SampleJson/course/CreateCourseSuccess.json");
        Optional<Course> response = courseWriter.createCourse(ARBITRARY_ACCOUNT_ID, newCourse);
        Assert.assertNotNull(response.get().getName());
        Assert.assertEquals("SeleniumTestCourseCode",response.get().getCourseCode());
    }

    @Test
    public void testCourseUpdate() throws IOException {
        Course newCourse = new Course();
        newCourse.setId(new Integer(ARBITRARY_COURSE_ID));
        newCourse.setCourseCode("UpdatedSeleniumTestCourseCode");
        newCourse.setName("UpdatedSeleniumTestName");
        String url = baseUrl + "/api/v1/courses/" + ARBITRARY_COURSE_ID;
        fakeRestClient.addSuccessResponse(url, "SampleJson/course/UpdateCourseSuccess.json");
        Optional<Course> response = courseWriter.updateCourse(newCourse);
        Assert.assertNotNull(response.get().getName());
        Assert.assertEquals(newCourse.getCourseCode(), response.get().getCourseCode());
    }

    @Test
    public void testCourseDeletion() throws IOException {
        String url = baseUrl + "/api/v1/courses/" + ARBITRARY_COURSE_ID;
        fakeRestClient.addSuccessResponse(url, "SampleJson/course/DeleteCourseSuccess.json");
        Boolean deleted = courseWriter.deleteCourse(ARBITRARY_COURSE_ID);
        Assert.assertTrue("course deletion did not return treu", deleted);
    }

    @Test
    public void testSisUserMasqueradeCourseCreation() throws IOException {
        Course newCourse = new Course();
        newCourse.setCourseCode("SeleniumTestCourseCode");
        newCourse.setName("SeleniumTestCourse");
        String url = baseUrl + "/api/v1/accounts/" + ARBITRARY_ACCOUNT_ID + "/courses?as_user_id=sis_user_id:" + ARBITRARY_USER_ID;
        System.out.println("URL in test class: " + url);
        fakeRestClient.addSuccessResponse(url, "SampleJson/course/CreateCourseSuccess.json");
        Optional<Course> response = courseWriter.writeAsSisUser(ARBITRARY_USER_ID).createCourse(ARBITRARY_ACCOUNT_ID, newCourse);
        Assert.assertNotNull(response.get().getName());
        Assert.assertEquals("SeleniumTestCourseCode",response.get().getCourseCode());
    }

    @Test
    public void testSisUserMasqueradeCourseDeletion() throws IOException {
        String url = baseUrl + "/api/v1/courses/" + ARBITRARY_COURSE_ID +"?as_user_id=sis_user_id:" + ARBITRARY_USER_ID;
        fakeRestClient.addSuccessResponse(url, "SampleJson/course/DeleteCourseSuccess.json");
        Boolean deleted = courseWriter.writeAsSisUser(ARBITRARY_USER_ID).deleteCourse(ARBITRARY_COURSE_ID);
        Assert.assertTrue("course deletion did not return true", deleted);
    }

    @Test
    public void testCanvasUserMasqueradeCourseCreation() throws IOException {
        Course newCourse = new Course();
        newCourse.setCourseCode("SeleniumTestCourseCode");
        newCourse.setName("SeleniumTestCourse");
        String url = baseUrl + "/api/v1/accounts/" + ARBITRARY_ACCOUNT_ID + "/courses?as_user_id=" + ARBITRARY_USER_ID;
        fakeRestClient.addSuccessResponse(url, "SampleJson/course/CreateCourseSuccess.json");
        Optional<Course> response = courseWriter.writeAsCanvasUser(ARBITRARY_USER_ID).createCourse(ARBITRARY_ACCOUNT_ID, newCourse);
        Assert.assertNotNull(response.get().getName());
        Assert.assertEquals("SeleniumTestCourseCode",response.get().getCourseCode());
    }

    @Test
    public void testCanvasUserMasqueradeCourseDeletion() throws IOException {
        String url = baseUrl + "/api/v1/courses/" + ARBITRARY_COURSE_ID + "?as_user_id=" + ARBITRARY_USER_ID;
        System.out.println("putting URL in face client: " + url);
        fakeRestClient.addSuccessResponse(url, "SampleJson/course/DeleteCourseSuccess.json");
        Boolean deleted = courseWriter.writeAsCanvasUser(ARBITRARY_USER_ID).deleteCourse(ARBITRARY_COURSE_ID);
        Assert.assertTrue("course deletion did not return true", deleted);
    }
}
