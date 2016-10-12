package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;
import java.io.Serializable;
import java.util.List;

/**
 * Class to represent Canvas assigmnents.
 * See <a href="https://canvas.instructure.com/doc/api/assignments.html#Assignment">Canvas assignment</a> documentation.
 */
@CanvasObject(postKey = "assignment")
public class Assignment extends BaseCanvasModel implements Serializable{

    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String description;
    private String createdAt;
    private String updatedAt;
    private String dueAt;
    private String lockAt;
    private String unlockAt;
    private String allDates;
    private String courseId;
    private String htmlUrl;
    private String assingmentGroupId;
    private String[] allowedExtensions;
    private Boolean turnitinEnabled;
    private String turnitinSettings;
    private Boolean gradeGroupStudentsIndividually;
    private String externalToolTagAttributes;
    private Boolean peerReviews;
    private Boolean automaticPeerReviews;
    private String peerReviewCount;
    private String peerReviewsAssignAt;
    private String groupCategoryId;
    private String needsGradingCount;
    private String needsGradingCountBySection;
    private String position;
    private Boolean postToSis;
    private String integrationId;
    private Object integrationData;
    private String muted;
    private Integer pointsPossible;
    private List<String> submissionTypes;
    private String gradingType;
    private String gradingStandardId;
    private Boolean published;
    private Boolean unpublishable;
    private Boolean onlyVisibleToOverrides;
    private Boolean lockedForUser;
    private Boolean notifyOfUpdate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @CanvasField(postKey = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @CanvasField(postKey = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @CanvasField(postKey = "due_at")
    public String getDueAt() {
        return dueAt;
    }

    public void setDueAt(String dueAt) {
        this.dueAt = dueAt;
    }

    @CanvasField(postKey = "lock_at")
    public String getLockAt() {
        return lockAt;
    }

    public void setLockAt(String lockAt) {
        this.lockAt = lockAt;
    }

    @CanvasField(postKey = "unlock_at")
    public String getUnlockAt() {
        return unlockAt;
    }

    public void setUnlockAt(String unlockAt) {
        this.unlockAt = unlockAt;
    }

    public String getAllDates() {
        return allDates;
    }

    public void setAllDates(String allDates) {
        this.allDates = allDates;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getAssingmentGroupId() {
        return assingmentGroupId;
    }

    public void setAssingmentGroupId(String assingmentGroupId) {
        this.assingmentGroupId = assingmentGroupId;
    }

    public String[] getAllowedExtensions() {
        return allowedExtensions;
    }

    public void setAllowedExtensions(String[] allowedExtensions) {
        this.allowedExtensions = allowedExtensions;
    }

    @CanvasField(postKey = "turnitin_enabled")
    public Boolean getTurnitinEnabled() {
        return turnitinEnabled;
    }

    public void setTurnitinEnabled(Boolean turnitinEnabled) {
        this.turnitinEnabled = turnitinEnabled;
    }

    public String getTurnitinSettings() {
        return turnitinSettings;
    }

    public void setTurnitinSettings(String turnitinSettings) {
        this.turnitinSettings = turnitinSettings;
    }

    @CanvasField(postKey = "grade_group_students_individually")
    public Boolean getGradeGroupStudentsIndividually() {
        return gradeGroupStudentsIndividually;
    }

    public void setGradeGroupStudentsIndividually(Boolean gradeGroupStudentsIndividually) {
        this.gradeGroupStudentsIndividually = gradeGroupStudentsIndividually;
    }

    public String getExternalToolTagAttributes() {
        return externalToolTagAttributes;
    }

    public void setExternalToolTagAttributes(String externalToolTagAttributes) {
        this.externalToolTagAttributes = externalToolTagAttributes;
    }

    @CanvasField(postKey = "peer_reviews")
    public Boolean getPeerReviews() {
        return peerReviews;
    }

    public void setPeerReviews(Boolean peerReviews) {
        this.peerReviews = peerReviews;
    }

    @CanvasField(postKey = "automatic_peer_reviews")
    public Boolean getAutomaticPeerReviews() {
        return automaticPeerReviews;
    }

    public void setAutomaticPeerReviews(Boolean automaticPeerReviews) {
        this.automaticPeerReviews = automaticPeerReviews;
    }

    public String getPeerReviewCount() {
        return peerReviewCount;
    }

    public void setPeerReviewCount(String peerReviewCount) {
        this.peerReviewCount = peerReviewCount;
    }

    public String getPeerReviewsAssignAt() {
        return peerReviewsAssignAt;
    }

    public void setPeerReviewsAssignAt(String peerReviewsAssignAt) {
        this.peerReviewsAssignAt = peerReviewsAssignAt;
    }

    @CanvasField(postKey = "group_category_id")
    public String getGroupCategoryId() {
        return groupCategoryId;
    }

    public void setGroupCategoryId(String groupCategoryId) {
        this.groupCategoryId = groupCategoryId;
    }

    public String getNeedsGradingCount() {
        return needsGradingCount;
    }

    public void setNeedsGradingCount(String needsGradingCount) {
        this.needsGradingCount = needsGradingCount;
    }

    public String getNeedsGradingCountBySection() {
        return needsGradingCountBySection;
    }

    public void setNeedsGradingCountBySection(String needsGradingCountBySection) {
        this.needsGradingCountBySection = needsGradingCountBySection;
    }

    @CanvasField(postKey = "position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Boolean getPostToSis() {
        return postToSis;
    }

    public void setPostToSis(Boolean postToSis) {
        this.postToSis = postToSis;
    }

    @CanvasField(postKey = "integration_id")
    public String getIntegrationId() {
        return integrationId;
    }

    public void setIntegrationId(String integrationId) {
        this.integrationId = integrationId;
    }

    @CanvasField(postKey = "integration_data")
    public Object getIntegrationData() {
        return integrationData;
    }

    public void setIntegrationData(Object integrationData) {
        this.integrationData = integrationData;
    }

    @CanvasField(postKey = "muted")
    public String getMuted() {
        return muted;
    }

    public void setMuted(String muted) {
        this.muted = muted;
    }

    @CanvasField(postKey = "points_possible")
    public Integer getPointsPossible() {
        return pointsPossible;
    }

    public void setPointsPossible(Integer pointsPossible) {
        this.pointsPossible = pointsPossible;
    }

    public List<String> getSubmissionTypes() {
        return submissionTypes;
    }

    public void setSubmissionTypes(List<String> submissionTypes) {
        this.submissionTypes = submissionTypes;
    }

    @CanvasField(postKey = "grading_type")
    public String getGradingType() {
        return gradingType;
    }

    public void setGradingType(String gradingType) {
        this.gradingType = gradingType;
    }

    @CanvasField(postKey = "grading_standard_id")
    public String getGradingStandardId() {
        return gradingStandardId;
    }

    public void setGradingStandardId(String gradingStandardId) {
        this.gradingStandardId = gradingStandardId;
    }

    @CanvasField(postKey = "published")
    public Boolean isPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Boolean isUnpublishable() {
        return unpublishable;
    }

    public void setUnpublishable(Boolean unpublishable) {
        this.unpublishable = unpublishable;
    }

    @CanvasField(postKey = "only_visible_to_overrides")
    public Boolean isOnlyVisibleToOverrides() {
        return onlyVisibleToOverrides;
    }

    public void setOnlyVisibleToOverrides(Boolean onlyVisibleToOverrides) {
        this.onlyVisibleToOverrides = onlyVisibleToOverrides;
    }

    public Boolean isLockedForUser() {
        return lockedForUser;
    }

    public void setLockedForUser(Boolean lockedForUser) {
        this.lockedForUser = lockedForUser;
    }

    @CanvasField(postKey = "notify_of_update")
    public Boolean isNotifyOfUpdate() {
        return notifyOfUpdate;
    }

    public void setNotifyOfUpdate(Boolean notifyOfUpdate) {
        this.notifyOfUpdate = notifyOfUpdate;
    }
}
