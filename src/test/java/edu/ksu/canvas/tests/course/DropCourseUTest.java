package edu.ksu.canvas.tests.course;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.constants.CanvasConstants;
import edu.ksu.canvas.impl.EnrollmentImpl;
import edu.ksu.canvas.interfaces.EnrollmentWriter;
import edu.ksu.canvas.model.Enrollment;
import edu.ksu.canvas.net.FakeRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DropCourseUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private EnrollmentWriter enrollmentsWriter;

    @BeforeEach
    void setupData() {
        enrollmentsWriter = new EnrollmentImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    void testEnrollUser() throws IOException {
        //should make sure user exists
        String url = baseUrl + "/api/v1/courses/25/enrollments";
        fakeRestClient.addSuccessResponse(url, "SampleJson/EnrollmentResponse.json");
        Enrollment enrollment = new Enrollment();
        enrollment.setCourseId(25L);
        enrollment.setUserId("78839");
        Optional<Enrollment> enrollmentResponse = enrollmentsWriter.enrollUser(enrollment);
        assertTrue(25==enrollmentResponse.get().getCourseId());
        assertEquals(enrollmentResponse.get().getUserId(),"78839");
        assertTrue(enrollmentResponse.get().getId()!=0);
    }

    @Test
    void testDropEnrolledUser() throws IOException {
        String url = baseUrl + "/api/v1/courses/25/enrollments/355047";
        fakeRestClient.addSuccessResponse(url, "SampleJson/EnrollmentDeleteResponse.json");
        Optional<Enrollment> dropResponse = enrollmentsWriter.dropUser("25", "355047");
        assertTrue("deleted".equals(dropResponse.get().getEnrollmentState()));
    }

    @Test
    void testSisUserMasqueradeEnrollUser() throws IOException {
        //should make sure user exists
        String userId = "899123456";
        String url = baseUrl + "/api/v1/courses/25/enrollments?as_user_id=" + CanvasConstants.MASQUERADE_SIS_USER + ":" + userId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/EnrollmentResponse.json");
        Enrollment enrollment = new Enrollment();
        enrollment.setCourseId(25L);
        enrollment.setUserId("78839");
        Optional<Enrollment> enrollmentResponse = enrollmentsWriter.writeAsSisUser(userId).enrollUser(enrollment);
        assertTrue(25==enrollmentResponse.get().getCourseId());
        assertEquals(enrollmentResponse.get().getUserId(),"78839");
        assertTrue(enrollmentResponse.get().getId()!=0);
    }

    @Test
    void testSisUserMasqueradeDropEnrolledUser() throws IOException {
        String userId = "899123456";
        String url = baseUrl + "/api/v1/courses/25/enrollments/355047?as_user_id=" + CanvasConstants.MASQUERADE_SIS_USER + ":" + userId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/EnrollmentDeleteResponse.json");
        Optional<Enrollment> dropResponse = enrollmentsWriter.writeAsSisUser(userId).dropUser("25", "355047");
        assertTrue("deleted".equals(dropResponse.get().getEnrollmentState()));
    }

    @Test
    void testCanvasUserMasqueradeEnrollUser() throws IOException {
        //should make sure user exists
        String userId = "899123456";
        String url = baseUrl + "/api/v1/courses/25/enrollments?as_user_id=" + userId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/EnrollmentResponse.json");
        Enrollment enrollment = new Enrollment();
        enrollment.setCourseId(25L);
        enrollment.setUserId("78839");
        Optional<Enrollment> enrollmentResponse = enrollmentsWriter.writeAsCanvasUser(userId).enrollUser(enrollment);
        assertTrue(25==enrollmentResponse.get().getCourseId());
        assertEquals(enrollmentResponse.get().getUserId(),"78839");
        assertTrue(enrollmentResponse.get().getId()!=0);
    }

    @Test
    void testCanvasUserMasqueradeDropEnrolledUser() throws IOException {
        String userId = "899123456";
        String url = baseUrl + "/api/v1/courses/25/enrollments/355047?as_user_id=" + userId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/EnrollmentDeleteResponse.json");
        Optional<Enrollment> dropResponse = enrollmentsWriter.writeAsCanvasUser(userId).dropUser("25", "355047");
        assertTrue("deleted".equals(dropResponse.get().getEnrollmentState()));
    }
}

