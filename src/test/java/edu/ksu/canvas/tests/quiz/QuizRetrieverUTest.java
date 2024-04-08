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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuizRetrieverUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private QuizReader quizReader;

    @BeforeEach
    void setupData() {
        quizReader = new QuizImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    void testListCourseQuizzes() throws Exception {
        String someCourseId = "123";
        Quiz quiz1 = new Quiz();
        quiz1.setId(1L);
        Quiz quiz2 = new Quiz();
        quiz2.setId(2L);
        Response notErroredResponse = new Response();
        notErroredResponse.setErrorHappened(false);
        notErroredResponse.setResponseCode(200);
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,
                "courses/" + someCourseId + "/quizzes", Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/quiz/QuizList.json");

        List<Quiz> quizzess = quizReader.getQuizzesInCourse(someCourseId);
        assertEquals(2, quizzess.size());
        assertTrue(quizzess.stream().map(Quiz::getTitle).filter("Quiz1"::equals).findFirst().isPresent());
        assertTrue(quizzess.stream().map(Quiz::getTitle).filter("Quiz2"::equals).findFirst().isPresent());

    }

    @Test
    void testListAssignments_responseInvalid() throws Exception {
        String someCourseId = "123456";
        Response erroredResponse = new Response();
        erroredResponse.setResponseCode(401);
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,
                "courses/" + someCourseId + "/quizzes", Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "InvalidJson.json");
        assertThrows(JsonSyntaxException.class, () -> {
            quizReader.getQuizzesInCourse(someCourseId);
        });

    }

    @Test
    void testRetrieveAssignment() throws Exception {
        String someCourseId = "1234";
        String someQuizId = "123";
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,
                "courses/" + someCourseId + "/quizzes/" + someQuizId, Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/quiz/Quiz1.json");
        Optional<Quiz> quiz = quizReader.getSingleQuiz(someCourseId, someQuizId);
        assertTrue(quiz.isPresent());
        assertEquals("Quiz1", quiz.map(Quiz::getTitle).orElse(""));
    }

    @Test
    void testSisUserMasqueradeListCourseQuizzes() throws Exception {
        String someUserId = "899123456";
        String someCourseId = "123";
        Quiz quiz1 = new Quiz();
        quiz1.setId(1L);
        Quiz quiz2 = new Quiz();
        quiz2.setId(2L);
        Response notErroredResponse = new Response();
        notErroredResponse.setErrorHappened(false);
        notErroredResponse.setResponseCode(200);
        String url =  baseUrl + "/api/v1/courses/" + someCourseId + "/quizzes?as_user_id="
                + CanvasConstants.MASQUERADE_SIS_USER + ":" + someUserId;

        fakeRestClient.addSuccessResponse(url, "SampleJson/quiz/QuizList.json");

        List<Quiz> quizzess = quizReader.readAsSisUser(someUserId).getQuizzesInCourse(someCourseId);
        assertEquals(2, quizzess.size());
        assertTrue(quizzess.stream().map(Quiz::getTitle).filter("Quiz1"::equals).findFirst().isPresent());
        assertTrue(quizzess.stream().map(Quiz::getTitle).filter("Quiz2"::equals).findFirst().isPresent());

    }

    @Test
    void testSisUserMasqueradeListAssignments_responseInvalid() throws Exception {
        String someUserId = "899123456";
        String someCourseId = "123456";
        Response erroredResponse = new Response();
        erroredResponse.setResponseCode(401);
        String url =  baseUrl + "/api/v1/courses/" + someCourseId + "/quizzes?as_user_id="
                + CanvasConstants.MASQUERADE_SIS_USER + ":" + someUserId;

        fakeRestClient.addSuccessResponse(url, "InvalidJson.json");
        assertThrows(JsonSyntaxException.class, () -> {
            quizReader.readAsSisUser(someUserId).getQuizzesInCourse(someCourseId);
        });

    }

    @Test
    void testSisUserMasqueradeRetrieveAssignment() throws Exception {
        String someUserId = "899123456";
        String someCourseId = "1234";
        String someQuizId = "123";
        String url =  baseUrl + "/api/v1/courses/" + someCourseId + "/quizzes/" + someQuizId + "?as_user_id="
                + CanvasConstants.MASQUERADE_SIS_USER + ":" + someUserId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/quiz/Quiz1.json");
        Optional<Quiz> quiz = quizReader.readAsSisUser(someUserId).getSingleQuiz(someCourseId, someQuizId);
        assertTrue(quiz.isPresent());
        assertEquals("Quiz1", quiz.map(Quiz::getTitle).orElse(""));
    }
    @Test
    void testCanvasUserMasqueradeListCourseQuizzes() throws Exception {
        String someUserId = "899123456";
        String someCourseId = "123";
        Quiz quiz1 = new Quiz();
        quiz1.setId(1L);
        Quiz quiz2 = new Quiz();
        quiz2.setId(2L);
        Response notErroredResponse = new Response();
        notErroredResponse.setErrorHappened(false);
        notErroredResponse.setResponseCode(200);
        String url =  baseUrl + "/api/v1/courses/" + someCourseId + "/quizzes?as_user_id=" + someUserId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/quiz/QuizList.json");

        List<Quiz> quizzess = quizReader.readAsCanvasUser(someUserId).getQuizzesInCourse(someCourseId);
        assertEquals(2, quizzess.size());
        assertTrue(quizzess.stream().map(Quiz::getTitle).filter("Quiz1"::equals).findFirst().isPresent());
        assertTrue(quizzess.stream().map(Quiz::getTitle).filter("Quiz2"::equals).findFirst().isPresent());

    }

    @Test
    void testCanvasUserMasqueradeListAssignments_responseInvalid() throws Exception {
        String someUserId = "899123456";
        String someCourseId = "123456";
        Response erroredResponse = new Response();
        erroredResponse.setResponseCode(401);
        String url =  baseUrl + "/api/v1/courses/" + someCourseId + "/quizzes?as_user_id=" + someUserId;

        fakeRestClient.addSuccessResponse(url, "InvalidJson.json");
        assertThrows(JsonSyntaxException.class, () -> {
            quizReader.readAsCanvasUser(someUserId).getQuizzesInCourse(someCourseId);
        });

    }

    @Test
    void testCanvasUserMasqueradeRetrieveAssignment() throws Exception {
        String someUserId = "899123456";
        String someCourseId = "1234";
        String someQuizId = "123";
        String url =  baseUrl + "/api/v1/courses/" + someCourseId + "/quizzes/" + someQuizId+ "?as_user_id=" + someUserId;
        fakeRestClient.addSuccessResponse(url, "SampleJson/quiz/Quiz1.json");
        Optional<Quiz> quiz = quizReader.readAsCanvasUser(someUserId).getSingleQuiz(someCourseId, someQuizId);
        assertTrue(quiz.isPresent());
        assertEquals("Quiz1", quiz.map(Quiz::getTitle).orElse(""));
    }
}
