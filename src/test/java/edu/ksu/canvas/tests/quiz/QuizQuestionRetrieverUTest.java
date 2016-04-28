package edu.ksu.canvas.tests.quiz;

import com.google.gson.JsonSyntaxException;
import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.impl.QuizQuestionImpl;
import edu.ksu.canvas.interfaces.QuizQuestionReader;
import edu.ksu.canvas.model.quizzes.QuizQuestion;
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

public class QuizQuestionRetrieverUTest extends CanvasTestBase {
    private static final Logger LOG = Logger.getLogger(QuizQuestionRetrieverUTest.class);
    @Autowired
    private FakeRestClient fakeRestClient;
    private QuizQuestionReader quizQuestionReader;

    @Before
    public void setupData() {
        quizQuestionReader = new QuizQuestionImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT);
    }

    @Test
    public void testRetrieveQuizAnswer() throws Exception {
        String someCourseId = "123456";
        String someQUizId = "123456";
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,
                "courses/" + someCourseId + "/quizzes/" + someQUizId + "/questions", Collections.emptyMap());  Response notErroredResponse = new Response();
        notErroredResponse.setErrorHappened(false);
        notErroredResponse.setResponseCode(200);
        fakeRestClient.addSuccessResponse(url, "SampleJson/quiz/QuizQuestionList.json");

        List<QuizQuestion> quizQuestions = quizQuestionReader.getQuizQuestions(someCourseId, someQUizId);
        Assert.assertEquals(2, quizQuestions.size());
        Assert.assertTrue(quizQuestions.stream().map(QuizQuestion::getQuestion_name).filter("Quiz Question 1"::equals).findFirst().isPresent());
        Assert.assertTrue(quizQuestions.stream().map(QuizQuestion::getQuestion_name).filter("Quiz Question 2"::equals).findFirst().isPresent());
    }
    @Test(expected = InvalidOauthTokenException.class)
    public void testListAssignments_canvasError() throws Exception {
        String someCourseId = "123456";
        String someQUizId = "123456";
        Response erroredResponse = new Response();
        erroredResponse.setErrorHappened(true);
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,
                "courses/" + someCourseId + "/quizzes/" + someQUizId + "/questions", Collections.emptyMap());  Response notErroredResponse = new Response();
        fakeRestClient.add401Response(url, "SampleJson/quiz/QuizQuestionList.json");
        quizQuestionReader.getQuizQuestions(someCourseId, someQUizId);
    }

    @Test(expected = JsonSyntaxException.class)
    public void testListAssignments_responseInvalid() throws Exception {
        String someCourseId = "123456";
        String someQUizId = "123456";
        Response erroredResponse = new Response();
        erroredResponse.setResponseCode(401);
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,
                "courses/" + someCourseId + "/quizzes/" + someQUizId + "/questions", Collections.emptyMap());  Response notErroredResponse = new Response();
        fakeRestClient.addSuccessResponse(url, "InvalidJson.json");

        Assert.assertTrue(quizQuestionReader.getQuizQuestions(someCourseId, someQUizId).isEmpty());
    }

}
