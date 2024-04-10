package edu.ksu.canvas.tests.course;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.CourseImpl;
import edu.ksu.canvas.interfaces.CourseWriter;
import edu.ksu.canvas.model.Course;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.requestOptions.DeleteCourseOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CourseManagerUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private CourseWriter courseWriter;

    private static final String ARBITRARY_USER_ID = "899123456";
    private static final String ARBITRARY_COURSE_ID = "20732";
    private static final String ARBITRARY_ACCOUNT_ID = "9";

    @BeforeEach
    void setupData() {
        courseWriter = new CourseImpl(baseUrl,apiVersion,SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    void testCourseCreation() throws IOException {
        Course newCourse = new Course();
        newCourse.setCourseCode("SeleniumTestCourseCode");
        newCourse.setName("SeleniumTestCourse");
        String url = baseUrl + "/api/v1/accounts/" + ARBITRARY_ACCOUNT_ID + "/courses";
        fakeRestClient.addSuccessResponse(url, "SampleJson/course/CreateCourseSuccess.json");
        Optional<Course> response = courseWriter.createCourse(ARBITRARY_ACCOUNT_ID, newCourse);
        assertNotNull(response.get().getName());
        assertEquals("SeleniumTestCourseCode",response.get().getCourseCode());
    }

    @Test
    void testCourseUpdate() throws IOException {
        Course newCourse = new Course();
        newCourse.setId(new Long(ARBITRARY_COURSE_ID));
        newCourse.setCourseCode("UpdatedSeleniumTestCourseCode");
        newCourse.setName("UpdatedSeleniumTestName");
        String url = baseUrl + "/api/v1/courses/" + ARBITRARY_COURSE_ID;
        fakeRestClient.addSuccessResponse(url, "SampleJson/course/UpdateCourseSuccess.json");
        Optional<Course> response = courseWriter.updateCourse(newCourse);
        assertNotNull(response.get().getName());
        assertEquals(newCourse.getCourseCode(), response.get().getCourseCode());
    }

    @Test
    void testCourseUpdateWithId() throws IOException {
        Course newCourse = new Course();
        newCourse.setId(new Long(ARBITRARY_COURSE_ID));
        newCourse.setCourseCode("UpdatedSeleniumTestCourseCode");
        newCourse.setName("UpdatedSeleniumTestName");
        String url = baseUrl + "/api/v1/courses/" + "sis_course_id:sis-id-1";
        fakeRestClient.addSuccessResponse(url, "SampleJson/course/UpdateCourseSuccess.json");
        Optional<Course> response = courseWriter.updateCourse("sis_course_id:sis-id-1", newCourse);
        assertNotNull(response.get().getName());
        assertEquals(newCourse.getCourseCode(), response.get().getCourseCode());
    }

    @Test
    void testCourseUpdateWithBadCharacters() throws IOException {
        Course newCourse = new Course();
        newCourse.setId(new Long(ARBITRARY_COURSE_ID));
        newCourse.setCourseCode("UpdatedSeleniumTestCourseCode");
        newCourse.setName("UpdatedSeleniumTestName");
        String url = baseUrl + "/api/v1/courses/" + "sis_course_id:sis id 1 ū";
        fakeRestClient.addSuccessResponse(url, "SampleJson/course/UpdateCourseSuccess.json");
        Optional<Course> response = courseWriter.updateCourse("sis_course_id:sis id 1 ū", newCourse);
        assertNotNull(response.get().getName());
        assertEquals(newCourse.getCourseCode(), response.get().getCourseCode());
    }

    @Test
    void testCourseDeletion() throws IOException {
        String url = baseUrl + "/api/v1/courses/" + ARBITRARY_COURSE_ID;
        fakeRestClient.addSuccessResponse(url, "SampleJson/course/DeleteCourseSuccess.json");
        Boolean deleted = courseWriter.deleteCourse(ARBITRARY_COURSE_ID);
        assertTrue(deleted, "course deletion did not return treu");
    }

    @Test
    void testCourseDeletion_withOptions() throws IOException {
        String url = baseUrl + "/api/v1/courses/" + ARBITRARY_COURSE_ID;
        fakeRestClient.addSuccessResponse(url, "SampleJson/course/DeleteCourseSuccess.json");
        DeleteCourseOptions options = new DeleteCourseOptions(ARBITRARY_COURSE_ID,
                DeleteCourseOptions.EventType.DELETE);
        Boolean deleted = courseWriter.deleteCourse(options);
        assertTrue(deleted, "course deletion (with event=delete) did not return true");
    }

    @Test
    void testCourseConclusion() throws IOException {
        String url = baseUrl + "/api/v1/courses/" + ARBITRARY_COURSE_ID;
        fakeRestClient.addSuccessResponse(url, "SampleJson/course/DeleteConcludeCourseSuccess.json");
        DeleteCourseOptions options = new DeleteCourseOptions(ARBITRARY_COURSE_ID,
                DeleteCourseOptions.EventType.CONCLUDE);
        Boolean deleted = courseWriter.deleteCourse(options);
        assertTrue(deleted, "course deletion (with event=conclude) did not return true");
    }

    @Test
    void testSisUserMasqueradeCourseCreation() throws IOException {
        Course newCourse = new Course();
        newCourse.setCourseCode("SeleniumTestCourseCode");
        newCourse.setName("SeleniumTestCourse");
        String url = baseUrl + "/api/v1/accounts/" + ARBITRARY_ACCOUNT_ID + "/courses?as_user_id=sis_user_id:" + ARBITRARY_USER_ID;
        System.out.println("URL in test class: " + url);
        fakeRestClient.addSuccessResponse(url, "SampleJson/course/CreateCourseSuccess.json");
        Optional<Course> response = courseWriter.writeAsSisUser(ARBITRARY_USER_ID).createCourse(ARBITRARY_ACCOUNT_ID, newCourse);
        assertNotNull(response.get().getName());
        assertEquals("SeleniumTestCourseCode",response.get().getCourseCode());
    }

    @Test
    void testSisUserMasqueradeCourseDeletion() throws IOException {
        String url = baseUrl + "/api/v1/courses/" + ARBITRARY_COURSE_ID +"?as_user_id=sis_user_id:" + ARBITRARY_USER_ID;
        fakeRestClient.addSuccessResponse(url, "SampleJson/course/DeleteCourseSuccess.json");
        Boolean deleted = courseWriter.writeAsSisUser(ARBITRARY_USER_ID).deleteCourse(ARBITRARY_COURSE_ID);
        assertTrue(deleted, "course deletion did not return true");
    }

    @Test
    void testCanvasUserMasqueradeCourseCreation() throws IOException {
        Course newCourse = new Course();
        newCourse.setCourseCode("SeleniumTestCourseCode");
        newCourse.setName("SeleniumTestCourse");
        String url = baseUrl + "/api/v1/accounts/" + ARBITRARY_ACCOUNT_ID + "/courses?as_user_id=" + ARBITRARY_USER_ID;
        fakeRestClient.addSuccessResponse(url, "SampleJson/course/CreateCourseSuccess.json");
        Optional<Course> response = courseWriter.writeAsCanvasUser(ARBITRARY_USER_ID).createCourse(ARBITRARY_ACCOUNT_ID, newCourse);
        assertNotNull(response.get().getName());
        assertEquals("SeleniumTestCourseCode",response.get().getCourseCode());
    }

    @Test
    void testCanvasUserMasqueradeCourseDeletion() throws IOException {
        String url = baseUrl + "/api/v1/courses/" + ARBITRARY_COURSE_ID + "?as_user_id=" + ARBITRARY_USER_ID;
        System.out.println("putting URL in face client: " + url);
        fakeRestClient.addSuccessResponse(url, "SampleJson/course/DeleteCourseSuccess.json");
        Boolean deleted = courseWriter.writeAsCanvasUser(ARBITRARY_USER_ID).deleteCourse(ARBITRARY_COURSE_ID);
        assertTrue(deleted, "course deletion did not return true");
    }
}
