package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;

import java.io.Serializable;

/**
 * Class to represent course settings in Canvas.
 * See <a href="https://canvas.instructure.com/doc/api/courses.html#method.courses.api_settings">Canvas course settings</a> documentation.
 */
@CanvasObject(postKey = "") //The update call for course settings only works with a form post which does NOT include the object name
public class CourseSettings  extends BaseCanvasModel implements Serializable {
    public static final long serialVersionUID = 1L;

    private Boolean allowFinalGradeOverride;
    private Boolean allowStudentDiscussionTopics;
    private Boolean allowStudentForumAttachments;
    private Boolean allowStudentDiscussionEditing;
    private Boolean filterSpeedGraderByStudentGroup;
    private Boolean gradingStandardEnabled;
    private Long gradingStandardId;
    private Boolean allowStudentOrganizedGroups;
    private Boolean hideFinalGrades;
    private Boolean hideDistributionGraphs;
    private Boolean lockAllAnnouncements;
    private Boolean restrictStudentPastView;
    private Boolean restrictStudentFutureView;
    private Boolean showAnnouncementsOnHomePage;
    private Integer homePageAnnouncementLimit;
    private String imageUrl;
    private String imageId;
    private String image;

    public Boolean getAllowFinalGradeOverride() {
        return allowFinalGradeOverride;
    }

    public void setAllowFinalGradeOverride(Boolean allowFinalGradeOverride) {
        this.allowFinalGradeOverride = allowFinalGradeOverride;
    }

    @CanvasField(postKey = "allow_student_discussion_topics")
    public Boolean getAllowStudentDiscussionTopics() {
        return allowStudentDiscussionTopics;
    }

    public void setAllowStudentDiscussionTopics(Boolean allowStudentDiscussionTopics) {
        this.allowStudentDiscussionTopics = allowStudentDiscussionTopics;
    }

    @CanvasField(postKey = "allow_student_forum_attachments")
    public Boolean getAllowStudentForumAttachments() {
        return allowStudentForumAttachments;
    }

    public void setAllowStudentForumAttachments(Boolean allowStudentForumAttachments) {
        this.allowStudentForumAttachments = allowStudentForumAttachments;
    }

    @CanvasField(postKey = "allow_student_discussion_editing")
    public Boolean getAllowStudentDiscussionEditing() {
        return allowStudentDiscussionEditing;
    }

    public void setAllowStudentDiscussionEditing(Boolean allowStudentDiscussionEditing) {
        this.allowStudentDiscussionEditing = allowStudentDiscussionEditing;
    }

    @CanvasField(postKey = "filter_speed_grader_by_student_group")
    public Boolean getFilterSpeedGraderByStudentGroup() {
        return filterSpeedGraderByStudentGroup;
    }

    public void setFilterSpeedGraderByStudentGroup(Boolean filterSpeedGraderByStudentGroup) {
        this.filterSpeedGraderByStudentGroup = filterSpeedGraderByStudentGroup;
    }

    public Boolean getGradingStandardEnabled() {
        return gradingStandardEnabled;
    }

    public void setGradingStandardEnabled(Boolean gradingStandardEnabled) {
        this.gradingStandardEnabled = gradingStandardEnabled;
    }

    public Long getGradingStandardId() {
        return gradingStandardId;
    }

    public void setGradingStandardId(Long gradingStandardId) {
        this.gradingStandardId = gradingStandardId;
    }

    @CanvasField(postKey = "allow_student_organized_groups")
    public Boolean getAllowStudentOrganizedGroups() {
        return allowStudentOrganizedGroups;
    }

    public void setAllowStudentOrganizedGroups(Boolean allowStudentOrganizedGroups) {
        this.allowStudentOrganizedGroups = allowStudentOrganizedGroups;
    }

    @CanvasField(postKey = "hide_final_grades")
    public Boolean getHideFinalGrades() {
        return hideFinalGrades;
    }

    public void setHideFinalGrades(Boolean hideFinalGrades) {
        this.hideFinalGrades = hideFinalGrades;
    }

    @CanvasField(postKey = "hide_distribution_graphs")
    public Boolean getHideDistributionGraphs() {
        return hideDistributionGraphs;
    }

    public void setHideDistributionGraphs(Boolean hideDistributionGraphs) {
        this.hideDistributionGraphs = hideDistributionGraphs;
    }

    @CanvasField(postKey = "lock_all_announcements")
    public Boolean getLockAllAnnouncements() {
        return lockAllAnnouncements;
    }

    public void setLockAllAnnouncements(Boolean lockAllAnnouncements) {
        this.lockAllAnnouncements = lockAllAnnouncements;
    }

    @CanvasField(postKey = "restrict_student_past_view")
    public Boolean getRestrictStudentPastView() {
        return restrictStudentPastView;
    }

    public void setRestrictStudentPastView(Boolean restrictStudentPastView) {
        this.restrictStudentPastView = restrictStudentPastView;
    }

    @CanvasField(postKey = "restrict_student_future_view")
    public Boolean getRestrictStudentFutureView() {
        return restrictStudentFutureView;
    }

    public void setRestrictStudentFutureView(Boolean restrictStudentFutureView) {
        this.restrictStudentFutureView = restrictStudentFutureView;
    }

    @CanvasField(postKey = "show_announcements_on_home_page")
    public Boolean getShowAnnouncementsOnHomePage() {
        return showAnnouncementsOnHomePage;
    }

    public void setShowAnnouncementsOnHomePage(Boolean showAnnouncementsOnHomePage) {
        this.showAnnouncementsOnHomePage = showAnnouncementsOnHomePage;
    }

    @CanvasField(postKey = "home_page_announcement_limit")
    public Integer getHomePageAnnouncementLimit() {
        return homePageAnnouncementLimit;
    }

    public void setHomePageAnnouncementLimit(Integer homePageAnnouncementLimit) {
        this.homePageAnnouncementLimit = homePageAnnouncementLimit;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
