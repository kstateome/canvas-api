package edu.ksu.canvas.tests.course;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.EnrollmentsImpl;
import edu.ksu.canvas.interfaces.EnrollmentsWriter;
import edu.ksu.canvas.model.Enrollment;
import edu.ksu.canvas.net.FakeRestClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Optional;

public class DropCourseUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private EnrollmentsWriter enrollmentsWriter;

    @Before
    public void setupData() {
        enrollmentsWriter = new EnrollmentsImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT);
    }

    @Test
    public void testEnrollUser() throws IOException {
        //should make sure user exists
        String url = baseUrl + "/api/v1/courses/25/enrollments";
        fakeRestClient.addSuccessResponse(url, "SampleJson/EnrollmentResponse.json");
        Optional<Enrollment> enrollmentResponse = enrollmentsWriter.enrollUser(25,78839);
        Assert.assertTrue(25==enrollmentResponse.get().getCourseId());
        Assert.assertTrue(78839 == enrollmentResponse.get().getUserId());
        Assert.assertTrue(enrollmentResponse.get().getId()!=0);
    }

    @Test
    public void  testDropEnrolledUser() throws IOException {
        String url = baseUrl + "/api/v1/courses/25/enrollments/355047";
        fakeRestClient.addSuccessResponse(url, "SampleJson/EnrollmentDeleteResponse.json");
        Optional<Enrollment> dropResponse = enrollmentsWriter.dropUser(25, 355047L);
        Assert.assertTrue("deleted".equals(dropResponse.get().getEnrollmentState()));
    }
}

