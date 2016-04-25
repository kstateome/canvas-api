package edu.ksu.canvas.model.quizzes;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;
import edu.ksu.canvas.model.BaseCanvasModel;

import java.util.List;
import java.util.Map;

/**
 * Class to represent Canvas quizzes.
 * See <a href="https://canvas.instructure.com/doc/api/quizzes.html#Quiz">Canvas Quiz</a> documentation.
 */
@CanvasObject(postKey = "quiz")
public class Quiz extends BaseCanvasModel {

    private Integer id;
    private String title;
    private String html_url;
    private String mobile_url;
    private String preview_url;
    private String description;
    private String quiz_type;
    private Integer assignment_group_id;
    private Double time_limit; //in minutes
    private Boolean shuffle_answers;
    private String hide_results;
    private Boolean show_correct_answers;
    private Boolean show_correct_answers_last_attempt;
    private String show_correct_answers_at;
    private String hide_correct_answers_at;
    private Boolean one_time_results;
    private String scoring_policy;
    private Integer allowed_attempts;
    private Boolean one_question_at_a_time;
    private Integer question_count;
    private Double points_possible;
    private Boolean cant_go_back;
    private String access_code;
    private String ip_filter;
    private String due_at;
    private String lock_at;
    private String unlock_at;
    private Boolean published;
    private Boolean unpublishable; //Whether it can be unpublished, not whether it cannot be published
    private Boolean locked_for_user;
    private String lock_info;
    private String lock_explanation;
    private String speedgrader_url;
    private String quiz_extensions_url;
    private QuizPermission permissions;
    private List<Map<String, String>> all_dates;
    private Integer version_number;
    private List<String> question_types;
    private Integer assignment_id;

    public Integer getAssignment_id() {
        return assignment_id;
    }

    public void setAssignment_id(Integer assignment_id) {
        this.assignment_id = assignment_id;
    }

    public List<String> getQuestion_types() {
        return question_types;
    }

