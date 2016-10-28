package edu.ksu.canvas.model.assignment;

/**
 * Class to represent Canvas quiz answer.
 * * See <a href="https://canvas.instructure.com/doc/api/quiz_questions.html#Answer">Canvas Quiz Answer</a> documentation.
 */

import java.util.List;

public class QuizAnswer {
    private Integer id;
    private String text;
    private Integer answer_weight;
    private String answer_comments;
    private String text_after_answers;
    private String answer_match_left;
    private String answer_match_right;
    private List<String> matching_answer_incorrect_matches;
    private String numerical_answer_type;
    private String exact; //used if numerical_answer_type is "exact_answer"
    private Double margin; //margin of error for above
    private String start; //used if numerical_answer_type is "range_answer"
    private String end; //used if numerical_answer_type is "range_answer"
    private Integer blank_id;


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

    public Integer getAnswer_weight() {
        return answer_weight;
    }

    public void setAnswer_weight(Integer answer_weight) {
        this.answer_weight = answer_weight;
    }

    public String getAnswer_comments() {
        return answer_comments;
    }

    public void setAnswer_comments(String answer_comments) {
        this.answer_comments = answer_comments;
    }

    public String getText_after_answers() {
        return text_after_answers;
    }

    public void setText_after_answers(String text_after_answers) {
        this.text_after_answers = text_after_answers;
    }

    public String getAnswer_match_left() {
        return answer_match_left;
    }

    public void setAnswer_match_left(String answer_match_left) {
        this.answer_match_left = answer_match_left;
    }

    public String getAnswer_match_right() {
        return answer_match_right;
    }

    public void setAnswer_match_right(String answer_match_right) {
        this.answer_match_right = answer_match_right;
    }

    public List<String> getMatching_answer_incorrect_matches() {
        return matching_answer_incorrect_matches;
    }

    public void setMatching_answer_incorrect_matches(List<String> matching_answer_incorrect_matches) {
        this.matching_answer_incorrect_matches = matching_answer_incorrect_matches;
    }

    public String getNumerical_answer_type() {
        return numerical_answer_type;
    }

    public void setNumerical_answer_type(String numerical_answer_type) {
        this.numerical_answer_type = numerical_answer_type;
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

    public Integer getBlank_id() {
        return blank_id;
    }

    public void setBlank_id(Integer blank_id) {
        this.blank_id = blank_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuizAnswer that = (QuizAnswer) o;

        if (answer_comments != null ? !answer_comments.equals(that.answer_comments) : that.answer_comments != null)
            return false;
        if (answer_match_left != null ? !answer_match_left.equals(that.answer_match_left) : that.answer_match_left != null)
            return false;
        if (answer_match_right != null ? !answer_match_right.equals(that.answer_match_right) : that.answer_match_right != null)
            return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (answer_weight != null ? !answer_weight.equals(that.answer_weight) : that.answer_weight != null)
            return false;
        if (blank_id != null ? !blank_id.equals(that.blank_id) : that.blank_id != null) return false;
        if (end != null ? !end.equals(that.end) : that.end != null) return false;
        if (exact != null ? !exact.equals(that.exact) : that.exact != null) return false;
        if (!id.equals(that.id)) return false;
        if (margin != null ? !margin.equals(that.margin) : that.margin != null) return false;
        if (matching_answer_incorrect_matches != null ? !matching_answer_incorrect_matches.equals(that.matching_answer_incorrect_matches) : that.matching_answer_incorrect_matches != null)
            return false;
        if (numerical_answer_type != null ? !numerical_answer_type.equals(that.numerical_answer_type) : that.numerical_answer_type != null)
            return false;
        if (start != null ? !start.equals(that.start) : that.start != null) return false;
        if (text_after_answers != null ? !text_after_answers.equals(that.text_after_answers) : that.text_after_answers != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (answer_weight != null ? answer_weight.hashCode() : 0);
        result = 31 * result + (answer_comments != null ? answer_comments.hashCode() : 0);
        result = 31 * result + (text_after_answers != null ? text_after_answers.hashCode() : 0);
        result = 31 * result + (answer_match_left != null ? answer_match_left.hashCode() : 0);
        result = 31 * result + (answer_match_right != null ? answer_match_right.hashCode() : 0);
        result = 31 * result + (matching_answer_incorrect_matches != null ? matching_answer_incorrect_matches.hashCode() : 0);
        result = 31 * result + (numerical_answer_type != null ? numerical_answer_type.hashCode() : 0);
        result = 31 * result + (exact != null ? exact.hashCode() : 0);
        result = 31 * result + (margin != null ? margin.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + (blank_id != null ? blank_id.hashCode() : 0);
        return result;
    }
}