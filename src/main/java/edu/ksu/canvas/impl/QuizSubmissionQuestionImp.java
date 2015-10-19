package edu.ksu.canvas.impl;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.exception.OauthTokenRequiredException;
import edu.ksu.canvas.interfaces.QuizSubmissionQuestionReader;
import edu.ksu.canvas.interfaces.QuizSubmissionQuestionWriter;
import edu.ksu.canvas.model.quizzes.QuizAnswer;
import edu.ksu.canvas.model.quizzes.QuizSubmission;
import edu.ksu.canvas.model.quizzes.QuizSubmissionQuestion;
import edu.ksu.canvas.model.quizzes.QuizSubmissionQuestionWrapper;
import edu.ksu.canvas.model.quizzes.QuizSubmissionWrapper;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.util.CanvasURLBuilder;
import edu.ksu.lti.error.MessageUndeliverableException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class QuizSubmissionQuestionImp extends  BaseImpl implements QuizSubmissionQuestionReader, QuizSubmissionQuestionWriter {
    private static final Logger LOG = Logger.getLogger(QuizSubmissionQuestionImp.class);

    public QuizSubmissionQuestionImp(String canvasBaseUrl, Integer apiVersion, String oauthToken, RestClient restClient) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient);
    }

    @Override
    public List<QuizSubmissionQuestion> answerQuestions(QuizSubmission submission, String wid, String answerArrayJson, String accessCode) throws MessageUndeliverableException, IOException, OauthTokenRequiredException {
        String url = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion,
                "quiz_submissions/" + submission.getId() + "/questions", Collections.emptyMap());
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("attemtp", String.valueOf(submission.getAttempt()));
        requestBody.addProperty("validation_token", submission.getValidation_token());
        if (accessCode != null) {
            requestBody.addProperty("access_code", accessCode);
        }

        //Setup the quiz question array that Canvas requires
        JsonParser parser = new JsonParser();
        JsonArray answerJson = (JsonArray) parser.parse(answerArrayJson); //handling escaped serialization
        requestBody.add("quiz_questions", answerJson);
        Response response = canvasMessenger.sendToJsonCanvas(oauthToken, url, requestBody);
        if(response.getErrorHappened() || response.getResponseCode() != 200) {
            LOG.error("Error answering questions. Returning null");
            LOG.debug(response.getContent());
            return null;
        }

        Type responseType = new TypeToken<QuizSubmissionWrapper>() {
        }.getType();
        Gson responseGson = new GsonBuilder().registerTypeAdapter(responseType, new QuizSubmissionQuestionTypeAdapter()).create();
        QuizSubmissionQuestionWrapper wrapper = responseGson.fromJson(response.getContent(), responseType);

        return wrapper.getQuiz_submission_questions();
    }

    /**
     * Type adapter for parsing out QuizSubmissionQuestions from JSON
     */
    private static class QuizSubmissionQuestionTypeAdapter implements JsonDeserializer<QuizSubmissionQuestionWrapper> {

        @Override
        public QuizSubmissionQuestionWrapper deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            QuizSubmissionQuestionWrapper wrapper = new QuizSubmissionQuestionWrapper();
            if (json.getAsJsonObject().get("quiz_submission_questions").isJsonArray()) {
                JsonArray questionArray = json.getAsJsonObject().getAsJsonArray("quiz_submission_questions");
                List<QuizSubmissionQuestion> questionList = new LinkedList<>();
                for (JsonElement question : questionArray) {
                    QuizSubmissionQuestion newQuestion = new QuizSubmissionQuestion();
                    JsonObject questionObject = question.getAsJsonObject();
                    newQuestion.setId(questionObject.has("id") ? questionObject.get("id").getAsInt() : null);
                    newQuestion.setFlagged(questionObject.has("flagged") ? questionObject.get("flagged").getAsBoolean() : null);

                    List<Integer> answerList = new LinkedList<>();
                    if (questionObject.has("answer")) {
                        if (questionObject.get("answer").isJsonArray()) {
                            for (JsonElement answer : questionObject.getAsJsonArray("answer")) {
                                answerList.add(answer.getAsInt());
                            }
                        } else answerList.add(questionObject.get("answer").getAsInt());
                    }
                    newQuestion.setAnswer(answerList);

                    Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
                    Type listType = new TypeToken<List<QuizAnswer>>() {
                    }.getType();
                    newQuestion.setAnswers(gson.fromJson(questionObject.get("answers").toString(), listType));

                    questionList.add(newQuestion);

                }
                wrapper.setQuiz_submission_questions(questionList);
            }
            return wrapper;
        }
    }
}
