package edu.ksu.canvas.tests.assignment;

import com.google.gson.JsonSyntaxException;
import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.impl.AssignmentsImpl;
import edu.ksu.canvas.interfaces.AssignmentReader;
import edu.ksu.canvas.model.Assignment;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.net.Response;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class AssignmentRetrieverUTest extends CanvasTestBase {
    private static final Logger LOG = Logger.getLogger(AssignmentRetrieverUTest.class);
    @Autowired
    private FakeRestClient fakeRestClient;
    private AssignmentReader assignmentReader;

    @Before
    public void setupData() {
        assignmentReader = new AssignmentsImpl(baseUrl, apiVersion, null, fakeRestClient);
    }


    @Test
    public void testListCourseAssignments() throws Exception {
        String someCourseId = "123456";
        Assignment assignment1 = new Assignment();
        assignment1.setId("1");
        Assignment assignment2 = new Assignment();
        assignment2.setId("2");
        Response notErroredResponse = new Response();
        notErroredResponse.setErrorHappened(false);
        notErroredResponse.setResponseCode(200);
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/assignments";
        fakeRestClient.addSuccessResponse(url, "AssignmentList.json");

        List<Assignment> assignments = assignmentReader.listCourseAssignments(oauthToken.getToken(), someCourseId);
        Assert.assertEquals(2, assignments.size());
        Assert.assertTrue(assignments.stream().map(Assignment::getName).filter("Assignment1"::equals).findFirst().isPresent());
        Assert.assertTrue(assignments.stream().map(Assignment::getName).filter("Assignment2"::equals).findFirst().isPresent());
    }

    @Test(expected = InvalidOauthTokenException.class)
    public void testListAssignments_canvasError() throws Exception {
        String someCourseId = "123456";
        Response erroredResponse = new Response();
        erroredResponse.setErrorHappened(true);
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/assignments";
        fakeRestClient.add401Response(url, "Assignment1.json");
        assignmentReader.listCourseAssignments(oauthToken.getToken(), someCourseId);
    }

    @Test(expected = JsonSyntaxException.class)
    public void testListAssignments_responseInvalid() throws Exception {
        String someCourseId = "123456";
        Response erroredResponse = new Response();
        erroredResponse.setResponseCode(401);
        String url =  baseUrl + "/api/v1/courses/" + someCourseId + "/assignments";
        fakeRestClient.addSuccessResponse(url, "InvalidJson.json");

        Assert.assertTrue(assignmentReader.listCourseAssignments(oauthToken.getToken(), someCourseId).isEmpty());
    }

    @Test
    public void testRetrieveAssignment() throws Exception {
        String someCourseId = "1234";
        String someAssignmentId = "123";
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/assignments/" + someAssignmentId;
        fakeRestClient.addSuccessResponse(url, "Assignment1.json");
        Optional<Assignment> assignment = assignmentReader.getSingleAssignment(oauthToken.getToken(), someCourseId, someAssignmentId);
        Assert.assertTrue(assignment.isPresent());
        Assert.assertEquals("Assignment1", assignment.map(Assignment::getName).orElse(""));
    }

}
