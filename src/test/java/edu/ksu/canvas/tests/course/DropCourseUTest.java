package edu.ksu.canvas.tests.course;

import edu.ksu.canvas.config.BaseTestConfig;
import edu.ksu.canvas.impl.CoursesImpl;
import edu.ksu.canvas.impl.EnrollmentsImpl;
import edu.ksu.canvas.impl.UserImpl;
import edu.ksu.canvas.interfaces.EnrollmentsReader;
import edu.ksu.canvas.interfaces.EnrollmentsWriter;
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

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@ContextConfiguration(classes = {BaseTestConfig.class})
public class DropCourseUTest {
    @Autowired
    private FakeRestClient fakeRestClient;
    private String canvasBaseURL = "https://k-state.test.instructure.com";
    private Integer apiVersion = 1;
    private String token = "1726~MQ8vuaJUbVosHUcvEcUfdHixQobAkS03AxSKVXvRy79lAcSX2uURHc2IHnDINpP2";
    private EnrollmentsWriter enrollmentsWriter;
    private EnrollmentsReader enrollmentsReader;
    @Before
    public void setup() {
        enrollmentsWriter = new EnrollmentsImpl(canvasBaseURL, apiVersion, token, fakeRestClient);
        enrollmentsReader = new EnrollmentsImpl(canvasBaseURL, apiVersion, token, fakeRestClient);
    }

    @Test
    public void testEnrollUser() throws IOException {
        //should make sure user exists
        Optional<Enrollment> enrollmentResponse = enrollmentsWriter.enrollUser(token,25,78839);
        Assert.assertTrue(25==enrollmentResponse.get().getCourseId());
        Assert.assertTrue(78839 == enrollmentResponse.get().getUserId());
        Assert.assertTrue(enrollmentResponse.get().getId()!=0);
    }

    @Test
    public void  testDropEnrolledUser() throws IOException {
        //TODO get user enrollments and find enrollment id .
        List<Enrollment> getEnrollments = enrollmentsReader.getUserEnrollments(token, 78839);
        Optional<Enrollment> dropResponse = enrollmentsWriter.dropUser(token, 25, 355047L);
        Assert.assertTrue("deleted".equals(dropResponse.get().getEnrollmentState()));
    }
}

