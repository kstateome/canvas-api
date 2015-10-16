package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.QuizSubmissionReader;
import edu.ksu.canvas.interfaces.QuizSubmissionWriter;
import edu.ksu.canvas.model.quizzes.QuizSubmission;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.util.CanvasURLBuilder;
import edu.ksu.lti.error.OauthTokenRequiredException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class QuizSubmissionImpl extends  BaseImpl implements QuizSubmissionReader, QuizSubmissionWriter {
    private static final Logger LOG = Logger.getLogger(QuizSubmissionImpl.class);

     public QuizSubmissionImpl(String canvasBaseUrl, Integer apiVersion, String oauthToken, RestClient restClient) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient);
    }

    @Override
    public List<QuizSubmission> getQuizSubmissions(String oauthToken, String courseId, String quizId) throws OauthTokenRequiredException, IOException {
        String url = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion,
                "courses/" + courseId + "/quizzes/" + quizId + "/submissions", Collections.emptyMap());
        List<Response> responses = canvasMessenger.getFromCanvas(oauthToken, url);
        return parseQuizSubmissionList(responses);
    }

    private List <QuizSubmission> parseQuizSubmissionList(final List<Response> responses) {
        return responses.stream().
                map(this::parseQuizSubmissionList).
                flatMap(Collection::stream).
                collect(Collectors.toList());
    }

    private List<QuizSubmission> parseQuizSubmissionList(final Response response) {
        QuizSubmissionListWrapper wrapper = getDefaultGsonParser().fromJson(response.getContent(), QuizSubmissionListWrapper.class);
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
}
