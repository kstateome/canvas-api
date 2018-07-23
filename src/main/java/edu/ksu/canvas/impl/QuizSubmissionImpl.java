package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;

import edu.ksu.canvas.interfaces.QuizSubmissionReader;
import edu.ksu.canvas.interfaces.QuizSubmissionWriter;
import edu.ksu.canvas.model.assignment.QuizSubmission;
import edu.ksu.canvas.model.assignment.QuizSubmissionResponse;
import edu.ksu.canvas.model.wrapper.QuizSubmissionWrapper;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.CompleteQuizSubmissionOptions;
import edu.ksu.canvas.requestOptions.GetQuizSubmissionsOptions;
import edu.ksu.canvas.requestOptions.StartQuizSubmissionOptions;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class QuizSubmissionImpl extends BaseImpl<QuizSubmission, QuizSubmissionReader, QuizSubmissionWriter> implements QuizSubmissionReader, QuizSubmissionWriter {
    private static final Logger LOG = Logger.getLogger(QuizSubmissionImpl.class);

     public QuizSubmissionImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                               int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
         super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                 paginationPageSize, serializeNulls);
     }

    @Override
    public List<QuizSubmission> getQuizSubmissions(final String courseId, final String quizId) throws IOException {
        return getQuizSubmissions(new GetQuizSubmissionsOptions(courseId, quizId)).getQuizSubmissions();
    }

    @Override
    public QuizSubmissionResponse getQuizSubmissions(final GetQuizSubmissionsOptions options) throws IOException {
        final String url = buildCanvasUrl("courses/" + options.getCourseId() + "/quizzes/" + options.getQuizId() + "/submissions", options.getOptionsMap());
        final List<Response> responses = canvasMessenger.getFromCanvas(oauthToken, url);
        final QuizSubmissionWrapper wrapper = parseQuizSubmissionResponses(responses);
        return new QuizSubmissionResponse(wrapper.getQuizSubmissions(), wrapper.getUsers());
    }

    @Override
    public Optional<QuizSubmission> startQuizSubmission(StartQuizSubmissionOptions options) throws IOException {
        String url = buildCanvasUrl("courses/" + options.getCourseId() + "/quizzes/" + options.getQuizId() + "/submissions", options.getOptionsMap());
        Response response = canvasMessenger.sendToCanvas(oauthToken, url, Collections.emptyMap());
        return Optional.of(parseQuizSubmissionResponse(response).getQuizSubmissions().get(0));
    }

    @Override
    public Optional<QuizSubmission> completeQuizSubmission(CompleteQuizSubmissionOptions options) throws IOException {
        LOG.debug("completing quiz submission for user/course/quiz: " + masqueradeAs + "/" + options.getCourseId() + "/" + options.getQuizId());
        String url = buildCanvasUrl("courses/" + options.getCourseId() + "/quizzes/" + options.getQuizId() +
                "/submissions/" + options.getSubmissionId() + "/complete", options.getOptionsMap());
        Response response = canvasMessenger.sendToCanvas(oauthToken, url, Collections.emptyMap());
        //This call comes back from Canvas as an object containing a list of quiz submissions.
        //No clue why it doesn't just return a raw quiz submission.
        return Optional.of(parseQuizSubmissionResponse(response).getQuizSubmissions().get(0));
    }

    private QuizSubmissionWrapper parseQuizSubmissionResponses(final List<Response> responses) {
        return responses.stream()
                .map(this::parseQuizSubmissionResponse)
                .collect(QuizSubmissionWrapper::new, QuizSubmissionImpl::accumulateQuizSubmissions, QuizSubmissionImpl::accumulateQuizSubmissions);
    }

    private QuizSubmissionWrapper parseQuizSubmissionResponse(final Response response) {
        return GsonResponseParser.getDefaultGsonParser(serializeNulls).fromJson(response.getContent(), QuizSubmissionWrapper.class);
    }

    private static void accumulateQuizSubmissions(final QuizSubmissionWrapper result, final QuizSubmissionWrapper element) {
        result.getQuizSubmissions().addAll(element.getQuizSubmissions());
        result.getUsers().addAll(element.getUsers());
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<QuizSubmission>>(){}.getType();
    }

    @Override
    protected Class<QuizSubmission> objectType() {
        return QuizSubmission.class;
    }

}
