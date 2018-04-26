package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;

import edu.ksu.canvas.interfaces.QuizSubmissionReader;
import edu.ksu.canvas.interfaces.QuizSubmissionWriter;
import edu.ksu.canvas.model.assignment.QuizSubmission;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.CompleteQuizSubmissionOptions;
import edu.ksu.canvas.requestOptions.StartQuizSubmissionOptions;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class QuizSubmissionImpl extends BaseImpl<QuizSubmission, QuizSubmissionReader, QuizSubmissionWriter> implements QuizSubmissionReader, QuizSubmissionWriter {
    private static final Logger LOG = Logger.getLogger(QuizSubmissionImpl.class);

     public QuizSubmissionImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                               int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
         super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                 paginationPageSize, serializeNulls);
     }

    @Override
    public List<QuizSubmission> getQuizSubmissions(String courseId, String quizId) throws IOException {
        String url = buildCanvasUrl("courses/" + courseId + "/quizzes/" + quizId + "/submissions", Collections.emptyMap());
        List<Response> responses = canvasMessenger.getFromCanvas(oauthToken, url);
        return parseQuizSubmissionList(responses);
    }

    @Override
    public Optional<QuizSubmission> startQuizSubmission(StartQuizSubmissionOptions options) throws IOException {
        String url = buildCanvasUrl("courses/" + options.getCourseId() + "/quizzes/" + options.getQuizId() + "/submissions", options.getOptionsMap());
        Response response = canvasMessenger.sendToCanvas(oauthToken, url,Collections.emptyMap());
        return Optional.of(parseQuizSubmissionList(response).get(0));
    }

    @Override
    public Optional<QuizSubmission> completeQuizSubmission(CompleteQuizSubmissionOptions options) throws IOException {
        LOG.debug("completing quiz submission for user/course/quiz: " + masqueradeAs + "/" + options.getCourseId() + "/" + options.getQuizId());
        String url = buildCanvasUrl("courses/" + options.getCourseId() + "/quizzes/" + options.getQuizId() +
                "/submissions/" + options.getSubmissionId() + "/complete", options.getOptionsMap());
        Response response = canvasMessenger.sendToCanvas(oauthToken, url, Collections.emptyMap());
        //This call comes back from Canvas as an object containing a list of quiz submissions.
        //No clue why it doesn't just return a raw quiz submission.
        return Optional.of(parseQuizSubmissionList(response).get(0));
    }

    private List <QuizSubmission> parseQuizSubmissionList(final List<Response> responses) {
        return responses.stream().
                map(this::parseQuizSubmissionList).
                flatMap(Collection::stream).
                collect(Collectors.toList());
    }

    private List<QuizSubmission> parseQuizSubmissionList(final Response response) {
        QuizSubmissionListWrapper wrapper = GsonResponseParser.getDefaultGsonParser(serializeNulls).fromJson(response.getContent(), QuizSubmissionListWrapper.class);
        return wrapper.getQuiz_submissions();
    }

    /**
     * Because Canvas, instead of returning a simple list of quiz submission objects,
     * instead had the bright idea to return AN OBJECT containing a list of quiz submissions,
     * we need a wrapper class to represent this object for automatic parsing...
     * @author alexanda
     */
    private class QuizSubmissionListWrapper {

        private List<QuizSubmission> quiz_submissions;

        public List<QuizSubmission> getQuiz_submissions() {
            return quiz_submissions;
        }

        public void setQuiz_submissions(List<QuizSubmission> quiz_submissions) {
            this.quiz_submissions = quiz_submissions;
        }

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
