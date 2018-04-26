package edu.ksu.canvas.tests.assignment;

import com.google.gson.JsonSyntaxException;
import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.constants.CanvasConstants;
import edu.ksu.canvas.impl.AssignmentImpl;
import edu.ksu.canvas.interfaces.AssignmentReader;
import edu.ksu.canvas.model.assignment.Assignment;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.requestOptions.GetSingleAssignmentOptions;
import edu.ksu.canvas.requestOptions.ListCourseAssignmentsOptions;

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
        assignmentReader = new AssignmentImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
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
        fakeRestClient.addSuccessResponse(url, "SampleJson/assignment/AssignmentList.json");

        List<Assignment> assignments = assignmentReader.listCourseAssignments(new ListCourseAssignmentsOptions(someCourseId));
        Assert.assertEquals(2, assignments.size());
        Assert.assertTrue(assignments.stream().map(Assignment::getName).filter("Assignment1"::equals).findFirst().isPresent());
        Assert.assertTrue(assignments.stream().map(Assignment::getName).filter("Assignment2"::equals).findFirst().isPresent());
    }

    @Test(expected = JsonSyntaxException.class)
    public void testListAssignments_responseInvalid() throws Exception {
        String someCourseId = "123456";
        Response erroredResponse = new Response();
        erroredResponse.setResponseCode(401);
        String url =  baseUrl + "/api/v1/courses/" + someCourseId + "/assignments";
        fakeRestClient.addSuccessResponse(url, "InvalidJson.json");

        Assert.assertTrue(assignmentReader.listCourseAssignments(new ListCourseAssignmentsOptions(someCourseId)).isEmpty());
    }

    @Test
    public void testRetrieveAssignment() throws Exception {
        String someCourseId = "1234";
        String someAssignmentId = "123";
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/assignments/" + someAssignmentId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/assignment/MinimalAssignment.json");
        Optional<Assignment> assignment = assignmentReader.getSingleAssignment(new GetSingleAssignmentOptions(someCourseId, someAssignmentId));
        Assert.assertTrue(assignment.isPresent());
        Assert.assertEquals("Assignment1", assignment.map(Assignment::getName).orElse(""));
    }

    @Test
    public void testSisUserMasqueradeListCourseAssignments() throws Exception {
        String someUserId = "899123456";
        String someCourseId = "123456";
        Assignment assignment1 = new Assignment();
        assignment1.setId("1");
        Assignment assignment2 = new Assignment();
        assignment2.setId("2");
        Response notErroredResponse = new Response();
        notErroredResponse.setErrorHappened(false);
        notErroredResponse.setResponseCode(200);
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/assignments?as_user_id=" + CanvasConstants.MASQUERADE_SIS_USER + ":" + someUserId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/assignment/AssignmentList.json");

        List<Assignment> assignments = assignmentReader.readAsSisUser(someUserId).listCourseAssignments(new ListCourseAssignmentsOptions(someCourseId));
        Assert.assertEquals(2, assignments.size());
        Assert.assertTrue(assignments.stream().map(Assignment::getName).filter("Assignment1"::equals).findFirst().isPresent());
        Assert.assertTrue(assignments.stream().map(Assignment::getName).filter("Assignment2"::equals).findFirst().isPresent());
    }

    @Test(expected = JsonSyntaxException.class)
    public void testSisUserMasqueradeListAssignments_responseInvalid() throws Exception {
        String someUserId = "899123456";
        String someCourseId = "123456";
        Response erroredResponse = new Response();
        erroredResponse.setResponseCode(401);
        String url =  baseUrl + "/api/v1/courses/" + someCourseId + "/assignments?as_user_id=" + CanvasConstants.MASQUERADE_SIS_USER + ":" + someUserId;
        fakeRestClient.addSuccessResponse(url, "InvalidJson.json");
        Assert.assertTrue(assignmentReader.readAsSisUser(someUserId).listCourseAssignments(new ListCourseAssignmentsOptions(someCourseId)).isEmpty());
    }

    @Test
    public void testSisUserMasqueradingRetriveAssignment() throws Exception{
        String someUserId = "8991123123";
        String someCourseId = "1234";
        String someAssignmentId = "123";
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/assignments/" + someAssignmentId + "?as_user_id=" + CanvasConstants.MASQUERADE_SIS_USER + ":" + someUserId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/assignment/MinimalAssignment.json");
        Optional<Assignment> assignment = assignmentReader.readAsSisUser(someUserId).getSingleAssignment(new GetSingleAssignmentOptions(someCourseId, someAssignmentId));
        Assert.assertTrue(assignment.isPresent());
        Assert.assertEquals("Assignment1", assignment.map(Assignment::getName).orElse(""));
    }

    @Test
    public void testCanvasUserMasqueradeListCourseAssignments() throws Exception {
        String someUserId = "899123456";
        String someCourseId = "123456";
        Assignment assignment1 = new Assignment();
        assignment1.setId("1");
        Assignment assignment2 = new Assignment();
        assignment2.setId("2");
        Response notErroredResponse = new Response();
        notErroredResponse.setErrorHappened(false);
        notErroredResponse.setResponseCode(200);
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/assignments?as_user_id=" + someUserId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/assignment/AssignmentList.json");

        List<Assignment> assignments = assignmentReader.readAsCanvasUser(someUserId).listCourseAssignments(new ListCourseAssignmentsOptions(someCourseId));
        Assert.assertEquals(2, assignments.size());
        Assert.assertTrue(assignments.stream().map(Assignment::getName).filter("Assignment1"::equals).findFirst().isPresent());
        Assert.assertTrue(assignments.stream().map(Assignment::getName).filter("Assignment2"::equals).findFirst().isPresent());
    }

    @Test(expected = JsonSyntaxException.class)
    public void testCanvasUserMasqueradeListAssignments_responseInvalid() throws Exception {
        String someUserId = "899123456";
        String someCourseId = "123456";
        Response erroredResponse = new Response();
        erroredResponse.setResponseCode(401);
        String url =  baseUrl + "/api/v1/courses/" + someCourseId + "/assignments?as_user_id=" + someUserId;
        fakeRestClient.addSuccessResponse(url, "InvalidJson.json");
        Assert.assertTrue(assignmentReader.readAsCanvasUser(someUserId).listCourseAssignments(new ListCourseAssignmentsOptions(someCourseId)).isEmpty());
    }

    @Test
    public void testCanvasUserMasqueradingRetriveAssignment() throws Exception{
        String someUserId = "8991123123";
        String someCourseId = "1234";
        String someAssignmentId = "123";
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/assignments/" + someAssignmentId + "?as_user_id=" + someUserId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/assignment/MinimalAssignment.json");
        Optional<Assignment> assignment = assignmentReader.readAsCanvasUser(someUserId).getSingleAssignment(new GetSingleAssignmentOptions(someCourseId, someAssignmentId));
        Assert.assertTrue(assignment.isPresent());
        Assert.assertEquals("Assignment1", assignment.map(Assignment::getName).orElse(""));
    }


}
