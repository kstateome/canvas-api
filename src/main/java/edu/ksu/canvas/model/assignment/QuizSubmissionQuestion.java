package edu.ksu.canvas.model.assignment;

import java.io.Serializable;

/**
 * Class to represent Canvas quiz submission question.
 * See <a href="https://canvas.instructure.com/doc/api/quiz_submission_questions.html#QuizSubmissionQuestion">Canvas Quiz Submission Question</a> documentation.
 */

import java.util.List;

public class QuizSubmissionQuestion implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Boolean flagged;
    private List<Integer> answer; //Set to JSON object, because it can either be an array or a String.
    private List<QuizAnswer> answers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getFlagged() {
        return flagged;
    }

    public void setFlagged(Boolean flagged) {
        this.flagged = flagged;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public List<QuizAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<QuizAnswer> answers) {
        this.answers = answers;
    }
}
