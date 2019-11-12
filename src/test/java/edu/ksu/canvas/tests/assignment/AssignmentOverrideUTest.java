package edu.ksu.canvas.tests.assignment;

import com.google.gson.JsonSyntaxException;
import edu.ksu.canvas.CanvasTestBase;
import static edu.ksu.canvas.CanvasTestBase.DEFAULT_PAGINATION_PAGE_SIZE;
import static edu.ksu.canvas.CanvasTestBase.SOME_CONNECT_TIMEOUT;
import static edu.ksu.canvas.CanvasTestBase.SOME_OAUTH_TOKEN;
import static edu.ksu.canvas.CanvasTestBase.SOME_READ_TIMEOUT;
import edu.ksu.canvas.impl.AssignmentOverrideImpl;
import edu.ksu.canvas.interfaces.AssignmentOverrideReader;
import edu.ksu.canvas.interfaces.AssignmentOverrideWriter;
import edu.ksu.canvas.model.assignment.AssignmentOverride;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.net.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class AssignmentOverrideUTest extends CanvasTestBase {
    private static final Logger LOG = LoggerFactory.getLogger(AssignmentRetrieverUTest.class);
    @Autowired
    private FakeRestClient fakeRestClient;
    private AssignmentOverrideReader assignmentOverrideReader;
    private AssignmentOverrideWriter assignmentOverrideWriter;

    @Before
    public void setupData() {
        assignmentOverrideReader = new AssignmentOverrideImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
        assignmentOverrideWriter = new AssignmentOverrideImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }
    
    @Test(expected = JsonSyntaxException.class)
    public void testListAssignments_responseInvalid() throws Exception {
        String someCourseId = "14";
        Integer someAssignmentId = 80;
        Response erroredResponse = new Response();
        erroredResponse.setResponseCode(401);
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/assignments/" + someAssignmentId + "/overrides";
        fakeRestClient.addSuccessResponse(url, "InvalidJson.json");

        Assert.assertTrue(assignmentOverrideReader.listAssignmentOverrides(someCourseId, someAssignmentId).isEmpty());
    }
    
    @Test
    public void testRetrieveAssignmentOverride() throws Exception {
        AssignmentOverride assignmentOverride1 = new AssignmentOverride();
        assignmentOverride1.setId(4);
        String someCourseId = "14";
        Integer someAssignmentId = 80;
        Integer someAssignmentOverrideId = 4;
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/assignments/" + someAssignmentId + "/overrides/" + someAssignmentOverrideId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/assignment/AssignmentOverride.json");
        Optional<AssignmentOverride> assignmentOverride = assignmentOverrideReader.getAssignmentOverride(someCourseId, someAssignmentId, someAssignmentOverrideId);
        
        Assert.assertTrue(assignmentOverride.isPresent());
        Assert.assertEquals(new Integer(4), assignmentOverride.map(AssignmentOverride::getId).orElse(0));
    }

    @Test
    public void testRetrieveAssignmentOverrides() throws Exception {
        AssignmentOverride assignmentOverride = new AssignmentOverride();
        assignmentOverride.setId(4);
        String someCourseId = "14";
        Integer someAssignmentId = 80;
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/assignments/" + someAssignmentId + "/overrides";
        fakeRestClient.addSuccessResponse(url, "SampleJson/assignment/AssignmentOverrideList.json");
        List<AssignmentOverride> assignmentOverrides = assignmentOverrideReader.listAssignmentOverrides(someCourseId, someAssignmentId);
        
        Assert.assertEquals(3, assignmentOverrides.size());
        Assert.assertTrue(assignmentOverrides.stream().map(AssignmentOverride::getTitle).filter("Sec1"::equals).findFirst().isPresent());
        Assert.assertTrue(assignmentOverrides.stream().map(AssignmentOverride::getTitle).filter("Sec2"::equals).findFirst().isPresent());
        Assert.assertTrue(assignmentOverrides.stream().map(AssignmentOverride::getTitle).filter("Sec3"::equals).findFirst().isPresent());
    }
    
    @Test
    public void testCreateAssignmentOverride() throws Exception {
        String someCourseId = "14";
        Integer someAssignmentId = 80;
        AssignmentOverride assignmentOverride1 = new AssignmentOverride();
        assignmentOverride1.setId(4);
        assignmentOverride1.setTitle("Section1");
        assignmentOverride1.setAssignmentId(someAssignmentId);
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/assignments/" + someAssignmentId + "/overrides";
        fakeRestClient.addSuccessResponse(url, "SampleJson/assignment/AssignmentOverride.json");
        Optional<AssignmentOverride> assignmentOverride = assignmentOverrideWriter.createAssignmentOverride(someCourseId, assignmentOverride1);
        
        System.out.println(assignmentOverride.toString());
        Assert.assertTrue(assignmentOverride.isPresent());
        Assert.assertNotNull(assignmentOverride.get().getId());
        Assert.assertEquals("Sec3", assignmentOverride.get().getTitle());
    }
}