package edu.ksu.canvas.tests.submission;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.SubmissionImpl;
import edu.ksu.canvas.interfaces.SubmissionReader;
import edu.ksu.canvas.model.assignment.Submission;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.requestOptions.GetMultipleSubmissionsOptions;

public class SubmissionUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private SubmissionReader submissionReader;
    
    @Before
    public void setupData() {
    	submissionReader = new SubmissionImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }
    
    @Test
    public void testGetCourseSubmissionMultipleAssignments() throws Exception {
        String someCourseId = "123456";
        Response notErroredResponse = new Response();
        notErroredResponse.setErrorHappened(false);
        notErroredResponse.setResponseCode(200);
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/students/submissions";
        fakeRestClient.addSuccessResponse(url, "SampleJson/submission/SubmissionList.json");

        List<Submission> submissions = submissionReader.getCourseSubmissionMultipleAssignments(new GetMultipleSubmissionsOptions(someCourseId));
        Assert.assertEquals(2, submissions.size());
        Assert.assertTrue(submissions.stream().map(Submission::getGrade).filter("A-"::equals).findFirst().isPresent());
        Assert.assertTrue(submissions.stream().map(Submission::getGrade).filter("B-"::equals).findFirst().isPresent());
    }
}
