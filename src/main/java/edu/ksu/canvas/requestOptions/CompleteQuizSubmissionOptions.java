package edu.ksu.canvas.requestOptions;

public class CompleteQuizSubmissionOptions extends BaseOptions {

    private String courseId;
    private Integer quizId;
    private Integer submissionId;

    /**
     * Constructs the API options for completing a quiz submission with the required arguments.
     * @param courseId Course ID that the quiz is in
     * @param quizId Quiz ID that this submission is associated with
     * @param submissionId The ID of the submission object that is being completed.
     * @param attempt The attempt number of the quiz submission being completed. Must be the latest attempt index.
     * @param validationToken The unique validation token received from Canvas when starting the quiz submission.
     */
    public CompleteQuizSubmissionOptions(String courseId, Integer quizId, Integer submissionId, Integer attempt, String validationToken) {
        this.courseId = courseId;
        this.quizId = quizId;
        this.submissionId = submissionId;
        addSingleItem("attempt", attempt.toString());
        addSingleItem("validation_token", validationToken);
    }

    public String getCourseId() {
        return courseId;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public Integer getSubmissionId() {
        return submissionId;
    }

    /**
     * Add the quiz access code (if required)
     * @param accessCode Access code set by the instructor
     * @return This object to allow adding more options
     */
    public CompleteQuizSubmissionOptions accessCode(String accessCode) {
        addSingleItem("access_code", accessCode);
        return this;
    }
}
