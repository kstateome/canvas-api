package edu.ksu.canvas.tests.assignment;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.AssignmentGroupImpl;
import edu.ksu.canvas.interfaces.AssignmentGroupReader;
import edu.ksu.canvas.model.assignment.AssignmentGroup;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.requestOptions.GetAssignmentGroupOptions;
import edu.ksu.canvas.requestOptions.ListAssignmentGroupOptions;
import static org.junit.Assert.*;

public class AssignmentGroupUTest extends CanvasTestBase {
    private final static String courseId = "123456";
    private final static Integer assignmentGroupId = 123;

    @Autowired
    private FakeRestClient fakeRestClient;
    private AssignmentGroupReader reader;

    @Before
    public void setupData() {
        reader = new AssignmentGroupImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    public void listAssignmentGroups() throws Exception {
        String url = baseUrl + "/api/v1/courses/" + courseId + "/assignment_groups";
        fakeRestClient.addSuccessResponse(url, "SampleJson/assignment/AssignmentGroupList.json");

        List<AssignmentGroup> assignmentGroups = reader.listAssignmentGroup(new ListAssignmentGroupOptions(courseId));

        assertEquals(1, assignmentGroups.stream().map(AssignmentGroup::getName).filter("Assignments"::equals).count());
        assertEquals(1, assignmentGroups.stream().map(AssignmentGroup::getName).filter("Exams"::equals).count());
        assertEquals(1, assignmentGroups.stream().map(AssignmentGroup::getName).filter("Imported Assignments"::equals).count());
    }

    @Test
    public void getAssignmentGroup() throws Exception {
        String url = baseUrl + "/api/v1/courses/" + courseId + "/assignment_groups/" + assignmentGroupId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/assignment/AssignmentGroup.json");

        Optional<AssignmentGroup> assignmentGroupResult = reader.getAssignmentGroup(new GetAssignmentGroupOptions(courseId, assignmentGroupId));

        assertTrue("Assignment group must be present in result", assignmentGroupResult.isPresent());
        assertEquals("Assignments", assignmentGroupResult.get().getName());
        assertEquals(new Integer(123), assignmentGroupResult.get().getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidGetAssignmentGroupIncludes() throws Exception {
        new GetAssignmentGroupOptions(courseId, 123).includes(Arrays.asList(GetAssignmentGroupOptions.Include.ASSIGNMENT_VISIBILITY));
    }

    @Test
    public void getAssignmentGroupWithAssignmentsIncluded() throws Exception {
        String url = baseUrl + "/api/v1/courses/" + courseId + "/assignment_groups/" + assignmentGroupId + "?include[]=assignments";
        fakeRestClient.addSuccessResponse(url, "SampleJson/assignment/AssignmentGroupWithAssignments.json");

        Optional<AssignmentGroup> assignmentGroupResult = reader.getAssignmentGroup(
                new GetAssignmentGroupOptions(courseId, assignmentGroupId)
                    .includes(Arrays.asList(GetAssignmentGroupOptions.Include.ASSIGNMENTS)));

        assertTrue("Assignment group with assignments should be present", assignmentGroupResult.isPresent());
        assertEquals("Final Exam", assignmentGroupResult.get().getAssignments().get(0).getName());
    }
}
