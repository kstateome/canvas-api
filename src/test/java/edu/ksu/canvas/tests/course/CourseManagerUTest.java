package edu.ksu.canvas.tests.course;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.CoursesImpl;
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

    @Before
    public void setupData() {
        courseWriter = new CoursesImpl(baseUrl,apiVersion,SOME_OAUTH_TOKEN, fakeRestClient);
    }

    @Test
    public void testCourseCreation() throws IOException {
        Course newCourse = new Course();
        newCourse.setCourseCode("SeleniumTestCourseCode");
        newCourse.setName("SeleniumTestCourse");
        String url = baseUrl + "/api/v1/accounts/1/courses";
        fakeRestClient.addSuccessResponse(url, "SampleJson/course/CreateCourseSuccess.json");
        Optional<Course> response = courseWriter.createCourse(newCourse);
        Assert.assertNotNull(response.get().getName());
        Assert.assertEquals("SeleniumTestCourseCode",response.get().getCourseCode());
    }

    @Test
    public void testCourseDeletion() throws IOException {
        String arbitraryCourseId = "20732";
        String url = baseUrl + "/api/v1/courses/" + arbitraryCourseId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/course/DeleteCourseSuccess.json");
        Assert.assertTrue(courseWriter.deleteCourse(arbitraryCourseId));
    }
}
