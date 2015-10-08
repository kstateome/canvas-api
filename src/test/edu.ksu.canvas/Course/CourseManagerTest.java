package Course;

import config.CourseTestConfig;
import edu.ksu.canvas.entity.lti.OauthToken;
import edu.ksu.canvas.impl.CoursesImpl;
import edu.ksu.canvas.model.Course;
import edu.ksu.canvas.repository.OauthTokenRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.yaml.snakeyaml.tokens.Token;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by prakashreddy on 10/7/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@ContextConfiguration(classes = {CourseTestConfig.class})
@WebAppConfiguration
public class CourseManagerTest{


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
        Assert.assertEquals(response.get().getCourseCode(),"SeleniumTestCourseCode");
    }

    /*@Test
    public void testCourseDeletion() throws IOException{
        //setup course
        Course newCourse = new Course();
        newCourse.setAccountId(1);
        newCourse.setCourseCode("SeleniumTestCourseCode");
        newCourse.setName("SeleniumTestCourse");
        //newCourse.setName("TestCourseCreation");
        Assert.assertTrue(coursesImpl.deleteCourse(token, newCourse));
    }*/
}
