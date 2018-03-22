package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.QuizQuestionReader;
import edu.ksu.canvas.interfaces.QuizQuestionWriter;
import edu.ksu.canvas.model.assignment.QuizQuestion;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.GetQuizQuestionsOptions;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class QuizQuestionImpl extends BaseImpl<QuizQuestion, QuizQuestionReader, QuizQuestionWriter> implements QuizQuestionReader, QuizQuestionWriter {
    private static final Logger LOG = Logger.getLogger(QuizQuestionImpl.class);

    public QuizQuestionImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                            int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    public List<QuizQuestion> getQuizQuestions(GetQuizQuestionsOptions options) throws IOException {
        LOG.debug("Fetching quiz questions for quiz " + options.getQuizId() + " in course " + options.getCourseId());
        String url = buildCanvasUrl("courses/" + options.getCourseId() + "/quizzes/" + options.getQuizId() + "/questions",
                options.getOptionsMap());
        return getListFromCanvas(url);
    }

    @Override
    public boolean deleteQuizQuestion(String courseId, Integer quizId, Integer questionId) throws IOException {
        LOG.debug("Deleting quiz question in course " + courseId + ", quiz " + quizId + ", question " + questionId);
        String url = buildCanvasUrl("courses/" + courseId + "/quizzes/" + quizId + "/questions/" + questionId, Collections.emptyMap());
        Response response = canvasMessenger.deleteFromCanvas(oauthToken, url, Collections.emptyMap());
        int responseCode = response.getResponseCode();
        if (responseCode == 204) {
            return true;
        }
        LOG.error("Canvas returned code " + responseCode + " (success = 204) when deleting question " + questionId);
        return false;
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
