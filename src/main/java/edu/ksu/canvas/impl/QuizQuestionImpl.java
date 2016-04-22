package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.QuizQuestionReader;
import edu.ksu.canvas.interfaces.QuizQuestionWriter;
import edu.ksu.canvas.model.quizzes.QuizQuestion;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.util.CanvasURLBuilder;
import edu.ksu.canvas.exception.OauthTokenRequiredException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class QuizQuestionImpl extends  BaseImpl implements QuizQuestionReader, QuizQuestionWriter {
    private static final Logger LOG = Logger.getLogger(QuizQuestionImpl.class);

    public QuizQuestionImpl(String canvasBaseUrl, Integer apiVersion, String oauthToken, RestClient restClient) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient);
    }

    @Override
    public List<QuizQuestion> getQuizQuestions(String oauthToken, String courseId, String quizId) throws OauthTokenRequiredException, IOException {
        String url = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion,
                "courses/" + courseId + "/quizzes/" + quizId + "/questions", Collections.emptyMap());
        List<Response> responses = canvasMessenger.getFromCanvas(oauthToken, url);
        return parseQuizQuestionList(responses);

    }

    private List <QuizQuestion> parseQuizQuestionList(final List<Response> responses) {
        return responses.stream().
                map(this::parseQuizQuestionList).
                flatMap(Collection::stream).
                collect(Collectors.toList());
    }

    private List<QuizQuestion> parseQuizQuestionList(final Response response) {
        Type listType = new TypeToken<List<QuizQuestion>>(){}.getType();
        return getDefaultGsonParser().fromJson(response.getContent(), listType);
    }

    @Override
    protected List parseListResponse(Response response) {
        return null;
    }

    @Override
    protected Optional parseObjectResponse(Response response) {
        return null;
    }
}
