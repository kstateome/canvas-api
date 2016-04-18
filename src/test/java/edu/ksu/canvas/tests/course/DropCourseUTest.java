package edu.ksu.canvas.tests.course;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.EnrollmentsImpl;
import edu.ksu.canvas.interfaces.EnrollmentsReader;
import edu.ksu.canvas.interfaces.EnrollmentsWriter;
import edu.ksu.canvas.model.Enrollment;
import edu.ksu.canvas.net.FakeRestClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class DropCourseUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private EnrollmentsWriter enrollmentsWriter;
    private EnrollmentsReader enrollmentsReader;

    @Before
    public void setupData() {
        enrollmentsWriter = new EnrollmentsImpl(baseUrl, apiVersion, oauthToken.getToken(), fakeRestClient);
        enrollmentsReader = new EnrollmentsImpl(baseUrl, apiVersion, oauthToken.getToken(), fakeRestClient);
    }

    @Test
    public void testEnrollUser() throws IOException {
        //should make sure user exists
        Optional<Enrollment> enrollmentResponse = enrollmentsWriter.enrollUser(oauthToken.getToken(),25,78839);
        Assert.assertTrue(25==enrollmentResponse.get().getCourseId());
        Assert.assertTrue(78839 == enrollmentResponse.get().getUserId());
        Assert.assertTrue(enrollmentResponse.get().getId()!=0);
    }

    @Test
    public void  testDropEnrolledUser() throws IOException {
        //TODO get user enrollments and find enrollment id .
        List<Enrollment> getEnrollments = enrollmentsReader.getUserEnrollments(oauthToken.getToken(), 78839);
        Optional<Enrollment> dropResponse = enrollmentsWriter.dropUser(oauthToken.getToken(), 25, 355047L);
        Assert.assertTrue("deleted".equals(dropResponse.get().getEnrollmentState()));
    }
}

