package edu.ksu.canvas.model.assignment;

/**
 * Class to represent Canvas quiz question.
 * * See <a href="https://canvas.instructure.com/doc/api/quiz_questions.html#QuizQuestion">Canvas Quiz Question</a> documentation.
 */

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;
import edu.ksu.canvas.model.BaseCanvasModel;

import java.io.Serializable;
import java.util.List;

@CanvasObject(postKey = "quizQuestion")
public class QuizQuestion extends BaseCanvasModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer quizId;
    private Integer position;
    private String questionName;
    private String questionType;
    private String questionText;
    private Double pointsPossible;
    private String correctComments;
    private String incorrectComments;
    private String neutralComments;
    private List<QuizAnswer> answers;

    @CanvasField(postKey = "id", array = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @CanvasField(postKey = "quiz_id", array = false)
    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    @CanvasField(postKey = "position")
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @CanvasField(postKey = "question_name")
    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    @CanvasField(postKey = "question_type")
    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    @CanvasField(postKey = "question_text")
    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    @CanvasField(postKey = "points_possible")
    public Double getPointsPossible() {
        return pointsPossible;
    }

    public void setPointsPossible(Double pointsPossible) {
        this.pointsPossible = pointsPossible;
    }

    @CanvasField(postKey = "correct_comments")
    public String getCorrectComments() {
        return correctComments;
    }

    public void setCorrectComments(String correctComments) {
        this.correctComments = correctComments;
    }

    @CanvasField(postKey = "incorrect_comments")
    public String getIncorrectComments() {
        return incorrectComments;
    }

    public void setIncorrectComments(String incorrectComments) {
        this.incorrectComments = incorrectComments;
    }

    @CanvasField(postKey = "neutral_comments")
    public String getNeutralComments() {
        return neutralComments;
    }

    public void setNeutralComments(String neutralComments) {
        this.neutralComments = neutralComments;
    }

    @CanvasField(postKey = "answers")
    public List<QuizAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<QuizAnswer> answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof QuizQuestion)) return false;

        QuizQuestion that = (QuizQuestion) o;

        if (answers != null ? !answers.equals(that.answers) : that.answers != null) return false;
        if (correctComments != null ? !correctComments.equals(that.correctComments) : that.correctComments != null)
            return false;
        if (!id.equals(that.id)) return false;
        if (incorrectComments != null ? !incorrectComments.equals(that.incorrectComments) : that.incorrectComments != null)
            return false;
        if (neutralComments != null ? !neutralComments.equals(that.neutralComments) : that.neutralComments != null)
            return false;
        if (pointsPossible != null ? !pointsPossible.equals(that.pointsPossible) : that.pointsPossible != null)
            return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (questionName != null ? !questionName.equals(that.questionName) : that.questionName != null)
            return false;
        if (questionText != null ? !questionText.equals(that.questionText) : that.questionText != null)
            return false;
        if (!quizId.equals(that.quizId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + quizId.hashCode();
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (questionName != null ? questionName.hashCode() : 0);
        result = 31 * result + (questionText != null ? questionText.hashCode() : 0);
        result = 31 * result + (pointsPossible != null ? pointsPossible.hashCode() : 0);
        result = 31 * result + (correctComments != null ? correctComments.hashCode() : 0);
        result = 31 * result + (incorrectComments != null ? incorrectComments.hashCode() : 0);
        result = 31 * result + (neutralComments != null ? neutralComments.hashCode() : 0);
        result = 31 * result + (answers != null ? answers.hashCode() : 0);
        return result;
    }
}