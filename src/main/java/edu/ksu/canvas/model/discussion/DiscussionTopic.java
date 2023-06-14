package edu.ksu.canvas.model.discussion;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;
import edu.ksu.canvas.model.BaseCanvasModel;
import edu.ksu.canvas.model.File;
import edu.ksu.canvas.model.assignment.LockInfo;

import java.io.Serializable;
import java.util.Date;

/**
 * Class to represent canvas Discussion Topics.
 * See <a href="https://canvas.instructure.com/doc/api/discussion_topics.html#DiscussionTopic">Canvas DiscussionTopic</a>documentation.
 */
@CanvasObject(postKey = "discussion_topic")
public class DiscussionTopic extends BaseCanvasModel implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String title;
    private String message;
    private String htmlURL;
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
    private Date lockAt;
    private Boolean locked;
    private Boolean pinned;
    private Boolean lockedForUser;
    private LockInfo lockInfo;
    private String lockExplanation;
    private String userName;
    private Long[] topicChildren;
    private TopicChildren[] groupTopicChildren;
    private Long rootTopicId;
    private String podcastURL;
    private String discussionType;
    private Long groupCategoryId;
    private File[] attachments;
    private DiscussionTopicPermission permissions;
    private Boolean allowRating;
    private Boolean onlyGradersCanRate;
    private Boolean sortByRating;

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

    public String getHtmlURL() {
        return htmlURL;
    }

    public void setHtmlURL(String htmlURL) {
        this.htmlURL = htmlURL;
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }

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

    public Boolean getUserCanSeePosts() {
        return userCanSeePosts;
    }

    public void setUserCanSeePosts(Boolean userCanSeePosts) {
        this.userCanSeePosts = userCanSeePosts;
    }

    public Long getDiscussionSubentryCount() {
        return discussionSubentryCount;
    }

    public void setDiscussionSubentryCount(Long discussionSubentryCount) {
        this.discussionSubentryCount = discussionSubentryCount;
    }

    public String getReadState() {
        return readState;
    }

    public void setReadState(String readState) {
        this.readState = readState;
    }

    public Long getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Long unreadCount) {
        this.unreadCount = unreadCount;
    }

    public Boolean getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }

    public String getSubscriptionHold() {
        return subscriptionHold;
    }

    public void setSubscriptionHold(String subscriptionHold) {
        this.subscriptionHold = subscriptionHold;
    }

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

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    @CanvasField(postKey = "lock_at")
    public Date getLockAt() {
        return lockAt;
    }

    public void setLockAt(Date lockAt) {
        this.lockAt = lockAt;
    }

    @CanvasField(postKey = "locked")
    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getPinned() {
        return pinned;
    }

    public void setPinned(Boolean pinned) {
        this.pinned = pinned;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long[] getTopicChildren() {
        return topicChildren;
    }

    public void setTopicChildren(Long[] topicChildren) {
        this.topicChildren = topicChildren;
    }

    public TopicChildren[] getGroupTopicChildren() {
        return groupTopicChildren;
    }

    public void setGroupTopicChildren(TopicChildren[] groupTopicChildren) {
        this.groupTopicChildren = groupTopicChildren;
    }

    public Long getRootTopicId() {
        return rootTopicId;
    }

    public void setRootTopicId(Long rootTopicId) {
        this.rootTopicId = rootTopicId;
    }

    public String getPodcastURL() {
        return podcastURL;
    }

    public void setPodcastURL(String podcastURL) {
        this.podcastURL = podcastURL;
    }

    @CanvasField(postKey = "discussion_type")
    public String getDiscussionType() {
        return discussionType;
    }

    public void setDiscussionType(String discussionType) {
        this.discussionType = discussionType;
    }

    public Long getGroupCategoryId() {
        return groupCategoryId;
    }

    public void setGroupCategoryId(Long groupCategoryId) {
        this.groupCategoryId = groupCategoryId;
    }

    @CanvasField(postKey = "attachments")
    public File[] getAttachments() {
        return attachments;
    }

    public void setAttachments(File[] attachments) {
        this.attachments = attachments;
    }

    public DiscussionTopicPermission getPermissions() {
        return permissions;
    }

    public void setPermissions(DiscussionTopicPermission permissions) {
        this.permissions = permissions;
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

    public class TopicChildren implements Serializable {

        private static final long serialVersionUID = 1L;

        private String topicId;
        private String groupId;

        public String getTopicId() {
            return topicId;
        }

        public void setTopicId(String topicId) {
            this.topicId = topicId;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }
    }

}
