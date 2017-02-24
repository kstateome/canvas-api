package edu.ksu.canvas.requestOptions;

public class AnswerQuizQuestionOptions extends BaseOptions {

    private Integer quizSubmissionId;

    /**
     * Create required API options for answering quiz questions
     * @param quizSubmissionId Quiz submission ID to submit answers to
     * @param attemptNumber Attempt number. Must be the latest attempt index since earlier ones can not be modified
     * @param validationToken Validation token returned by Canvas when the quiz submission was created
     */
    public AnswerQuizQuestionOptions(Integer quizSubmissionId, Integer attemptNumber, String validationToken) {
        this.quizSubmissionId = quizSubmissionId;
        addSingleItem("attempt", attemptNumber.toString());
        addSingleItem("validation_token", validationToken);
    }

    public Integer getQuizSubmissionid() {
        return quizSubmissionId;
    }

    /**
     * If required, set an access code on this request
     * @param accessCode Access code set by the teacher on the quiz
     * @return This object to allow adding more options
     */
    public AnswerQuizQuestionOptions accessCode(String accessCode) {
        addSingleItem("access_code", accessCode);
        return this;
    }

}
