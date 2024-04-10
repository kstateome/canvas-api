package edu.ksu.canvas.tests.quiz;

import com.google.gson.JsonSyntaxException;
import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.constants.CanvasConstants;
import edu.ksu.canvas.impl.QuizSubmissionImpl;
import edu.ksu.canvas.interfaces.QuizSubmissionReader;
import edu.ksu.canvas.model.assignment.QuizSubmission;
import edu.ksu.canvas.model.assignment.QuizSubmissionResponse;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.requestOptions.GetQuizSubmissionsOptions;
import edu.ksu.canvas.util.CanvasURLBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuizSubmissionRetrieverUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private QuizSubmissionReader quizSubmissionReader;

    @BeforeEach
    void setUpData() {
        quizSubmissionReader = new QuizSubmissionImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    void testRetrieveQuizSubmissions() throws Exception{
        String someCourseId = "123456";
        String someQuizId = "1234556";
        Response notErroredResponse = new Response();
        notErroredResponse.setErrorHappened(false);
        notErroredResponse.setResponseCode(200);
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,
                "courses/" + someCourseId + "/quizzes/" + someQuizId + "/submissions", Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/quiz/QuizSubmissions.json");

        List<QuizSubmission> submissions = quizSubmissionReader.getQuizSubmissions(someCourseId, someQuizId);
        assertEquals(2, submissions.size());
        assertTrue(submissions.stream().map(QuizSubmission::getId).filter(Long.valueOf(1)::equals).findFirst().isPresent());
        assertTrue(submissions.stream().map(QuizSubmission::getId).filter(Long.valueOf(2)::equals).findFirst().isPresent());
    }

    @Test
    void testQuizSubmissions_responseInvalid() throws Exception {
        String someCourseId = "123456";
        String someQuizId = "1234556";
        Response erroredResponse = new Response();
        erroredResponse.setResponseCode(401);
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,
                "courses/" + someCourseId + "/quizzes/" + someQuizId + "/submissions", Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "InvalidJson.json");
        assertThrows(JsonSyntaxException.class, () -> {
            quizSubmissionReader.getQuizSubmissions(someCourseId, someQuizId);
        });

    }

    @Test
    void testQuizSubmissions_includeUsers() throws Exception {
        final String someCourseId = "25132";
        final String someQuizId = "61279";
        final Response notErroredResponse = new Response();
        notErroredResponse.setErrorHappened(false);
        notErroredResponse.setResponseCode(200);
        final String url = baseUrl + "/api/v1/courses/" + someCourseId + "/quizzes/" + someQuizId + "/submissions?include[]=user";
        fakeRestClient.addSuccessResponse(url, "SampleJson/quiz/QuizSubmissionsIncludeUser.json");
        final GetQuizSubmissionsOptions options = new GetQuizSubmissionsOptions(someCourseId, someQuizId, GetQuizSubmissionsOptions.Include.USER);
        final QuizSubmissionResponse response = quizSubmissionReader.getQuizSubmissions(options);

        assertNotNull(response);
        assertNotNull(response.getQuizSubmissions());
        assertNotNull(response.getUsers());
        assertEquals(2, response.getQuizSubmissions().size());
        assertEquals(2, response.getUsers().size());
        assertEquals((Long) 508566L, response.getQuizSubmissions().get(0).getId());
        assertEquals("User 218826", response.getUser(218826L).get().getName());
    }

    @Test
    void testSisUserMasqueradeRetrieveQuizSubmissions() throws Exception{
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
        assertEquals(2, submissions.size());
        assertTrue(submissions.stream().map(QuizSubmission::getId).filter(new Long(1)::equals).findFirst().isPresent());
        assertTrue(submissions.stream().map(QuizSubmission::getId).filter(new Long(2)::equals).findFirst().isPresent());
    }

    @Test
    void testSisUserMasqueradeQuizSubmissions_responseInvalid() throws Exception {
        String someUserId = "8991123123";
        String someCourseId = "123456";
        String someQuizId = "1234556";
        Response erroredResponse = new Response();
        erroredResponse.setResponseCode(401);
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/quizzes/" + someQuizId + "/submissions?as_user_id="
                + CanvasConstants.MASQUERADE_SIS_USER + ":" + someUserId;

        fakeRestClient.addSuccessResponse(url, "InvalidJson.json");
        assertThrows(JsonSyntaxException.class, () -> {
            quizSubmissionReader.readAsSisUser(someUserId).getQuizSubmissions(someCourseId, someQuizId);
        });

    }

    @Test
    void testCanvasUserMasqueradeRetrieveQuizSubmissions() throws Exception{
        String someUserId = "8991123123";
        String someCourseId = "123456";
        String someQuizId = "1234556";
        Response notErroredResponse = new Response();
        notErroredResponse.setErrorHappened(false);
        notErroredResponse.setResponseCode(200);
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/quizzes/" + someQuizId + "/submissions?as_user_id=" + someUserId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/quiz/QuizSubmissions.json");

        List<QuizSubmission> submissions = quizSubmissionReader.readAsCanvasUser(someUserId).getQuizSubmissions(someCourseId, someQuizId);
        assertEquals(2, submissions.size());
        assertTrue(submissions.stream().map(QuizSubmission::getId).filter(Long.valueOf(1)::equals).findFirst().isPresent());
        assertTrue(submissions.stream().map(QuizSubmission::getId).filter(Long.valueOf(2)::equals).findFirst().isPresent());
    }

    @Test
    void testCanvasUserMasqueradeQuizSubmissions_responseInvalid() throws Exception {
        String someUserId = "8991123123";
        String someCourseId = "123456";
        String someQuizId = "1234556";
        Response erroredResponse = new Response();
        erroredResponse.setResponseCode(401);
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/quizzes/" + someQuizId + "/submissions?as_user_id=" + someUserId;
        fakeRestClient.addSuccessResponse(url, "InvalidJson.json");
        assertThrows(JsonSyntaxException.class, () -> {
            quizSubmissionReader.readAsCanvasUser(someUserId).getQuizSubmissions(someCourseId, someQuizId);
        });

    }
}
