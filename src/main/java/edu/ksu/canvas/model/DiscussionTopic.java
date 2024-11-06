package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasField;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DiscussionTopic extends BaseCanvasModel implements Serializable {

    public static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String message;
    private String htmlUrl;
    private Date postedAt;
    private Date lastReplyAt;
    private Boolean requireInitialPost;
    private Boolean userCanSeePosts;
    private Long discussionSubentryCount;
    private String readState;
    private Long unreadCount;
    private Boolean subscribed;
    private String subscriptionHold;
    private Long assignmentId;
    private Date delayedPostAt;
    private Boolean published;
    private Boolean locked;
    private Boolean pinned;
    private Boolean lockedForUser;
    private String lockInfo;
    private String lockExplanation;
    private String userName;
    // group_topic_children
    private Long rootTopicId;
    private String podcastUrl;
    private String discussionType;
    private Long groupCategoryId;
    private List<File> attachments;
    // permissions
    private Boolean allowRating;
    private Boolean onlyGradersCanRate;
    private Boolean sortByRating;
    // TODO: finish adding fields

    @CanvasField(postKey = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @CanvasField(postKey = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @CanvasField(postKey = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @CanvasField(postKey = "html_url")
    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    @CanvasField(postKey = "posted_at")
    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }

    @CanvasField(postKey = "last_reply_at")
    public Date getLastReplyAt() {
        return lastReplyAt;
    }

    public void setLastReplyAt(Date lastReplyAt) {
        this.lastReplyAt = lastReplyAt;
    }

    @CanvasField(postKey = "require_initial_post")
    public Boolean getRequireInitialPost() {
        return requireInitialPost;
    }

    public void setRequireInitialPost(Boolean requireInitialPost) {
        this.requireInitialPost = requireInitialPost;
    }

    @CanvasField(postKey = "user_can_see_posts")
    public Boolean getUserCanSeePosts() {
        return userCanSeePosts;
    }

    public void setUserCanSeePosts(Boolean userCanSeePosts) {
        this.userCanSeePosts = userCanSeePosts;
    }

    @CanvasField(postKey = "discussion_subentry_count")
    public Long getDiscussionSubentryCount() {
        return discussionSubentryCount;
    }

    public void setDiscussionSubentryCount(Long discussionSubentryCount) {
        this.discussionSubentryCount = discussionSubentryCount;
    }

    @CanvasField(postKey = "read_state")
    public String getReadState() {
        return readState;
    }

    public void setReadState(String readState) {
        this.readState = readState;
    }

    @CanvasField(postKey = "unread_count")
    public Long getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Long unreadCount) {
        this.unreadCount = unreadCount;
    }

    @CanvasField(postKey = "subscribed")
    public Boolean getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }

    @CanvasField(postKey = "subscription_hold")
    public String getSubscriptionHold() {
        return subscriptionHold;
    }

    public void setSubscriptionHold(String subscriptionHold) {
        this.subscriptionHold = subscriptionHold;
    }

    @CanvasField(postKey = "assignment_id")
    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    @CanvasField(postKey = "delayed_post_at")
    public Date getDelayedPostAt() {
        return delayedPostAt;
    }

    public void setDelayedPostAt(Date delayedPostAt) {
        this.delayedPostAt = delayedPostAt;
    }

    @CanvasField(postKey = "published")
    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    @CanvasField(postKey = "locked")
    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    @CanvasField(postKey = "pinned")
    public Boolean getPinned() {
        return pinned;
    }

    public void setPinned(Boolean pinned) {
        this.pinned = pinned;
    }

    @CanvasField(postKey = "locked_for_user")
    public Boolean getLockedForUser() {
        return lockedForUser;
    }

    public void setLockedForUser(Boolean lockedForUser) {
        this.lockedForUser = lockedForUser;
    }

    @CanvasField(postKey = "lock_info")
    public String getLockInfo() {
        return lockInfo;
    }

    public void setLockInfo(String lockInfo) {
        this.lockInfo = lockInfo;
    }

    @CanvasField(postKey = "lock_explanation")
    public String getLockExplanation() {
        return lockExplanation;
    }

    public void setLockExplanation(String lockExplanation) {
        this.lockExplanation = lockExplanation;
    }

    @CanvasField(postKey = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @CanvasField(postKey = "root_topic_id")
    public Long getRootTopicId() {
        return rootTopicId;
    }

    public void setRootTopicId(Long rootTopicId) {
        this.rootTopicId = rootTopicId;
    }

    @CanvasField(postKey = "podcast_url")
    public String getPodcastUrl() {
        return podcastUrl;
    }

    public void setPodcastUrl(String podcastUrl) {
        this.podcastUrl = podcastUrl;
    }

    @CanvasField(postKey = "discussion_type")
    public String getDiscussionType() {
        return discussionType;
    }

    public void setDiscussionType(String discussionType) {
        this.discussionType = discussionType;
    }

    @CanvasField(postKey = "group_category_id")
    public Long getGroupCategoryId() {
        return groupCategoryId;
    }

    public void setGroupCategoryId(Long groupCategoryId) {
        this.groupCategoryId = groupCategoryId;
    }

    @CanvasField(postKey = "attachments")
    public List<File> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<File> attachments) {
        this.attachments = attachments;
    }

    @CanvasField(postKey = "allow_rating")
    public Boolean getAllowRating() {
        return allowRating;
    }

    public void setAllowRating(Boolean allowRating) {
        this.allowRating = allowRating;
    }

    @CanvasField(postKey = "only_graders_can_rate")
    public Boolean getOnlyGradersCanRate() {
        return onlyGradersCanRate;
    }

    public void setOnlyGradersCanRate(Boolean onlyGradersCanRate) {
        this.onlyGradersCanRate = onlyGradersCanRate;
    }

    @CanvasField(postKey = "sort_by_rating")
    public Boolean getSortByRating() {
        return sortByRating;
    }

    public void setSortByRating(Boolean sortByRating) {
        this.sortByRating = sortByRating;
    }
}
