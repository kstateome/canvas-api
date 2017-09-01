package edu.ksu.canvas.model.assignment;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;
import edu.ksu.canvas.model.BaseCanvasModel;
import edu.ksu.canvas.model.assignment.LockInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Class to represent Canvas quizzes.
 * See <a href="https://canvas.instructure.com/doc/api/quizzes.html#Quiz">Canvas Quiz</a> documentation.
 */
@CanvasObject(postKey = "quiz")
public class Quiz extends BaseCanvasModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String title;
    private String htmlUrl;
    private String mobileUrl;
    private String previewUrl;
    private String description;
    private String quizType;
    private Integer assignmentGroupId;
    private Double timeLimit; //in minutes
    private Boolean shuffleAnswers;
    private String hideResults;
    private Boolean showCorrectAnswers;
    private Boolean showCorrectAnswersLastAttempt;
    private String showCorrectAnswersAt;
    private String hideCorrectAnswersAt;
    private Boolean oneTimeResults;
    private String scoringPolicy;
    private Integer allowedAttempts;
    private Boolean oneQuestionAtATime;
    private Integer questionCount;
    private Double pointsPossible;
    private Boolean cantGoBack;
    private String accessCode;
    private String ipFilter;
    private Date dueAt;
    private Date lockAt;
    private Date unlockAt;
    private Boolean published;
    private Boolean unpublishable; //Whether it can be unpublished, not whether it cannot be published
    private Boolean lockedForUser;
    private LockInfo lockInfo;
    private String lockExplanation;
    private String speedgraderUrl;
    private String quizExtensionsUrl;
    private QuizPermission permissions;
    private List<AssignmentDate> allDates;
    private Integer versionNumber;
    private List<String> questionTypes;
    private Integer assignmentId;

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public List<String> getQuestionTypes() {
        return questionTypes;
    }

    public void setQuestionTypes(List<String> questionTypes) {
        this.questionTypes = questionTypes;
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

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getMobileUrl() {
        return mobileUrl;
    }

    public void setMobileUrl(String mobileUrl) {
        this.mobileUrl = mobileUrl;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    @CanvasField(postKey = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @CanvasField(postKey = "quiz_type")
    public String getQuizType() {
        return quizType;
    }

    public void setQuizType(String quizType) {
        this.quizType = quizType;
    }

    @CanvasField(postKey = "assignment_group_id")
    public Integer getAssignmentGroupId() {
        return assignmentGroupId;
    }

    public void setAssignmentGroupId(Integer assignmentGroupId) {
        this.assignmentGroupId = assignmentGroupId;
    }

    @CanvasField(postKey = "time_limit")
    public Double getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Double timeLimit) {
        this.timeLimit = timeLimit;
    }

    @CanvasField(postKey = "shuffle_answers")
    public Boolean getShuffleAnswers() {
        return shuffleAnswers;
    }

    public void setShuffleAnswers(Boolean shuffleAnswers) {
        this.shuffleAnswers = shuffleAnswers;
    }

    @CanvasField(postKey = "hite_results")
    public String getHideResults() {
        return hideResults;
    }

    public void setHideresults(String hideResults) {
        this.hideResults = hideResults;
    }

    public Boolean getShowCorrectAnswers() {
        return showCorrectAnswers;
    }

    public void setShowCorrectAnswers(Boolean showCorrectAnswers) {
        this.showCorrectAnswers = showCorrectAnswers;
    }

    @CanvasField(postKey = "show_correct_answers_last_attempt")
    public Boolean getShowCorrectAnswersLastAttempt() {
        return showCorrectAnswersLastAttempt;
    }

    public void setShowCorrectAnswersLastAttempt(Boolean showCorrectAnswersLastAttempt) {
        this.showCorrectAnswersLastAttempt = showCorrectAnswersLastAttempt;
    }

    @CanvasField(postKey = "show_correct_answers_at")
    public String getShowCorrectAnswersAt() {
        return showCorrectAnswersAt;
    }

    public void setShowCorrectAnswersAt(String showCorrectAnswersAt) {
        this.showCorrectAnswersAt = showCorrectAnswersAt;
    }

    @CanvasField(postKey = "hide_correct_answers_at")
    public String getHideCorrectAnswersAt() {
        return hideCorrectAnswersAt;
    }

    public void setHideCorrectAnswersAt(String hideCorrectAnswersAt) {
        this.hideCorrectAnswersAt = hideCorrectAnswersAt;
    }

    @CanvasField(postKey = "one_time_results")
    public Boolean getOneTimeResults() {
        return oneTimeResults;
    }

    public void setOneTimeResults(Boolean oneTimeResults) {
        this.oneTimeResults = oneTimeResults;
    }

    public String getScoringPolicy() {
        return scoringPolicy;
    }

    public void setScoringPolicy(String scoringPolicy) {
        this.scoringPolicy = scoringPolicy;
    }

    @CanvasField(postKey = "one_question_at_a_time")
    public Boolean getOneQuestionAtATime() {
        return oneQuestionAtATime;
    }

    public void setOneQuestionAtATime(Boolean oneQuestionAtATime) {
        this.oneQuestionAtATime = oneQuestionAtATime;
    }

    public Integer getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Integer questionCount) {
        this.questionCount = questionCount;
    }

    public Double getPointsPossible() {
        return pointsPossible;
    }

    public void setPointsPossible(Double pointsPossible) {
        this.pointsPossible = pointsPossible;
    }

    @CanvasField(postKey = "cant_go_back")
    public Boolean getCantGoBack() {
        return cantGoBack;
    }

    public void setCantGoBack(Boolean cantGoBack) {
        this.cantGoBack = cantGoBack;
    }

    @CanvasField(postKey = "access_code")
    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    @CanvasField(postKey = "ip_filter")
    public String getIpFilter() {
        return ipFilter;
    }

    public void setIpFilter(String ipFilter) {
        this.ipFilter = ipFilter;
    }

    @CanvasField(postKey = "due_at")
    public Date getDueAt() {
        return dueAt;
    }

    public void setDueAt(Date dueAt) {
        this.dueAt = dueAt;
    }

    @CanvasField(postKey = "lock_at")
    public Date getLockAt() {
        return lockAt;
    }

    public void setLockAt(Date lockAt) {
        this.lockAt = lockAt;
    }

    @CanvasField(postKey = "unlock_at")
    public Date getUnlockAt() {
        return unlockAt;
    }

    public void setUnlockAt(Date unlockAt) {
        this.unlockAt = unlockAt;
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

    public Boolean getLockedForUser() {
        return lockedForUser;
    }

    public void setLockedForUser(Boolean lockedForUser) {
        this.lockedForUser = lockedForUser;
    }

    public LockInfo getLockInfo() {
        return lockInfo;
    }

    public void setLockInfo(LockInfo lockInfo) {
        this.lockInfo = lockInfo;
    }

    public String getLockExplanation() {
        return lockExplanation;
    }

    public void setLockExplanation(String lockExplanation) {
        this.lockExplanation = lockExplanation;
    }

    public String getSpeedgraderUrl() {
        return speedgraderUrl;
    }

    public void setSpeedgraderUrl(String speedgraderUrl) {
        this.speedgraderUrl = speedgraderUrl;
    }

    public String getQuizExtensionsUrl() {
        return quizExtensionsUrl;
    }

    public void setQuizExtensionsUrl(String quizExtensionsUrl) {
        this.quizExtensionsUrl = quizExtensionsUrl;
    }

    public List<AssignmentDate> getAllDates() {
        return allDates;
    }

    public void setAllDates(List<AssignmentDate> allDates) {
        this.allDates = allDates;
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
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
        return permissions.canReadStatistics();
    }

    public Boolean canUserReviewGrades(){
        return permissions.canReviewGrades();
    }

    public Boolean canUserUpdate(){
        return permissions.canUpdate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quiz that = (Quiz) o;

        if (accessCode != null ? !accessCode.equals(that.accessCode) : that.accessCode != null) return false;
        if (allDates != null ? !allDates.equals(that.allDates) : that.allDates != null) return false;
        if (assignmentGroupId != null ? !assignmentGroupId.equals(that.assignmentGroupId) : that.assignmentGroupId != null)
            return false;
        if (cantGoBack != null ? !cantGoBack.equals(that.cantGoBack) : that.cantGoBack != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (dueAt != null ? !dueAt.equals(that.dueAt) : that.dueAt != null) return false;
        if (hideCorrectAnswersAt != null ? !hideCorrectAnswersAt.equals(that.hideCorrectAnswersAt) : that.hideCorrectAnswersAt != null)
            return false;
        if (hideResults != null ? !hideResults.equals(that.hideResults) : that.hideResults != null) return false;
        if (htmlUrl != null ? !htmlUrl.equals(that.htmlUrl) : that.htmlUrl != null) return false;
        if (!id.equals(that.id)) return false;
        if (ipFilter != null ? !ipFilter.equals(that.ipFilter) : that.ipFilter != null) return false;
        if (lockAt != null ? !lockAt.equals(that.lockAt) : that.lockAt != null) return false;
        if (lockExplanation != null ? !lockExplanation.equals(that.lockExplanation) : that.lockExplanation != null)
            return false;
        if (lockInfo != null ? !lockInfo.equals(that.lockInfo) : that.lockInfo != null) return false;
        if (lockedForUser != null ? !lockedForUser.equals(that.lockedForUser) : that.lockedForUser != null)
            return false;
        if (mobileUrl != null ? !mobileUrl.equals(that.mobileUrl) : that.mobileUrl != null) return false;
        if (oneQuestionAtATime != null ? !oneQuestionAtATime.equals(that.oneQuestionAtATime) : that.oneQuestionAtATime != null)
            return false;
        if (oneTimeResults != null ? !oneTimeResults.equals(that.oneTimeResults) : that.oneTimeResults != null)
            return false;
        if (permissions != null ? !permissions.equals(that.permissions) : that.permissions != null) return false;
        if (pointsPossible != null ? !pointsPossible.equals(that.pointsPossible) : that.pointsPossible != null)
            return false;
        if (previewUrl != null ? !previewUrl.equals(that.previewUrl) : that.previewUrl != null) return false;
        if (published != null ? !published.equals(that.published) : that.published != null) return false;
        if (questionCount != null ? !questionCount.equals(that.questionCount) : that.questionCount != null)
            return false;
        if (questionTypes != null ? !questionTypes.equals(that.questionTypes) : that.questionTypes != null)
            return false;
        if (quizExtensionsUrl != null ? !quizExtensionsUrl.equals(that.quizExtensionsUrl) : that.quizExtensionsUrl != null)
            return false;
        if (quizType != null ? !quizType.equals(that.quizType) : that.quizType != null) return false;
        if (scoringPolicy != null ? !scoringPolicy.equals(that.scoringPolicy) : that.scoringPolicy != null)
            return false;
        if (showCorrectAnswers != null ? !showCorrectAnswers.equals(that.showCorrectAnswers) : that.showCorrectAnswers != null)
            return false;
        if (showCorrectAnswersAt != null ? !showCorrectAnswersAt.equals(that.showCorrectAnswersAt) : that.showCorrectAnswersAt != null)
            return false;
        if (showCorrectAnswersLastAttempt != null ? !showCorrectAnswersLastAttempt.equals(that.showCorrectAnswersLastAttempt) : that.showCorrectAnswersLastAttempt != null)
            return false;
        if (shuffleAnswers != null ? !shuffleAnswers.equals(that.shuffleAnswers) : that.shuffleAnswers != null)
            return false;
        if (speedgraderUrl != null ? !speedgraderUrl.equals(that.speedgraderUrl) : that.speedgraderUrl != null)
            return false;
        if (timeLimit != null ? !timeLimit.equals(that.timeLimit) : that.timeLimit != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (unlockAt != null ? !unlockAt.equals(that.unlockAt) : that.unlockAt != null) return false;
        if (unpublishable != null ? !unpublishable.equals(that.unpublishable) : that.unpublishable != null)
            return false;
        if (versionNumber != null ? !versionNumber.equals(that.versionNumber) : that.versionNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (htmlUrl != null ? htmlUrl.hashCode() : 0);
        result = 31 * result + (mobileUrl != null ? mobileUrl.hashCode() : 0);
        result = 31 * result + (previewUrl != null ? previewUrl.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (quizType != null ? quizType.hashCode() : 0);
        result = 31 * result + (assignmentGroupId != null ? assignmentGroupId.hashCode() : 0);
        result = 31 * result + (timeLimit != null ? timeLimit.hashCode() : 0);
        result = 31 * result + (shuffleAnswers != null ? shuffleAnswers.hashCode() : 0);
        result = 31 * result + (hideResults != null ? hideResults.hashCode() : 0);
        result = 31 * result + (showCorrectAnswers != null ? showCorrectAnswers.hashCode() : 0);
        result = 31 * result + (showCorrectAnswersLastAttempt != null ? showCorrectAnswersLastAttempt.hashCode() : 0);
        result = 31 * result + (showCorrectAnswersAt != null ? showCorrectAnswersAt.hashCode() : 0);
        result = 31 * result + (hideCorrectAnswersAt != null ? hideCorrectAnswersAt.hashCode() : 0);
        result = 31 * result + (oneTimeResults != null ? oneTimeResults.hashCode() : 0);
        result = 31 * result + (scoringPolicy != null ? scoringPolicy.hashCode() : 0);
        result = 31 * result + (oneQuestionAtATime != null ? oneQuestionAtATime.hashCode() : 0);
        result = 31 * result + (questionCount != null ? questionCount.hashCode() : 0);
        result = 31 * result + (pointsPossible != null ? pointsPossible.hashCode() : 0);
        result = 31 * result + (cantGoBack != null ? cantGoBack.hashCode() : 0);
        result = 31 * result + (accessCode != null ? accessCode.hashCode() : 0);
        result = 31 * result + (ipFilter != null ? ipFilter.hashCode() : 0);
        result = 31 * result + (dueAt != null ? dueAt.hashCode() : 0);
        result = 31 * result + (lockAt != null ? lockAt.hashCode() : 0);
        result = 31 * result + (unlockAt != null ? unlockAt.hashCode() : 0);
        result = 31 * result + (published != null ? published.hashCode() : 0);
        result = 31 * result + (unpublishable != null ? unpublishable.hashCode() : 0);
        result = 31 * result + (lockedForUser != null ? lockedForUser.hashCode() : 0);
        result = 31 * result + (lockInfo != null ? lockInfo.hashCode() : 0);
        result = 31 * result + (lockExplanation != null ? lockExplanation.hashCode() : 0);
        result = 31 * result + (speedgraderUrl != null ? speedgraderUrl.hashCode() : 0);
        result = 31 * result + (quizExtensionsUrl != null ? quizExtensionsUrl.hashCode() : 0);
        result = 31 * result + (permissions != null ? permissions.hashCode() : 0);
        result = 31 * result + (allDates != null ? allDates.hashCode() : 0);
        result = 31 * result + (versionNumber != null ? versionNumber.hashCode() : 0);
        result = 31 * result + (questionTypes != null ? questionTypes.hashCode() : 0);
        return result;
    }

    public Integer getAllowedAttempts() {
        return allowedAttempts;
    }

    public void setAllowedAttempts(Integer allowedAttempts) {
        this.allowedAttempts = allowedAttempts;
    }
}