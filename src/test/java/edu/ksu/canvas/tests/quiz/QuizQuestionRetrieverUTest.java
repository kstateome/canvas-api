package edu.ksu.canvas.tests.quiz;

import com.google.gson.JsonSyntaxException;
import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.constants.CanvasConstants;
import edu.ksu.canvas.impl.QuizQuestionImpl;
import edu.ksu.canvas.interfaces.QuizQuestionReader;
import edu.ksu.canvas.model.assignment.QuizQuestion;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.requestOptions.GetQuizQuestionsOptions;
import edu.ksu.canvas.util.CanvasURLBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuizQuestionRetrieverUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private QuizQuestionReader quizQuestionReader;

    @BeforeEach
    void setupData() {
        quizQuestionReader = new QuizQuestionImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    void testRetrieveQuizAnswer() throws Exception {
        String someCourseId = "123456";
        Long someQUizId = 123456L;
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,
                "courses/" + someCourseId + "/quizzes/" + someQUizId + "/questions", Collections.emptyMap());  Response notErroredResponse = new Response();
        notErroredResponse.setErrorHappened(false);
        notErroredResponse.setResponseCode(200);
        fakeRestClient.addSuccessResponse(url, "SampleJson/quiz/QuizQuestionList.json");

        List<QuizQuestion> quizQuestions = quizQuestionReader.getQuizQuestions(new GetQuizQuestionsOptions(someCourseId, someQUizId));
        assertEquals(2, quizQuestions.size());
        assertTrue(quizQuestions.stream().map(QuizQuestion::getQuestionName).filter("Quiz Question 1"::equals).findFirst().isPresent());
        assertTrue(quizQuestions.stream().map(QuizQuestion::getQuestionName).filter("Quiz Question 2"::equals).findFirst().isPresent());
    }

    @Test
    void testListAssignments_responseInvalid() throws Exception {
        String someCourseId = "123456";
        Long someQUizId = 123456L;
        Response erroredResponse = new Response();
        erroredResponse.setResponseCode(401);
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,
                "courses/" + someCourseId + "/quizzes/" + someQUizId + "/questions", Collections.emptyMap());  Response notErroredResponse = new Response();
        fakeRestClient.addSuccessResponse(url, "InvalidJson.json");
        assertThrows(JsonSyntaxException.class, () -> {
            quizQuestionReader.getQuizQuestions(new GetQuizQuestionsOptions(someCourseId, someQUizId));
        });
    }
    
    @Test
    void testSisUserMasqueradeRetrieveQuizAnswer() throws Exception {
        String someUserId = "899123456";
        String someCourseId = "123456";
        Long someQUizId = 123456L;
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/quizzes/" + someQUizId + "/questions?as_user_id=" + CanvasConstants.MASQUERADE_SIS_USER + ":" + someUserId;
        Response notErroredResponse = new Response();
        notErroredResponse.setErrorHappened(false);
        notErroredResponse.setResponseCode(200);
        fakeRestClient.addSuccessResponse(url, "SampleJson/quiz/QuizQuestionList.json");

        List<QuizQuestion> quizQuestions = quizQuestionReader.readAsSisUser(someUserId).getQuizQuestions(new GetQuizQuestionsOptions(someCourseId, someQUizId));
        assertEquals(2, quizQuestions.size());
        assertTrue(quizQuestions.stream().map(QuizQuestion::getQuestionName).filter("Quiz Question 1"::equals).findFirst().isPresent());
        assertTrue(quizQuestions.stream().map(QuizQuestion::getQuestionName).filter("Quiz Question 2"::equals).findFirst().isPresent());
    }

    @Test
    void testSisUserMasqueradeListAssignments_responseInvalid() throws Exception {
        String someUserId = "899123456";
        String someCourseId = "123456";
        Long someQUizId = 123456L;
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/quizzes/" + someQUizId + "/questions?as_user_id=" + CanvasConstants.MASQUERADE_SIS_USER + ":" + someUserId;
        Response erroredResponse = new Response();
        erroredResponse.setResponseCode(401);
        fakeRestClient.addSuccessResponse(url, "InvalidJson.json");
        assertThrows(JsonSyntaxException.class, () -> {
            quizQuestionReader.readAsSisUser(someUserId).getQuizQuestions(new GetQuizQuestionsOptions(someCourseId, someQUizId));
        });

    }

    @Test
    void testCanvasUserMasqueradeRetrieveQuizAnswer() throws Exception {
        String someUserId = "899123456";
        String someCourseId = "123456";
        Long someQUizId = 123456L;
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/quizzes/" + someQUizId + "/questions?as_user_id=" + someUserId;
        Response notErroredResponse = new Response();
        notErroredResponse.setErrorHappened(false);
        notErroredResponse.setResponseCode(200);
        fakeRestClient.addSuccessResponse(url, "SampleJson/quiz/QuizQuestionList.json");

        List<QuizQuestion> quizQuestions = quizQuestionReader.readAsCanvasUser(someUserId).getQuizQuestions(new GetQuizQuestionsOptions(someCourseId, someQUizId));
        assertEquals(2, quizQuestions.size());
        assertTrue(quizQuestions.stream().map(QuizQuestion::getQuestionName).filter("Quiz Question 1"::equals).findFirst().isPresent());
        assertTrue(quizQuestions.stream().map(QuizQuestion::getQuestionName).filter("Quiz Question 2"::equals).findFirst().isPresent());
    }

    @Test
    void testCanvasUserMasqueradeListAssignments_responseInvalid() throws Exception {
        String someUserId = "899123456";
        String someCourseId = "123456";
        Long someQUizId = 123456L;
        String url = baseUrl + "/api/v1/courses/" + someCourseId + "/quizzes/" + someQUizId + "/questions?as_user_id=" + someUserId;
        Response erroredResponse = new Response();
        erroredResponse.setResponseCode(401);
        fakeRestClient.addSuccessResponse(url, "InvalidJson.json");
        assertThrows(JsonSyntaxException.class, () -> {
            quizQuestionReader.readAsCanvasUser(someUserId).getQuizQuestions(new GetQuizQuestionsOptions(someCourseId, someQUizId));
        });

    }

}
