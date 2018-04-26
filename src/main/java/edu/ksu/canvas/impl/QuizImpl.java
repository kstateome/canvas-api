package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.QuizReader;
import edu.ksu.canvas.interfaces.QuizWriter;
import edu.ksu.canvas.model.assignment.Quiz;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class QuizImpl extends BaseImpl<Quiz, QuizReader, QuizWriter> implements QuizReader, QuizWriter {
    private static final Logger LOG = Logger.getLogger(QuizReader.class);

    public QuizImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                    int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    public Optional<Quiz> getSingleQuiz(String courseId, String quizId) throws IOException {
        LOG.debug("Retrieving single quiz " + quizId + " in course " + courseId);
        String url = buildCanvasUrl("courses/" + courseId + "/quizzes/" + quizId, Collections.emptyMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToObject(Quiz.class, response);
    }

    @Override
    public List<Quiz> getQuizzesInCourse(String courseId) throws IOException {
        LOG.debug("Getting quizzes for course " + courseId);
        String url = buildCanvasUrl("courses/" + courseId + "/quizzes", Collections.emptyMap());
        List<Response> responses = canvasMessenger.getFromCanvas(oauthToken, url);
        return parseQuizList(responses);
    }

    @Override
    public Optional<Quiz> updateQuiz(Quiz quiz, String courseId) throws IOException {
        LOG.debug("Updating quiz " + quiz.getId() + " in course " + courseId);
        String url = buildCanvasUrl("courses/" + courseId + "/quizzes/" + quiz.getId(), Collections.emptyMap());
        Response response = canvasMessenger.sendJsonPutToCanvas(oauthToken, url,quiz.toJsonObject(serializeNulls));
        return responseParser.parseToObject(Quiz.class, response);
    }

    private List <Quiz> parseQuizList(final List<Response> responses) {
        return responses.stream().
                map(this::parseQuizList).
                flatMap(Collection::stream).
                collect(Collectors.toList());
    }

    private List<Quiz> parseQuizList(final Response response) {
        Type listType = new TypeToken<List<Quiz>>(){}.getType();
        return GsonResponseParser.getDefaultGsonParser(serializeNulls).fromJson(response.getContent(), listType);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<Quiz>>(){}.getType();
    }

    @Override
    protected Class<Quiz> objectType() {
        return Quiz.class;
    }

}
