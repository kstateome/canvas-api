package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.QuizQuestionReader;
import edu.ksu.canvas.interfaces.QuizQuestionWriter;
import edu.ksu.canvas.model.assignment.QuizQuestion;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.requestOptions.GetQuizQuestionsOptions;
import edu.ksu.canvas.OauthToken;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class QuizQuestionImpl extends BaseImpl<QuizQuestion, QuizQuestionReader, QuizQuestionWriter> implements QuizQuestionReader, QuizQuestionWriter {
    private static final Logger LOG = Logger.getLogger(QuizQuestionImpl.class);

    public QuizQuestionImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient, int connectTimeout, int readTimout, Integer paginationPageSize) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimout, paginationPageSize);
    }

    @Override
    public List<QuizQuestion> getQuizQuestions(GetQuizQuestionsOptions options) throws IOException {
        String url = buildCanvasUrl("courses/" + options.getCourseId() + "/quizzes/" + options.getQuizId() + "/questions",
                options.getOptionsMap());
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
        return GsonResponseParser.getDefaultGsonParser().fromJson(response.getContent(), listType);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<QuizQuestion>>(){}.getType();
    }

    @Override
    protected Class<QuizQuestion> objectType() {
        return QuizQuestion.class;
    }
}
