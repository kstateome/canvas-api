package edu.ksu.canvas.requestOptions;

public class StartQuizSubmissionOptions extends BaseOptions {

    private String courseId;
    private Integer quizId;

    /**
     * Create API options wrapper for the "create quiz submission" API call with required parameters
     * @param courseId Course ID of the course the quiz is in
     * @param quizId Quiz ID of the quiz that is being taken
     */
    public StartQuizSubmissionOptions(String courseId, Integer quizId) {
        this.courseId = courseId;
        this.quizId = quizId;
    }

    public String getCourseId() {
        return courseId;
    }

    public Integer getQuizId() {
        return quizId;
    }

    /**
     * Add an access code (if required)
     * @param accessCode The access code set by the instructor
     * @return This object to allow adding more options
     */
    public StartQuizSubmissionOptions accessCode(String accessCode) {
        addSingleItem("access_code", accessCode);
        return this;
    }

    /**
     * Only valid for teachers. Set whether this should be a preview submission and not count towards the user's course record.
     * @param preview Whether this should be a preview
     * @return This object to allow adding more options
     */
    public StartQuizSubmissionOptions preview(Boolean preview) {
        addSingleItem("preview", preview.toString());
        return this;
    }
}
