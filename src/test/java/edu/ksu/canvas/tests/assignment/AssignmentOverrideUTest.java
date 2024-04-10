package edu.ksu.canvas.tests.assignment;

import com.google.gson.JsonSyntaxException;
import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.AssignmentOverrideImpl;
import edu.ksu.canvas.interfaces.AssignmentOverrideReader;
import edu.ksu.canvas.interfaces.AssignmentOverrideWriter;
import edu.ksu.canvas.model.assignment.AssignmentOverride;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.net.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AssignmentOverrideUTest extends CanvasTestBase {
    private static final Logger LOG = LoggerFactory.getLogger(AssignmentRetrieverUTest.class);
    @Autowired
    private FakeRestClient fakeRestClient;
    private AssignmentOverrideReader assignmentOverrideReader;
    private AssignmentOverrideWriter assignmentOverrideWriter;

    @BeforeEach
    void setupData() {
        assignmentOverrideReader = new AssignmentOverrideImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
        assignmentOverrideWriter = new AssignmentOverrideImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }
    
    @Test
    void testListAssignments_responseInvalid() throws Exception {
        String someCourseId = "14";
        Long someAssignmentId = 80L;
        Response erroredResponse = new Response();
        erroredResponse.setResponseCode(401);
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/assignments/" + someAssignmentId + "/overrides";

        fakeRestClient.addSuccessResponse(url, "InvalidJson.json");

        assertThrows(JsonSyntaxException.class, () -> {
            assertTrue(assignmentOverrideReader.listAssignmentOverrides(someCourseId, someAssignmentId).isEmpty());
        });
    }
    
    @Test
    void testRetrieveAssignmentOverride() throws Exception {
        AssignmentOverride assignmentOverride1 = new AssignmentOverride();
        assignmentOverride1.setId(4L);
        String someCourseId = "14";
        Long someAssignmentId = 80L;
        Long someAssignmentOverrideId = 4L;
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/assignments/" + someAssignmentId + "/overrides/" + someAssignmentOverrideId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/assignment/AssignmentOverride.json");
        Optional<AssignmentOverride> assignmentOverride = assignmentOverrideReader.getAssignmentOverride(someCourseId, someAssignmentId, someAssignmentOverrideId);
        
        assertTrue(assignmentOverride.isPresent());
        assertEquals(new Long(4), assignmentOverride.map(AssignmentOverride::getId).orElse(0L));
    }

    @Test
    void testRetrieveAssignmentOverrides() throws Exception {
        AssignmentOverride assignmentOverride = new AssignmentOverride();
        assignmentOverride.setId(4L);
        String someCourseId = "14";
        Long someAssignmentId = 80L;
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/assignments/" + someAssignmentId + "/overrides";
        fakeRestClient.addSuccessResponse(url, "SampleJson/assignment/AssignmentOverrideList.json");
        List<AssignmentOverride> assignmentOverrides = assignmentOverrideReader.listAssignmentOverrides(someCourseId, someAssignmentId);
        
        assertEquals(3, assignmentOverrides.size());
        assertTrue(assignmentOverrides.stream().map(AssignmentOverride::getTitle).filter("Sec1"::equals).findFirst().isPresent());
        assertTrue(assignmentOverrides.stream().map(AssignmentOverride::getTitle).filter("Sec2"::equals).findFirst().isPresent());
        assertTrue(assignmentOverrides.stream().map(AssignmentOverride::getTitle).filter("Sec3"::equals).findFirst().isPresent());
    }
    
    @Test
    void testCreateAssignmentOverride() throws Exception {
        String someCourseId = "14";
        Long someAssignmentId = 80L;
        AssignmentOverride assignmentOverride1 = new AssignmentOverride();
        assignmentOverride1.setId(4L);
        assignmentOverride1.setTitle("Section1");
        assignmentOverride1.setAssignmentId(someAssignmentId);
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/assignments/" + someAssignmentId + "/overrides";
        fakeRestClient.addSuccessResponse(url, "SampleJson/assignment/AssignmentOverride.json");
        Optional<AssignmentOverride> assignmentOverride = assignmentOverrideWriter.createAssignmentOverride(someCourseId, assignmentOverride1);
        
        System.out.println(assignmentOverride.toString());
        assertTrue(assignmentOverride.isPresent());
        assertNotNull(assignmentOverride.get().getId());
        assertEquals("Sec3", assignmentOverride.get().getTitle());
    }
}