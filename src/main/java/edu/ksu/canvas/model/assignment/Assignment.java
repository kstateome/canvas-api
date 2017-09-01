package edu.ksu.canvas.model.assignment;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;
import edu.ksu.canvas.model.BaseCanvasModel;

import java.io.Serializable;
import java.util.Date;
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
    private Date createdAt;
    private Date updatedAt;
    private Date dueAt;
    private Date lockAt;
    private Date unlockAt;
    private List<AssignmentDate> allDates;
    private String courseId;
    private String htmlUrl;
    private Long assignmentGroupId;
    private String[] allowedExtensions;
    private Boolean turnitinEnabled;
    private TurnitinSettings turnitinSettings;
    private Boolean gradeGroupStudentsIndividually;
    private ExternalToolTagAttribute externalToolTagAttributes;
    private Boolean peerReviews;
    private Boolean automaticPeerReviews;
    private String peerReviewCount;
    private Date peerReviewsAssignAt;
    private String groupCategoryId;
    private Integer needsGradingCount;
    private NeedsGradingCount needsGradingCountBySection;
    private String position;
    private Boolean postToSis;
    private String integrationId;
    private Object integrationData;
    private String muted;
    private Double pointsPossible;
    private List<String> submissionTypes;
    private String gradingType;
    private String gradingStandardId;
    private Boolean published;
    private Boolean unpublishable;
    private Boolean onlyVisibleToOverrides;
    private Boolean lockedForUser;
    private LockInfo lockInfo;
    private String lockExplanation;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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

    public List<AssignmentDate> getAllDates() {
        return allDates;
    }

    public void setAllDates(List<AssignmentDate> allDates) {
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

    public Long getAssignmentGroupId() {
        return assignmentGroupId;
    }

    public void setAssignmentGroupId(Long assignmentGroupId) {
        this.assignmentGroupId = assignmentGroupId;
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

    public TurnitinSettings getTurnitinSettings() {
        return turnitinSettings;
    }

    public void setTurnitinSettings(TurnitinSettings turnitinSettings) {
        this.turnitinSettings = turnitinSettings;
    }

    @CanvasField(postKey = "grade_group_students_individually")
    public Boolean getGradeGroupStudentsIndividually() {
        return gradeGroupStudentsIndividually;
    }

    public void setGradeGroupStudentsIndividually(Boolean gradeGroupStudentsIndividually) {
        this.gradeGroupStudentsIndividually = gradeGroupStudentsIndividually;
    }

    public ExternalToolTagAttribute getExternalToolTagAttributes() {
        return externalToolTagAttributes;
    }

    public void setExternalToolTagAttributes(ExternalToolTagAttribute externalToolTagAttributes) {
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

    public Date getPeerReviewsAssignAt() {
        return peerReviewsAssignAt;
    }

    public void setPeerReviewsAssignAt(Date peerReviewsAssignAt) {
        this.peerReviewsAssignAt = peerReviewsAssignAt;
    }

    @CanvasField(postKey = "group_category_id")
    public String getGroupCategoryId() {
        return groupCategoryId;
    }

    public void setGroupCategoryId(String groupCategoryId) {
        this.groupCategoryId = groupCategoryId;
    }

    public Integer getNeedsGradingCount() {
        return needsGradingCount;
    }

    public void setNeedsGradingCount(Integer needsGradingCount) {
        this.needsGradingCount = needsGradingCount;
    }

    public NeedsGradingCount getNeedsGradingCountBySection() {
        return needsGradingCountBySection;
    }

    public void setNeedsGradingCountBySection(NeedsGradingCount needsGradingCountBySection) {
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
    public Double getPointsPossible() {
        return pointsPossible;
    }

    public void setPointsPossible(Double pointsPossible) {
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

    @CanvasField(postKey = "notify_of_update")
    public Boolean isNotifyOfUpdate() {
        return notifyOfUpdate;
    }

    public void setNotifyOfUpdate(Boolean notifyOfUpdate) {
        this.notifyOfUpdate = notifyOfUpdate;
    }

    public class ExternalToolTagAttribute implements Serializable {
        private static final long serialVersionUID = 1L;

        private String url;
        private Boolean newTab;
        private String resourceLinkId;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Boolean getNewTab() {
            return newTab;
        }

        public void setNewTab(Boolean newTab) {
            this.newTab = newTab;
        }

        public String getResourceLinkId() {
            return resourceLinkId;
        }

        public void setResourceLinkId(String resourceLinkId) {
            this.resourceLinkId = resourceLinkId;
        }
    }

    public class TurnitinSettings implements Serializable {
        private static final long serialVersionUID = 1L;

        private String originalityReportVisibility;
        private Boolean sPaperCheck;
        private Boolean internetCheck;
        private Boolean journalCheck;
        private Boolean excludeBiblio;
        private Boolean excludeQuoted;
        private String excludeSmallMatchesType;
        private String excludeSmallMatchesValue;

        public String getOriginalityReportVisibility() {
            return originalityReportVisibility;
        }

        public void setOriginalityReportVisibility(
                String originalityReportVisibility) {
            this.originalityReportVisibility = originalityReportVisibility;
        }

        public Boolean getsPaperCheck() {
            return sPaperCheck;
        }

        public void setsPaperCheck(Boolean sPaperCheck) {
            this.sPaperCheck = sPaperCheck;
        }

        public Boolean getInternetCheck() {
            return internetCheck;
        }

        public void setInternetCheck(Boolean internetCheck) {
            this.internetCheck = internetCheck;
        }

        public Boolean getJournalCheck() {
            return journalCheck;
        }

        public void setJournalCheck(Boolean journalCheck) {
            this.journalCheck = journalCheck;
        }

        public Boolean getExcludeBiblio() {
            return excludeBiblio;
        }

        public void setExcludeBiblio(Boolean excludeBiblio) {
            this.excludeBiblio = excludeBiblio;
        }

        public Boolean getExcludeQuoted() {
            return excludeQuoted;
        }

        public void setExcludeQuoted(Boolean excludeQuoted) {
            this.excludeQuoted = excludeQuoted;
        }

        public String getExcludeSmallMatchesType() {
            return excludeSmallMatchesType;
        }

        public void setExcludeSmallMatchesType(String excludeSmallMatchesType) {
            this.excludeSmallMatchesType = excludeSmallMatchesType;
        }

        public String getExcludeSmallMatchesValue() {
            return excludeSmallMatchesValue;
        }

        public void setExcludeSmallMatchesValue(String excludeSmallMatchesValue) {
            this.excludeSmallMatchesValue = excludeSmallMatchesValue;
        }
    }

    public class NeedsGradingCount implements Serializable {
        private static final long serialVersionUID = 1L;

        private String sectionId;
        private Integer needsGradingCount;

        public String getSectionId() {
            return sectionId;
        }

        public void setSectionId(String sectionId) {
            this.sectionId = sectionId;
        }

        public Integer getNeedsGradingCount() {
            return needsGradingCount;
        }

        public void setNeedsGradingCount(Integer needsGradingCount) {
            this.needsGradingCount = needsGradingCount;
        }
    }
}