    public void setQuestion_types(List<String> question_types) {
        this.question_types = question_types;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @CanvasField(postKey = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getPreview_url() {
        return preview_url;
    }

    public void setPreview_url(String preview_url) {
        this.preview_url = preview_url;
    }

    @CanvasField(postKey = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @CanvasField(postKey = "quiz_type")
    public String getQuiz_type() {
        return quiz_type;
    }

    public void setQuiz_type(String quiz_type) {
        this.quiz_type = quiz_type;
    }

    @CanvasField(postKey = "assignment_group_id")
    public Integer getAssignment_group_id() {
        return assignment_group_id;
    }

    public void setAssignment_group_id(Integer assignment_group_id) {
        this.assignment_group_id = assignment_group_id;
    }

    @CanvasField(postKey = "time_limit")
    public Double getTime_limit() {
        return time_limit;
    }

    public void setTime_limit(Double time_limit) {
        this.time_limit = time_limit;
    }

    @CanvasField(postKey = "shuffle_answers")
    public Boolean getShuffle_answers() {
        return shuffle_answers;
    }

    public void setShuffle_answers(Boolean shuffle_answers) {
        this.shuffle_answers = shuffle_answers;
    }

    @CanvasField(postKey = "hite_results")
    public String getHide_results() {
        return hide_results;
    }

    public void setHide_results(String hide_results) {
        this.hide_results = hide_results;
    }

    public Boolean getShow_correct_answers() {
        return show_correct_answers;
    }

    public void setShow_correct_answers(Boolean show_correct_answers) {
        this.show_correct_answers = show_correct_answers;
    }

    @CanvasField(postKey = "show_correct_answers_last_attempt")
    public Boolean getShow_correct_answers_last_attempt() {
        return show_correct_answers_last_attempt;
    }

    public void setShow_correct_answers_last_attempt(Boolean show_correct_answers_last_attempt) {
        this.show_correct_answers_last_attempt = show_correct_answers_last_attempt;
    }

    @CanvasField(postKey = "show_correct_answers_at")
    public String getShow_correct_answers_at() {
        return show_correct_answers_at;
    }

    public void setShow_correct_answers_at(String show_correct_answers_at) {
        this.show_correct_answers_at = show_correct_answers_at;
    }

    @CanvasField(postKey = "hide_correct_answers_at")
    public String getHide_correct_answers_at() {
        return hide_correct_answers_at;
    }

    public void setHide_correct_answers_at(String hide_correct_answers_at) {
        this.hide_correct_answers_at = hide_correct_answers_at;
    }

    @CanvasField(postKey = "one_time_results")
    public Boolean getOne_time_results() {
        return one_time_results;
    }

    public void setOne_time_results(Boolean one_time_results) {
        this.one_time_results = one_time_results;
    }

    public String getScoring_policy() {
        return scoring_policy;
    }

    public void setScoring_policy(String scoring_policy) {
        this.scoring_policy = scoring_policy;
    }

    @CanvasField(postKey = "one_question_at_a_time")
    public Boolean getOne_question_at_a_time() {
        return one_question_at_a_time;
    }

    public void setOne_question_at_a_time(Boolean one_question_at_a_time) {
        this.one_question_at_a_time = one_question_at_a_time;
    }

    public Integer getQuestion_count() {
        return question_count;
    }

    public void setQuestion_count(Integer question_count) {
        this.question_count = question_count;
    }

    public Double getPoints_possible() {
        return points_possible;
    }

    public void setPoints_possible(Double points_possible) {
        this.points_possible = points_possible;
    }

    @CanvasField(postKey = "cant_go_back")
    public Boolean getCant_go_back() {
        return cant_go_back;
    }

    public void setCant_go_back(Boolean cant_go_back) {
        this.cant_go_back = cant_go_back;
    }

    @CanvasField(postKey = "access_code")
    public String getAccess_code() {
        return access_code;
    }

    public void setAccess_code(String access_code) {
        this.access_code = access_code;
    }

    @CanvasField(postKey = "ip_filter")
    public String getIp_filter() {
        return ip_filter;
    }

    public void setIp_filter(String ip_filter) {
        this.ip_filter = ip_filter;
    }

    @CanvasField(postKey = "due_at")
    public String getDue_at() {
        return due_at;
    }

    public void setDue_at(String due_at) {
        this.due_at = due_at;
    }

    @CanvasField(postKey = "lock_at")
    public String getLock_at() {
        return lock_at;
    }

    public void setLock_at(String lock_at) {
        this.lock_at = lock_at;
    }

    @CanvasField(postKey = "unlock_at")
    public String getUnlock_at() {
        return unlock_at;
    }

    public void setUnlock_at(String unlock_at) {
        this.unlock_at = unlock_at;
    }

    @CanvasField(postKey = "published")
    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Boolean getUnpublishable() {
        return unpublishable;
    }

    public void setUnpublishable(Boolean unpublishable) {
        this.unpublishable = unpublishable;
    }

    public Boolean getLocked_for_user() {
        return locked_for_user;
    }

    public void setLocked_for_user(Boolean locked_for_user) {
        this.locked_for_user = locked_for_user;
    }

    public String getLock_info() {
        return lock_info;
    }

    public void setLock_info(String lock_info) {
        this.lock_info = lock_info;
    }

    public String getLock_explanation() {
        return lock_explanation;
    }

    public void setLock_explanation(String lock_explanation) {
        this.lock_explanation = lock_explanation;
    }

    public String getSpeedgrader_url() {
        return speedgrader_url;
    }

    public void setSpeedgrader_url(String speedgrader_url) {
        this.speedgrader_url = speedgrader_url;
    }

    public String getQuiz_extensions_url() {
        return quiz_extensions_url;
    }

    public void setQuiz_extensions_url(String quiz_extensions_url) {
        this.quiz_extensions_url = quiz_extensions_url;
    }

    public List<Map<String, String>> getAll_dates() {
        return all_dates;
    }

    public void setAll_dates(List<Map<String, String>> all_dates) {
        this.all_dates = all_dates;
    }

    public Integer getVersion_number() {
        return version_number;
    }

    public void setVersion_number(Integer version_number) {
        this.version_number = version_number;
    }

    public QuizPermission getPermissions(){
        return permissions;
    }

    public Boolean canUserRead(){
        return permissions.canRead();
    }

    public Boolean canUserSubmit(){
        return permissions.canSubmit();
    }

    public Boolean canUserCreate(){
        return permissions.canCreate();
    }

    public Boolean canUserManage(){
        return permissions.canManage();
    }

    public Boolean canUserReadStatistics(){
        return permissions.canRead_statistics();
    }

    public Boolean canUserReviewGrades(){
        return permissions.canReview_grades();
    }

    public Boolean canUserUpdate(){
        return permissions.canUpdate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quiz that = (Quiz) o;

        if (access_code != null ? !access_code.equals(that.access_code) : that.access_code != null) return false;
        if (all_dates != null ? !all_dates.equals(that.all_dates) : that.all_dates != null) return false;
        if (assignment_group_id != null ? !assignment_group_id.equals(that.assignment_group_id) : that.assignment_group_id != null)
            return false;
        if (cant_go_back != null ? !cant_go_back.equals(that.cant_go_back) : that.cant_go_back != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (due_at != null ? !due_at.equals(that.due_at) : that.due_at != null) return false;
        if (hide_correct_answers_at != null ? !hide_correct_answers_at.equals(that.hide_correct_answers_at) : that.hide_correct_answers_at != null)
            return false;
        if (hide_results != null ? !hide_results.equals(that.hide_results) : that.hide_results != null) return false;
        if (html_url != null ? !html_url.equals(that.html_url) : that.html_url != null) return false;
        if (!id.equals(that.id)) return false;
        if (ip_filter != null ? !ip_filter.equals(that.ip_filter) : that.ip_filter != null) return false;
        if (lock_at != null ? !lock_at.equals(that.lock_at) : that.lock_at != null) return false;
        if (lock_explanation != null ? !lock_explanation.equals(that.lock_explanation) : that.lock_explanation != null)
            return false;
        if (lock_info != null ? !lock_info.equals(that.lock_info) : that.lock_info != null) return false;
        if (locked_for_user != null ? !locked_for_user.equals(that.locked_for_user) : that.locked_for_user != null)
            return false;
        if (mobile_url != null ? !mobile_url.equals(that.mobile_url) : that.mobile_url != null) return false;
        if (one_question_at_a_time != null ? !one_question_at_a_time.equals(that.one_question_at_a_time) : that.one_question_at_a_time != null)
            return false;
        if (one_time_results != null ? !one_time_results.equals(that.one_time_results) : that.one_time_results != null)
            return false;
        if (permissions != null ? !permissions.equals(that.permissions) : that.permissions != null) return false;
        if (points_possible != null ? !points_possible.equals(that.points_possible) : that.points_possible != null)
            return false;
        if (preview_url != null ? !preview_url.equals(that.preview_url) : that.preview_url != null) return false;
        if (published != null ? !published.equals(that.published) : that.published != null) return false;
        if (question_count != null ? !question_count.equals(that.question_count) : that.question_count != null)
            return false;
        if (question_types != null ? !question_types.equals(that.question_types) : that.question_types != null)
            return false;
        if (quiz_extensions_url != null ? !quiz_extensions_url.equals(that.quiz_extensions_url) : that.quiz_extensions_url != null)
            return false;
        if (quiz_type != null ? !quiz_type.equals(that.quiz_type) : that.quiz_type != null) return false;
        if (scoring_policy != null ? !scoring_policy.equals(that.scoring_policy) : that.scoring_policy != null)
            return false;
        if (show_correct_answers != null ? !show_correct_answers.equals(that.show_correct_answers) : that.show_correct_answers != null)
            return false;
        if (show_correct_answers_at != null ? !show_correct_answers_at.equals(that.show_correct_answers_at) : that.show_correct_answers_at != null)
            return false;
        if (show_correct_answers_last_attempt != null ? !show_correct_answers_last_attempt.equals(that.show_correct_answers_last_attempt) : that.show_correct_answers_last_attempt != null)
            return false;
        if (shuffle_answers != null ? !shuffle_answers.equals(that.shuffle_answers) : that.shuffle_answers != null)
            return false;
        if (speedgrader_url != null ? !speedgrader_url.equals(that.speedgrader_url) : that.speedgrader_url != null)
            return false;
        if (time_limit != null ? !time_limit.equals(that.time_limit) : that.time_limit != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (unlock_at != null ? !unlock_at.equals(that.unlock_at) : that.unlock_at != null) return false;
        if (unpublishable != null ? !unpublishable.equals(that.unpublishable) : that.unpublishable != null)
            return false;
        if (version_number != null ? !version_number.equals(that.version_number) : that.version_number != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (html_url != null ? html_url.hashCode() : 0);
        result = 31 * result + (mobile_url != null ? mobile_url.hashCode() : 0);
        result = 31 * result + (preview_url != null ? preview_url.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (quiz_type != null ? quiz_type.hashCode() : 0);
        result = 31 * result + (assignment_group_id != null ? assignment_group_id.hashCode() : 0);
        result = 31 * result + (time_limit != null ? time_limit.hashCode() : 0);
        result = 31 * result + (shuffle_answers != null ? shuffle_answers.hashCode() : 0);
        result = 31 * result + (hide_results != null ? hide_results.hashCode() : 0);
        result = 31 * result + (show_correct_answers != null ? show_correct_answers.hashCode() : 0);
        result = 31 * result + (show_correct_answers_last_attempt != null ? show_correct_answers_last_attempt.hashCode() : 0);
        result = 31 * result + (show_correct_answers_at != null ? show_correct_answers_at.hashCode() : 0);
        result = 31 * result + (hide_correct_answers_at != null ? hide_correct_answers_at.hashCode() : 0);
        result = 31 * result + (one_time_results != null ? one_time_results.hashCode() : 0);
        result = 31 * result + (scoring_policy != null ? scoring_policy.hashCode() : 0);
        result = 31 * result + (one_question_at_a_time != null ? one_question_at_a_time.hashCode() : 0);
        result = 31 * result + (question_count != null ? question_count.hashCode() : 0);
        result = 31 * result + (points_possible != null ? points_possible.hashCode() : 0);
        result = 31 * result + (cant_go_back != null ? cant_go_back.hashCode() : 0);
        result = 31 * result + (access_code != null ? access_code.hashCode() : 0);
        result = 31 * result + (ip_filter != null ? ip_filter.hashCode() : 0);
        result = 31 * result + (due_at != null ? due_at.hashCode() : 0);
        result = 31 * result + (lock_at != null ? lock_at.hashCode() : 0);
        result = 31 * result + (unlock_at != null ? unlock_at.hashCode() : 0);
        result = 31 * result + (published != null ? published.hashCode() : 0);
        result = 31 * result + (unpublishable != null ? unpublishable.hashCode() : 0);
        result = 31 * result + (locked_for_user != null ? locked_for_user.hashCode() : 0);
        result = 31 * result + (lock_info != null ? lock_info.hashCode() : 0);
        result = 31 * result + (lock_explanation != null ? lock_explanation.hashCode() : 0);
        result = 31 * result + (speedgrader_url != null ? speedgrader_url.hashCode() : 0);
        result = 31 * result + (quiz_extensions_url != null ? quiz_extensions_url.hashCode() : 0);
        result = 31 * result + (permissions != null ? permissions.hashCode() : 0);
        result = 31 * result + (all_dates != null ? all_dates.hashCode() : 0);
        result = 31 * result + (version_number != null ? version_number.hashCode() : 0);
        result = 31 * result + (question_types != null ? question_types.hashCode() : 0);
        return result;
    }

    public Integer getAllowed_attempts() {
        return allowed_attempts;
    }

    public void setAllowed_attempts(Integer allowed_attempts) {
        this.allowed_attempts = allowed_attempts;
    }
}