package edu.ksu.canvas.tests.quiz;

import com.google.gson.JsonSyntaxException;
import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.constants.CanvasConstants;
import edu.ksu.canvas.impl.QuizSubmissionImpl;
import edu.ksu.canvas.interfaces.QuizSubmissionReader;
import edu.ksu.canvas.model.assignment.QuizSubmission;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.util.CanvasURLBuilder;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

public class QuizSubmissionRetrieverUTest extends CanvasTestBase {
    private static final Logger LOG = Logger.getLogger(QuizSubmissionRetrieverUTest.class);
    @Autowired
    private FakeRestClient fakeRestClient;
    private QuizSubmissionReader quizSubmissionReader;

    @Before
    public void setUpData() {
        quizSubmissionReader = new QuizSubmissionImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    public void testRetrieveQuizSubmissions() throws Exception{
        String someCourseId = "123456";
        String someQuizId = "1234556";
        Response notErroredResponse = new Response();
        notErroredResponse.setErrorHappened(false);
        notErroredResponse.setResponseCode(200);
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,
                "courses/" + someCourseId + "/quizzes/" + someQuizId + "/submissions", Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/quiz/QuizSubmissions.json");

        List<QuizSubmission> submissions = quizSubmissionReader.getQuizSubmissions(someCourseId, someQuizId);
        Assert.assertEquals(2, submissions.size());
        Assert.assertTrue(submissions.stream().map(QuizSubmission::getId).filter(new Integer(1)::equals).findFirst().isPresent());
        Assert.assertTrue(submissions.stream().map(QuizSubmission::getId).filter(new Integer(2)::equals).findFirst().isPresent());
    }

    @Test(expected = JsonSyntaxException.class)
    public void testQuizSubmissions_responseInvalid() throws Exception {
        String someCourseId = "123456";
        String someQuizId = "1234556";
        Response erroredResponse = new Response();
        erroredResponse.setResponseCode(401);
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,
                "courses/" + someCourseId + "/quizzes/" + someQuizId + "/submissions", Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "InvalidJson.json");

        Assert.assertTrue(quizSubmissionReader.getQuizSubmissions(someCourseId, someQuizId).isEmpty());
    }
    
    @Test
    public void testSisUserMasqueradeRetrieveQuizSubmissions() throws Exception{
        String someUserId = "8991123123";
        String someCourseId = "123456";
        String someQuizId = "1234556";
        Response notErroredResponse = new Response();
        notErroredResponse.setErrorHappened(false);
        notErroredResponse.setResponseCode(200);
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/quizzes/" + someQuizId + "/submissions?as_user_id="
                + CanvasConstants.MASQUERADE_SIS_USER + ":" + someUserId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/quiz/QuizSubmissions.json");

        List<QuizSubmission> submissions = quizSubmissionReader.readAsSisUser(someUserId).getQuizSubmissions(someCourseId, someQuizId);
        Assert.assertEquals(2, submissions.size());
        Assert.assertTrue(submissions.stream().map(QuizSubmission::getId).filter(new Integer(1)::equals).findFirst().isPresent());
        Assert.assertTrue(submissions.stream().map(QuizSubmission::getId).filter(new Integer(2)::equals).findFirst().isPresent());
    }

    @Test(expected = JsonSyntaxException.class)
    public void testSisUserMasqueradeQuizSubmissions_responseInvalid() throws Exception {
        String someUserId = "8991123123";
        String someCourseId = "123456";
        String someQuizId = "1234556";
        Response erroredResponse = new Response();
        erroredResponse.setResponseCode(401);
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/quizzes/" + someQuizId + "/submissions?as_user_id="
                + CanvasConstants.MASQUERADE_SIS_USER + ":" + someUserId;
        fakeRestClient.addSuccessResponse(url, "InvalidJson.json");

        Assert.assertTrue(quizSubmissionReader.readAsSisUser(someUserId).getQuizSubmissions(someCourseId, someQuizId).isEmpty());
    }

    @Test
    public void testCanvasUserMasqueradeRetrieveQuizSubmissions() throws Exception{
        String someUserId = "8991123123";
        String someCourseId = "123456";
        String someQuizId = "1234556";
        Response notErroredResponse = new Response();
        notErroredResponse.setErrorHappened(false);
        notErroredResponse.setResponseCode(200);
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/quizzes/" + someQuizId + "/submissions?as_user_id=" + someUserId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/quiz/QuizSubmissions.json");

        List<QuizSubmission> submissions = quizSubmissionReader.readAsCanvasUser(someUserId).getQuizSubmissions(someCourseId, someQuizId);
        Assert.assertEquals(2, submissions.size());
        Assert.assertTrue(submissions.stream().map(QuizSubmission::getId).filter(new Integer(1)::equals).findFirst().isPresent());
        Assert.assertTrue(submissions.stream().map(QuizSubmission::getId).filter(new Integer(2)::equals).findFirst().isPresent());
    }

    @Test(expected = JsonSyntaxException.class)
    public void testCanvasUserMasqueradeQuizSubmissions_responseInvalid() throws Exception {
        String someUserId = "8991123123";
        String someCourseId = "123456";
        String someQuizId = "1234556";
        Response erroredResponse = new Response();
        erroredResponse.setResponseCode(401);
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/quizzes/" + someQuizId + "/submissions?as_user_id=" + someUserId;
        fakeRestClient.addSuccessResponse(url, "InvalidJson.json");

        Assert.assertTrue(quizSubmissionReader.readAsCanvasUser(someUserId).getQuizSubmissions(someCourseId, someQuizId).isEmpty());
    }
}
