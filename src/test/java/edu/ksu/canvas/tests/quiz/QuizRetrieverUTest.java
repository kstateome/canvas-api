package edu.ksu.canvas.tests.quiz;

import com.google.gson.JsonSyntaxException;
import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.constants.CanvasConstants;
import edu.ksu.canvas.impl.QuizImpl;
import edu.ksu.canvas.interfaces.QuizReader;
import edu.ksu.canvas.model.assignment.Quiz;
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
import java.util.Optional;

public class QuizRetrieverUTest extends CanvasTestBase {
    private static final Logger LOG = Logger.getLogger(QuizRetrieverUTest.class);
    @Autowired
    private FakeRestClient fakeRestClient;
    private QuizReader quizReader;

    @Before
    public void setupData() {
        quizReader = new QuizImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    public void testListCourseQuizzes() throws Exception {
        String someCourseId = "123";
        Quiz quiz1 = new Quiz();
        quiz1.setId(1);
        Quiz quiz2 = new Quiz();
        quiz2.setId(2);
        Response notErroredResponse = new Response();
        notErroredResponse.setErrorHappened(false);
        notErroredResponse.setResponseCode(200);
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,
                "courses/" + someCourseId + "/quizzes", Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/quiz/QuizList.json");

        List<Quiz> quizzess = quizReader.getQuizzesInCourse(someCourseId);
        Assert.assertEquals(2, quizzess.size());
        Assert.assertTrue(quizzess.stream().map(Quiz::getTitle).filter("Quiz1"::equals).findFirst().isPresent());
        Assert.assertTrue(quizzess.stream().map(Quiz::getTitle).filter("Quiz2"::equals).findFirst().isPresent());

    }

    @Test(expected = JsonSyntaxException.class)
    public void testListAssignments_responseInvalid() throws Exception {
        String someCourseId = "123456";
        Response erroredResponse = new Response();
        erroredResponse.setResponseCode(401);
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,
                "courses/" + someCourseId + "/quizzes", Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "InvalidJson.json");

        Assert.assertTrue(quizReader.getQuizzesInCourse(someCourseId).isEmpty());
    }

    @Test
    public void testRetrieveAssignment() throws Exception {
        String someCourseId = "1234";
        String someQuizId = "123";
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,
                "courses/" + someCourseId + "/quizzes/" + someQuizId, Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/quiz/Quiz1.json");
        Optional<Quiz> quiz = quizReader.getSingleQuiz(someCourseId, someQuizId);
        Assert.assertTrue(quiz.isPresent());
        Assert.assertEquals("Quiz1", quiz.map(Quiz::getTitle).orElse(""));
    }

    @Test
    public void testSisUserMasqueradeListCourseQuizzes() throws Exception {
        String someUserId = "899123456";
        String someCourseId = "123";
        Quiz quiz1 = new Quiz();
        quiz1.setId(1);
        Quiz quiz2 = new Quiz();
        quiz2.setId(2);
        Response notErroredResponse = new Response();
        notErroredResponse.setErrorHappened(false);
        notErroredResponse.setResponseCode(200);
        String url =  baseUrl + "/api/v1/courses/" + someCourseId + "/quizzes?as_user_id="
                + CanvasConstants.MASQUERADE_SIS_USER + ":" + someUserId;

        fakeRestClient.addSuccessResponse(url, "SampleJson/quiz/QuizList.json");

        List<Quiz> quizzess = quizReader.readAsSisUser(someUserId).getQuizzesInCourse(someCourseId);
        Assert.assertEquals(2, quizzess.size());
        Assert.assertTrue(quizzess.stream().map(Quiz::getTitle).filter("Quiz1"::equals).findFirst().isPresent());
        Assert.assertTrue(quizzess.stream().map(Quiz::getTitle).filter("Quiz2"::equals).findFirst().isPresent());

    }

    @Test(expected = JsonSyntaxException.class)
    public void testSisUserMasqueradeListAssignments_responseInvalid() throws Exception {
        String someUserId = "899123456";
        String someCourseId = "123456";
        Response erroredResponse = new Response();
        erroredResponse.setResponseCode(401);
        String url =  baseUrl + "/api/v1/courses/" + someCourseId + "/quizzes?as_user_id="
                + CanvasConstants.MASQUERADE_SIS_USER + ":" + someUserId;
        fakeRestClient.addSuccessResponse(url, "InvalidJson.json");

        Assert.assertTrue(quizReader.readAsSisUser(someUserId).getQuizzesInCourse(someCourseId).isEmpty());
    }

    @Test
    public void testSisUserMasqueradeRetrieveAssignment() throws Exception {
        String someUserId = "899123456";
        String someCourseId = "1234";
        String someQuizId = "123";
        String url =  baseUrl + "/api/v1/courses/" + someCourseId + "/quizzes/" + someQuizId + "?as_user_id="
                + CanvasConstants.MASQUERADE_SIS_USER + ":" + someUserId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/quiz/Quiz1.json");
        Optional<Quiz> quiz = quizReader.readAsSisUser(someUserId).getSingleQuiz(someCourseId, someQuizId);
        Assert.assertTrue(quiz.isPresent());
        Assert.assertEquals("Quiz1", quiz.map(Quiz::getTitle).orElse(""));
    }
    @Test
    public void testCanvasUserMasqueradeListCourseQuizzes() throws Exception {
        String someUserId = "899123456";
        String someCourseId = "123";
        Quiz quiz1 = new Quiz();
        quiz1.setId(1);
        Quiz quiz2 = new Quiz();
        quiz2.setId(2);
        Response notErroredResponse = new Response();
        notErroredResponse.setErrorHappened(false);
        notErroredResponse.setResponseCode(200);
        String url =  baseUrl + "/api/v1/courses/" + someCourseId + "/quizzes?as_user_id=" + someUserId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/quiz/QuizList.json");

        List<Quiz> quizzess = quizReader.readAsCanvasUser(someUserId).getQuizzesInCourse(someCourseId);
        Assert.assertEquals(2, quizzess.size());
        Assert.assertTrue(quizzess.stream().map(Quiz::getTitle).filter("Quiz1"::equals).findFirst().isPresent());
        Assert.assertTrue(quizzess.stream().map(Quiz::getTitle).filter("Quiz2"::equals).findFirst().isPresent());

    }

    @Test(expected = JsonSyntaxException.class)
    public void testCanvasUserMasqueradeListAssignments_responseInvalid() throws Exception {
        String someUserId = "899123456";
        String someCourseId = "123456";
        Response erroredResponse = new Response();
        erroredResponse.setResponseCode(401);
        String url =  baseUrl + "/api/v1/courses/" + someCourseId + "/quizzes?as_user_id=" + someUserId;
        fakeRestClient.addSuccessResponse(url, "InvalidJson.json");

        Assert.assertTrue(quizReader.readAsCanvasUser(someUserId).getQuizzesInCourse(someCourseId).isEmpty());
    }

    @Test
    public void testCanvasUserMasqueradeRetrieveAssignment() throws Exception {
        String someUserId = "899123456";
        String someCourseId = "1234";
        String someQuizId = "123";
        String url =  baseUrl + "/api/v1/courses/" + someCourseId + "/quizzes/" + someQuizId+ "?as_user_id=" + someUserId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/quiz/Quiz1.json");
        Optional<Quiz> quiz = quizReader.readAsCanvasUser(someUserId).getSingleQuiz(someCourseId, someQuizId);
        Assert.assertTrue(quiz.isPresent());
        Assert.assertEquals("Quiz1", quiz.map(Quiz::getTitle).orElse(""));
    }
}
