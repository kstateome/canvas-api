package edu.ksu.canvas.course;

import edu.ksu.canvas.config.CourseTestConfig;
import edu.ksu.canvas.impl.CoursesImpl;
import edu.ksu.canvas.model.Course;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by prakashreddy on 10/7/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@ContextConfiguration(classes = {CourseTestConfig.class})
@WebAppConfiguration
public class CourseManagerUTest {


    String canvasBaseURL = "https://k-state.test.instructure.com";
    Integer apiVersion = 1;
    String token = "1726~MQ8vuaJUbVosHUcvEcUfdHixQobAkS03AxSKVXvRy79lAcSX2uURHc2IHnDINpP2";
    CoursesImpl coursesImpl;

    @Before
    public void setupData(){
        coursesImpl = new CoursesImpl(canvasBaseURL,apiVersion,null);
    }

    @Test
    public void testCourseCreation() throws IOException{
        //setup course
        Course newCourse = new Course();
        newCourse.setCourseCode("SeleniumTestCourseCode");
        newCourse.setName("SeleniumTestCourse");
        //newCourse.setName("TestCourseCreation");
        Optional<Course> response = coursesImpl.createCourse(token,newCourse);
        Assert.assertNotNull(response.get().getName());
        Assert.assertEquals("SeleniumTestCourseCode",response.get().getCourseCode());
    }

    @Test
    public void testCourseDeletion() throws IOException{
        Assert.assertTrue(coursesImpl.deleteCourse(token, "20732"));
    }
}
