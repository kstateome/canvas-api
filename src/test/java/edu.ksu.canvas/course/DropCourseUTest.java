package edu.ksu.canvas.course;

import edu.ksu.canvas.config.BaseTestConfig;
import edu.ksu.canvas.impl.CoursesImpl;
import edu.ksu.canvas.impl.EnrollmentsImpl;
import edu.ksu.canvas.impl.UserImpl;
import edu.ksu.canvas.model.Enrollment;
import edu.ksu.canvas.net.FakeRestClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Created by prv on 10/8/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@ContextConfiguration(classes = {BaseTestConfig.class})
@WebAppConfiguration
public class DropCourseUTest {
    @Autowired
    private FakeRestClient fakeRestClient;
    private String canvasBaseURL = "https://k-state.test.instructure.com";
    private Integer apiVersion = 1;
    private String token = "1726~MQ8vuaJUbVosHUcvEcUfdHixQobAkS03AxSKVXvRy79lAcSX2uURHc2IHnDINpP2";
    private CoursesImpl coursesImpl;
    private UserImpl userImpl;
    private EnrollmentsImpl enrollmentsImpl;
    @Before
    public void setup(){
        coursesImpl = new CoursesImpl(canvasBaseURL,apiVersion, token, fakeRestClient);
        userImpl = new UserImpl(canvasBaseURL, apiVersion, token, fakeRestClient);
        enrollmentsImpl = new EnrollmentsImpl(canvasBaseURL, apiVersion, token, fakeRestClient);
    }

    @Test
    public void testEnrollUser() throws IOException {
        //should make sure user exists
        Optional<Enrollment> enrollmentResponse = enrollmentsImpl.enrollUser(token,25,78839);
        Assert.assertTrue(25==enrollmentResponse.get().getCourseId());
        Assert.assertTrue(78839 == enrollmentResponse.get().getUserId());
        Assert.assertTrue(enrollmentResponse.get().getId()!=0);
    }

    @Test
    public void  testDropEnrolledUser() throws IOException {
        //TODO get user enrollments and find enrollment id .
        List<Enrollment> getEnrollments = enrollmentsImpl.getUserEnrollments(token, 78839);
        Optional<Enrollment> dropResponse = enrollmentsImpl.dropUser(token, 25, 355047L);
        Assert.assertTrue("deleted".equals(dropResponse.get().getEnrollmentState()));
    }
}

