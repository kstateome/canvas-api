package edu.ksu.canvas.impl;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.ksu.canvas.interfaces.QuizReader;
import edu.ksu.canvas.interfaces.QuizWriter;
import edu.ksu.canvas.model.quizzes.Quiz;
import edu.ksu.canvas.model.quizzes.QuizQuestion;
import edu.ksu.canvas.model.quizzes.QuizSubmission;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.util.CanvasURLBuilder;
import edu.ksu.lti.error.MessageUndeliverableException;
import edu.ksu.lti.error.OauthTokenRequiredException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class QuizImpl extends  BaseImpl implements QuizReader, QuizWriter {
    private static final Logger LOG = Logger.getLogger(QuizReader.class);

    public QuizImpl(String canvasBaseUrl, Integer apiVersion, String oauthToken, RestClient restClient) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient);
    }

    @Override
    public Optional<Quiz> getSingleQuiz(String oauthToken, String courseId, String quizId) throws OauthTokenRequiredException, IOException {
        String url = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion,
                "courses/" + courseId + "/quizzes/" + quizId, Collections.emptyMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToObject(Quiz.class, response);
    }

    @Override
    public List<Quiz> getQuizzesInCourse(String oauthToken, String courseId) throws OauthTokenRequiredException, IOException {
        String url = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion,
                "courses/" + courseId + "/quizzes", Collections.emptyMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToList(Quiz.class, response);
    }

    @Override
    public Optional<Quiz> updateQuiz(String oauthToken, Quiz quiz, String courseId) throws MessageUndeliverableException, IOException, OauthTokenRequiredException {
        String url = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion,
                "courses/" + courseId + "/quizzes/" + quiz.getId(), Collections.emptyMap());
        Response response = canvasMessenger.sendToJsonCanvas(oauthToken, url,
                getDefaultGsonParser().toJsonTree(quiz).getAsJsonObject());
        return responseParser.parseToObject(Quiz.class, response);
    }

    @Override
    public List<QuizQuestion> getQuizQuestions(String oauthToken, String courseId, String quizId) throws OauthTokenRequiredException, IOException {
        String url = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion,
                "courses/" + courseId + "/quizzes/" + quizId + "/questions", Collections.emptyMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToList(QuizQuestion.class, response);

    }

    @Override
    public List<QuizSubmission> getQuizSubmissions(String oauthToken, String courseId, String quizId) throws OauthTokenRequiredException, IOException {
        String url = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion,
                "courses/" + courseId + "/quizzes/" + quizId + "/submissions", Collections.emptyMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToList(QuizSubmission.class, response);
    }


}
