package edu.ksu.canvas.model.assignment;

import java.io.Serializable;

/**
 * Class to represent Canvas quiz answer.
 * * See <a href="https://canvas.instructure.com/doc/api/quiz_questions.html#Answer">Canvas Quiz Answer</a> documentation.
 */

import java.util.List;

public class QuizAnswer implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String text;
    private String html;
    private Double weight;
    private String comments;
    private String commentsHtml;
    private String textAfterAnswers;
    private String answerMatchLeft;
    private String answerMatchRight;
    private List<String> matchingAnswerIncorrectMatches;
    private String numericalAnswerType;
    private String exact; //used if numerical_answer_type is "exact_answer"
    private Double margin; //margin of error for above
    private String start; //used if numerical_answer_type is "range_answer"
    private String end; //used if numerical_answer_type is "range_answer"
    private String blankId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCommentsHtml() {
        return commentsHtml;
    }

    public void setCommentsHtml(String commentsHtml) {
        this.commentsHtml = commentsHtml;
    }

    public String getTextAfterAnswers() {
        return textAfterAnswers;
    }

    public void setTextAfterAnswers(String textAfterAnswers) {
        this.textAfterAnswers = textAfterAnswers;
    }

    public String getAnswerMatchLeft() {
        return answerMatchLeft;
    }

    public void setAnswerMatchLeft(String answerMatchLeft) {
        this.answerMatchLeft = answerMatchLeft;
    }

    public String getAnswerMatchRight() {
        return answerMatchRight;
    }

    public void setAnswerMatchRight(String answerMatchRight) {
        this.answerMatchRight = answerMatchRight;
    }

    public List<String> getMatchingAnswerIncorrectMatches() {
        return matchingAnswerIncorrectMatches;
    }

    public void setMatchingAnswerIncorrectMatches(List<String> matchingAnswerIncorrectMatches) {
        this.matchingAnswerIncorrectMatches = matchingAnswerIncorrectMatches;
    }

    public String getNumericalAnswerType() {
        return numericalAnswerType;
    }

    public void setNumericalAnswerType(String numericalAnswerType) {
        this.numericalAnswerType = numericalAnswerType;
    }

    public String getExact() {
        return exact;
    }

    public void setExact(String exact) {
        this.exact = exact;
    }

    public Double getMargin() {
        return margin;
    }

    public void setMargin(Double margin) {
        this.margin = margin;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getBlankId() {
        return blankId;
    }

    public void setBlankId(String blankId) {
        this.blankId = blankId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuizAnswer that = (QuizAnswer) o;

        if (comments != null ? !comments.equals(that.comments) : that.comments != null)
            return false;
        if (answerMatchLeft != null ? !answerMatchLeft.equals(that.answerMatchLeft) : that.answerMatchLeft != null)
            return false;
        if (answerMatchRight != null ? !answerMatchRight.equals(that.answerMatchRight) : that.answerMatchRight != null)
            return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null)
            return false;
        if (blankId != null ? !blankId.equals(that.blankId) : that.blankId != null) return false;
        if (end != null ? !end.equals(that.end) : that.end != null) return false;
        if (exact != null ? !exact.equals(that.exact) : that.exact != null) return false;
        if (!id.equals(that.id)) return false;
        if (margin != null ? !margin.equals(that.margin) : that.margin != null) return false;
        if (matchingAnswerIncorrectMatches != null ? !matchingAnswerIncorrectMatches.equals(that.matchingAnswerIncorrectMatches) : that.matchingAnswerIncorrectMatches != null)
            return false;
        if (numericalAnswerType != null ? !numericalAnswerType.equals(that.numericalAnswerType) : that.numericalAnswerType != null)
            return false;
        if (start != null ? !start.equals(that.start) : that.start != null) return false;
        if (textAfterAnswers != null ? !textAfterAnswers.equals(that.textAfterAnswers) : that.textAfterAnswers != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (textAfterAnswers != null ? textAfterAnswers.hashCode() : 0);
        result = 31 * result + (answerMatchLeft != null ? answerMatchLeft.hashCode() : 0);
        result = 31 * result + (answerMatchRight != null ? answerMatchRight.hashCode() : 0);
        result = 31 * result + (matchingAnswerIncorrectMatches != null ? matchingAnswerIncorrectMatches.hashCode() : 0);
        result = 31 * result + (numericalAnswerType != null ? numericalAnswerType.hashCode() : 0);
        result = 31 * result + (exact != null ? exact.hashCode() : 0);
        result = 31 * result + (margin != null ? margin.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + (blankId != null ? blankId.hashCode() : 0);
        return result;
    }
}