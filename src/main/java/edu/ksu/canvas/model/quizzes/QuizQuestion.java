package edu.ksu.canvas.model.quizzes;

import java.util.List;

public class QuizQuestion {
    private Integer id;
    private Integer quiz_id;
    private Integer position;
    private String question_name;
    private String question_type;
    private String question_text;
    private Double points_possible;
    private String correct_comments;
    private String incorrect_comments;
    private String neutral_comments;
    private List<QuizAnswer> answers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(Integer quiz_id) {
        this.quiz_id = quiz_id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getQuestion_name() {
        return question_name;
    }

    public void setQuestion_name(String question_name) {
        this.question_name = question_name;
    }

    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public Double getPoints_possible() {
        return points_possible;
    }

    public void setPoints_possible(Double points_possible) {
        this.points_possible = points_possible;
    }

    public String getCorrect_comments() {
        return correct_comments;
    }

    public void setCorrect_comments(String correct_comments) {
        this.correct_comments = correct_comments;
    }

    public String getIncorrect_comments() {
        return incorrect_comments;
    }

    public void setIncorrect_comments(String incorrect_comments) {
        this.incorrect_comments = incorrect_comments;
    }

    public String getNeutral_comments() {
        return neutral_comments;
    }

    public void setNeutral_comments(String neutral_comments) {
        this.neutral_comments = neutral_comments;
    }

    public List<QuizAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<QuizAnswer> answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuizQuestion that = (QuizQuestion) o;

        if (answers != null ? !answers.equals(that.answers) : that.answers != null) return false;
        if (correct_comments != null ? !correct_comments.equals(that.correct_comments) : that.correct_comments != null)
            return false;
        if (!id.equals(that.id)) return false;
        if (incorrect_comments != null ? !incorrect_comments.equals(that.incorrect_comments) : that.incorrect_comments != null)
            return false;
        if (neutral_comments != null ? !neutral_comments.equals(that.neutral_comments) : that.neutral_comments != null)
            return false;
        if (points_possible != null ? !points_possible.equals(that.points_possible) : that.points_possible != null)
            return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (question_name != null ? !question_name.equals(that.question_name) : that.question_name != null)
            return false;
        if (question_text != null ? !question_text.equals(that.question_text) : that.question_text != null)
            return false;
        if (!quiz_id.equals(that.quiz_id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + quiz_id.hashCode();
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (question_name != null ? question_name.hashCode() : 0);
        result = 31 * result + (question_text != null ? question_text.hashCode() : 0);
        result = 31 * result + (points_possible != null ? points_possible.hashCode() : 0);
        result = 31 * result + (correct_comments != null ? correct_comments.hashCode() : 0);
        result = 31 * result + (incorrect_comments != null ? incorrect_comments.hashCode() : 0);
        result = 31 * result + (neutral_comments != null ? neutral_comments.hashCode() : 0);
        result = 31 * result + (answers != null ? answers.hashCode() : 0);
        return result;
    }
}